package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.common.SubjectIdAndNameDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.curriculum.SubjectRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SidebarService {

    private final SubjectRepository subjectRepository;
    private final CurriculumRepository curriculumRepository;

    public List<SubjectIdAndNameDto> getSubjectIdAndName(String username) {
        Curriculum curriculum = curriculumRepository.findCurriculumByUsername(username);
        return subjectRepository.findSubjectIdsAndNames(curriculum);
    }
}