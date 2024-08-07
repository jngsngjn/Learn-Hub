package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.vote.VoteFinishDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.vote.StudentVote;
import project.homelearn.entity.vote.Vote;
import project.homelearn.entity.vote.VoteContent;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.vote.StudentVoteRepository;
import project.homelearn.repository.vote.VoteContentRepository;
import project.homelearn.repository.vote.VoteRepository;

import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentVoteService {

    private final VoteRepository voteRepository;
    private final StudentRepository studentRepository;
    private final VoteContentRepository voteContentRepository;
    private final StudentVoteRepository studentVoteRepository;
    private final CurriculumRepository curriculumRepository;

    public boolean isParticipate(Long voteId, String username) {
        Student student = studentRepository.findByUsername(username);
        return studentVoteRepository.isParticipate(voteId, student);
    }

    public boolean isVoteFinished(Long voteId) {
        return voteRepository.isVoteFinished(voteId);
    }

    public VoteFinishDto getVoteFinishDto(Long voteId, String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByStudent(username);
        Integer total = studentRepository.findStudentCountByCurriculum(curriculum);

        VoteFinishDto result = voteRepository.findVoteFinished(voteId, username);
        result.setTotal(total);
        return result;
    }

    public boolean participateVote(Long voteId, String username, Map<Long, Boolean> voteResult) {
        Vote vote = voteRepository.findById(voteId).orElseThrow();
        if (vote.isFinished()) {
            return false; // 이미 마감된 경우 false 반환
        }

        boolean isMultipleChoice = vote.getIsMultipleChoice();
        long trueCount = voteResult.values().stream().filter(Boolean::booleanValue).count();

        if (trueCount == 0) {
            return false; // 모든 값이 false일 때 false 반환
        }

        if (!isMultipleChoice && trueCount > 1) {
            return false; // 단일 선택 투표에서 true 값이 2개 이상일 때 false 반환
        }

        boolean participate = isParticipate(voteId, username);
        if (participate) {
            return false; // 이미 참여한 경우 false 반환
        }

        Student student = studentRepository.findByUsername(username);
        for (Map.Entry<Long, Boolean> entry : voteResult.entrySet()) {
            Long contentId = entry.getKey();
            Boolean voted = entry.getValue();
            if (voted) {
                VoteContent voteContent = voteContentRepository.findById(contentId).orElseThrow();
                studentVoteRepository.save(new StudentVote(student, voteContent));
                voteContent.setVoteCount(voteContent.getVoteCount() + 1);
            }
        }
        return true;
    }
}