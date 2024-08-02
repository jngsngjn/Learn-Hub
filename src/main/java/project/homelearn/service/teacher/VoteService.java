package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.vote.VoteContentDto;
import project.homelearn.dto.teacher.vote.VoteDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;
import project.homelearn.entity.vote.Vote;
import project.homelearn.entity.vote.VoteContent;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.repository.vote.VoteContentRepository;
import project.homelearn.repository.vote.VoteRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteContentRepository voteContentRepository;
    private final UserRepository userRepository;

    public boolean createVote(VoteDto voteDto, String username) {
        try {
            // 투표가 속한 커리큘럼 찾기
            User user = userRepository.findByUsername(username);

            // 새로운 투표 생성
            Vote vote = new Vote();
            vote.setTitle(voteDto.getTitle());
            vote.setDescription(voteDto.getDescription());
            vote.setStartTime(voteDto.getStartTime());
            vote.setEndTime(voteDto.getEndTime());
            vote.setFinished(false);

            // 투표 항목 추가
            for (String content : voteDto.getContents()) {
                VoteContent voteContent = new VoteContent();
                voteContent.setVote(vote);
                voteContent.setContent(content);
                vote.getContents().add(voteContent);
            }
            voteRepository.save(vote);
            return true;
        } catch (Exception e) {
            log.error("Error creating Vote", e);
            return false;
        }
    }

    public boolean deleteVote(Long id) {
        try {
            voteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting Vote", e);
            return false;
        }
    }
}