package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.vote.VoteBasicDto;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.vote.VoteContent;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.vote.VoteContentRepository;
import project.homelearn.repository.vote.VoteRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherVoteService {

    private final VoteRepository voteRepository;
    private final CurriculumRepository curriculumRepository;
    private final VoteContentRepository voteContentRepository;

    public Page<VoteTabDto> getProgressVotes(String username, int page, int size, String status) {
        Curriculum curriculum = curriculumRepository.findCurriculumByTeacher(username);
        PageRequest pageRequest = PageRequest.of(page, size);

        return voteRepository.findVoteTab(curriculum, pageRequest, status);
    }

    public Page<VoteTabDto> getCompletedVotes(String username, int page, int size, String status) {
        Curriculum curriculum = curriculumRepository.findCurriculumByTeacher(username);
        PageRequest pageRequest = PageRequest.of(page, size);

        return voteRepository.findVoteTab(curriculum, pageRequest, status);
    }

    public VoteBasicDto getVoteBasic(Long voteId, String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByTeacher(username);
        return voteRepository.findVoteBasic(voteId, curriculum);
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