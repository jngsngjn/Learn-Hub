package project.homelearn.service.student;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.student.dashboard.ViewMyBadgeDto;
import project.homelearn.repository.badge.StudentBadgeRepository;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class StudentBadgeService {

    private final StudentBadgeRepository studentBadgeRepository;

    // 최근 뱃지 4개
    public List<ViewMyBadgeDto> getMyBadgesTop4(String username){
        return studentBadgeRepository.findStudentBadgeTop4(username);
    }
}
