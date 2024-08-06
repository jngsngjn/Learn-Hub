package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.vote.VoteCreateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.vote.Vote;
import project.homelearn.entity.vote.VoteContent;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.vote.VoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@EnableScheduling
@RequiredArgsConstructor
public class TeacherVoteService {
    private final VoteRepository voteRepository;
    private final CurriculumRepository curriculumRepository;

    // 투표 생성
    public boolean createVote(VoteCreateDto voteCreateDto, String username) {

        try {
            // 투표가 속한 커리큘럼 찾기
            Curriculum curriculum = curriculumRepository.findCurriculumByTeacher(username);

            // 새로운 투표 생성
            Vote vote = new Vote();
            vote.setCurriculum(curriculum); //커리큘럼 설정
            vote.setTitle(voteCreateDto.getTitle());
            vote.setDescription(voteCreateDto.getDescription());

            vote.setEndTime(voteCreateDto.getEndTime());

            vote.setIsAnonymous(voteCreateDto.getIsAnonymous());
            vote.setIsMultipleChoice(voteCreateDto.getIsMultipleChoice());
            vote.setFinished(false);

            // 투표 항목 추가
            for (String contents : voteCreateDto.getContents()) {
                VoteContent voteContent = new VoteContent();
                voteContent.setVote(vote);
                voteContent.setContent(contents);
                vote.getContents().add(voteContent);
            }

            voteRepository.save(vote);
            return true;
        } catch (Exception e) {
            log.error("Error creating Vote", e);
            return false;
        }
    }

    // 투표 삭제
    public boolean deleteVote(Long id) {
        try {
            voteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting Vote", e);
            return false;
        }
    }

    // 투표 수동 마감
    public boolean finishVotes(Long id) {
        try {
            Vote vote = voteRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Vote not found" + id));

            vote.setFinished(true);
            return true;
        } catch (Exception e) {
            log.error("Error finishing vote for voteId '{}'", id, e);
            return false;
        }
    }

    // 1분마다 실행
    @Scheduled(fixedDelay = 60000)
    public void autoFinishVotes() {
        try {
            long count = voteRepository.count();

            if (count > 0) {
                List<Vote> votes = voteRepository.findAllByIsFinishedFalse();
                LocalDateTime now = LocalDateTime.now();
                for (Vote vote : votes) {
                    if (vote.getEndTime().isBefore(now)) {
                        vote.setFinished(true);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error auto finishing votes", e);
        }
    }
}