package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.vote.VoteDetailDto;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.vote.VoteRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VoteCommonService {

    private final VoteRepository voteRepository;
    private final CurriculumRepository curriculumRepository;

    public Page<VoteTabDto> getProgressVotes(String username, int page, int size, String status) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        PageRequest pageRequest = PageRequest.of(page, size);

        return voteRepository.findVoteTab(curriculum, pageRequest, status);
    }

    public Page<VoteTabDto> getCompletedVotes(String username, int page, int size, String status) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        PageRequest pageRequest = PageRequest.of(page, size);

        return voteRepository.findVoteTab(curriculum, pageRequest, status);
    }

    public VoteDetailDto getVoteDetail(Long voteId) {
        boolean isAnonymous = voteRepository.isAnonymousVote(voteId);
        if (isAnonymous) {
            return null;
        }
        return voteRepository.findVoteDetail(voteId);
    }
}