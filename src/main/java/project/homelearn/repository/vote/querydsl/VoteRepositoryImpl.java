package project.homelearn.repository.vote.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.vote.QStudentVote.studentVote;
import static project.homelearn.entity.vote.QVote.vote;
import static project.homelearn.entity.vote.QVoteContent.voteContent;

@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

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
                    .where(vote.curriculum.eq(curriculum), vote.endTime.before(LocalDateTime.now()))
                    .orderBy(vote.createdDate.desc())
                    .fetch();
        }

        List<VoteTabDto> voteTabDtos = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Long voteId = tuple.get(vote.id);
            String title = tuple.get(vote.title);
            String description = tuple.get(vote.description);
            LocalDateTime endTime = tuple.get(vote.endTime);
            Long completedCount = findCompletedCount(voteId);

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
    public Long findCompletedCount(Long voteId) {
        return queryFactory
                .select(studentVote.user.id.count()).distinct()
                .from(studentVote)
                .join(studentVote.voteContent, voteContent)
                .where(voteContent.vote.id.eq(voteId))
                .fetchOne();
    }
}