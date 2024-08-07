package project.homelearn.repository.vote.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.vote.VoteBasicDto;
import project.homelearn.dto.teacher.vote.VoteDetailDto;
import project.homelearn.dto.teacher.vote.VoteDetailSub;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.StudentVote;
import project.homelearn.entity.vote.VoteContent;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.vote.StudentVoteRepository;
import project.homelearn.repository.vote.VoteContentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static project.homelearn.entity.vote.QStudentVote.studentVote;
import static project.homelearn.entity.vote.QVote.vote;
import static project.homelearn.entity.vote.QVoteContent.voteContent;

@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final StudentRepository studentRepository;
    private final VoteContentRepository voteContentRepository;
    private final StudentVoteRepository studentVoteRepository;

    @Override
    public Page<VoteTabDto> findVoteTab(Curriculum curriculum, Pageable pageable, String status) {
        List<Tuple> tuples = new ArrayList<>();

        if (status.equals("진행")) {
            tuples = queryFactory
                    .select(vote.id, vote.title, vote.description, vote.endTime)
                    .from(vote)
                    .where(vote.curriculum.eq(curriculum), vote.endTime.after(LocalDateTime.now()))
                    .orderBy(vote.createdDate.desc())
                    .fetch();
        }

        if (status.equals("마감")) {
            tuples = queryFactory
                    .select(vote.id, vote.title, vote.description, vote.endTime)
                    .from(vote)
                    .where(vote.curriculum.eq(curriculum), vote.endTime.before(LocalDateTime.now()).or(vote.isFinished.eq(true)))
                    .orderBy(vote.createdDate.desc())
                    .fetch();
        }

        List<VoteTabDto> voteTabDtos = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Long voteId = tuple.get(vote.id);
            String title = tuple.get(vote.title);
            String description = tuple.get(vote.description);
            LocalDateTime endTime = tuple.get(vote.endTime);
            Long completedCount = findParticipantCount(voteId);

            VoteTabDto vote = new VoteTabDto();
            vote.setVoteId(voteId);
            vote.setTitle(title);
            vote.setDescription(description);
            vote.setEndTime(endTime);
            vote.setVoteCount(completedCount);
            voteTabDtos.add(vote);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), voteTabDtos.size());

        return new PageImpl<>(voteTabDtos.subList(start, end), pageable, voteTabDtos.size());
    }

    @Override
    public Long findParticipantCount(Long voteId) {
        return queryFactory
                .select(studentVote.user.id.count()).distinct()
                .from(studentVote)
                .join(studentVote.voteContent, voteContent)
                .where(voteContent.vote.id.eq(voteId))
                .fetchOne();
    }

    @Override
    public VoteBasicDto findVoteBasic(Long voteId, Curriculum curriculum) {
        Tuple tuple = queryFactory
                .select(vote.id, vote.title, vote.description, vote.endTime, vote.isAnonymous, vote.isMultipleChoice, vote.isFinished)
                .from(vote)
                .where(vote.id.eq(voteId))
                .fetchOne();

        if (tuple == null) {
            return null;
        }

        Long total = (long) studentRepository.findStudentCountByCurriculum(curriculum);
        Long participantCount = findParticipantCount(voteId);
        Map<String, Long> voteCountByContent = getVoteCountByContent(voteId);

        return VoteBasicDto
                .builder()
                .voteId(tuple.get(vote.id))
                .title(tuple.get(vote.title))
                .description(tuple.get(vote.description))
                .endTime(tuple.get(vote.endTime))
                .isAnonymous(tuple.get(vote.isAnonymous))
                .isMultiple(tuple.get(vote.isMultipleChoice))
                .isFinished(tuple.get(vote.isFinished))
                .total(total)
                .participantCount(participantCount)
                .voteCountByContent(voteCountByContent)
                .build();
    }

    @Override
    public VoteDetailDto findVoteDetail(Long voteId) {
        Map<String, Long> voteCountByContent = getVoteCountByContent(voteId);
        VoteDetailDto result = new VoteDetailDto();
        result.setVoteCountByContent(voteCountByContent);

        List<VoteDetailSub> voteDetailSubList = new ArrayList<>();

        List<VoteContent> voteContents = voteContentRepository.findByVoteId(voteId);
        for (VoteContent voteContent : voteContents) {
            voteDetailSubList.addAll(createVoteDetailSubList(voteContent));
        }

        result.setVoteDetailSubList(voteDetailSubList);
        return result;
    }

    private List<VoteDetailSub> createVoteDetailSubList(VoteContent voteContent) {
        List<VoteDetailSub> voteDetailSubList = new ArrayList<>();
        List<StudentVote> studentVotes = studentVoteRepository.findAllByVoteContentId(voteContent.getId());

        for (StudentVote studentVote : studentVotes) {
            User student = studentVote.getUser();
            VoteDetailSub detailSub = new VoteDetailSub();
            detailSub.setName(student.getName());
            detailSub.setImagePath(student.getImagePath());
            detailSub.setContent(voteContent.getContent());
            voteDetailSubList.add(detailSub);
        }

        return voteDetailSubList;
    }

    public Map<String, Long> getVoteCountByContent(Long voteId) {
        List<VoteContent> voteContents = voteContentRepository.findByVoteId(voteId);
        Map<String, Long> voteCountByContent = new ConcurrentHashMap<>();

        for (VoteContent voteContent : voteContents) {
            voteCountByContent.put(voteContent.getContent(), (long) voteContent.getVoteCount());
        }

        return voteCountByContent;
    }
}