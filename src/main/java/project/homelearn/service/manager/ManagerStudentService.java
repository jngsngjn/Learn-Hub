package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.entity.student.Student;
import project.homelearn.entity.user.LoginHistory;
import project.homelearn.repository.user.LoginHistoryRepository;
import project.homelearn.repository.user.StudentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ManagerStudentService {

    private final StudentRepository studentRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    //필터링 x : 전체 학생 조회
    public Page<ManagerStudentDto> getStudents(int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<LoginHistory> todayLoginHistory = getTodayLoginHistory();
        List<ManagerStudentDto> studentDto = getManagerStudentDto(studentPage, todayLoginHistory);

        return new PageImpl<>(studentDto, pageable, studentPage.getTotalElements());
    }

    //필터링 o : 교육과정명 기준 학생 조회
    public Page<ManagerStudentDto> getStudentsWithCurriculumName(int size, int page, String curriculumName){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findByCurriculumName(pageable, curriculumName);

        List<LoginHistory> todayLoginHistory = getTodayLoginHistory();
        List<ManagerStudentDto> studentDto = getManagerStudentDto(studentPage, todayLoginHistory);

        return new PageImpl<>(studentDto, pageable, studentPage.getTotalElements());
    }


    //필터링 o : 기수 + 교육과정명 기준 학생 조회
    public Page<ManagerStudentDto> getStudentsWithCurriculumNameAndCurriculumTh(int size, int page, String curriculumName, Long curriculumTh){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findByCurriculumThAndCurriculumName(pageable, curriculumName, curriculumTh);

        List<LoginHistory> todayLoginHistory = getTodayLoginHistory();
        List<ManagerStudentDto> studentDto = getManagerStudentDto(studentPage, todayLoginHistory);

        return new PageImpl<>(studentDto, pageable, studentPage.getTotalElements());
    }

    //학생 DTO 매핑 메소드
    private static List<ManagerStudentDto> getManagerStudentDto(Page<Student> studentPage, List<LoginHistory> todayLoginHistory) {
        // 오늘 로그인한 학생 ID 목록을 집합으로 생성
        Set<Long> studentIdsWithLoginToday = todayLoginHistory.stream()
                .map(loginHistory -> loginHistory.getUser().getId())
                .collect(Collectors.toSet());

        // 학생 정보를 DTO로 변환
        return studentPage.stream()
                .map(student -> new ManagerStudentDto(
                        student.getName(),
                        student.getCurriculum().getTh(),
                        student.getCurriculum().getName(),
                        student.getPhone(),
                        student.getEmail(),
                        studentIdsWithLoginToday.contains(student.getId())  // 출석 여부
                ))
                .collect(Collectors.toList());
    }

    //로그인 기록이 오늘이랑 일치하는지 판단
    private  List<LoginHistory> getTodayLoginHistory() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        return loginHistoryRepository.findByLoginDateTimeBetween(startOfDay, endOfDay);
    }
}
