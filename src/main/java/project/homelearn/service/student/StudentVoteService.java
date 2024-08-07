package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.StudentRepository;
import project.homelearn.repository.vote.StudentVoteRepository;
import project.homelearn.repository.vote.VoteContentRepository;
import project.homelearn.repository.vote.VoteRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StudentVoteService {

    private final VoteRepository voteRepository;
    private final StudentRepository studentRepository;
    private final VoteContentRepository voteContentRepository;
    private final StudentVoteRepository studentVoteRepository;

    public boolean isParticipate(Long voteId, String username) {
        Student student = studentRepository.findByUsername(username);
        return studentVoteRepository.existsByUser(voteId, student);
    }

    public boolean isVoteFinished(Long voteId) {
        return voteRepository.isVoteFinished(voteId);
    }
}