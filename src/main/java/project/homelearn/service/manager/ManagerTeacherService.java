package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.MangerTeacherDto;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.user.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ManagerTeacherService {

    private final TeacherRepository teacherRepository;

    //필터링 x : 전체 강사 조회
    public Page<MangerTeacherDto> getTeachers(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<MangerTeacherDto> teacherDto = getManagerTeacherDto(teacherPage);

        return new PageImpl<>(teacherDto, pageable, teacherPage.getTotalElements());
    }

    //필터링 o : 교육과정명 기준 강사 조회
    public Page<MangerTeacherDto> getTeachersWithCurriculumName(int size, int page, String curriculumName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findByCurriculumName(pageable, curriculumName);

        List<MangerTeacherDto> teacherDto = getManagerTeacherDto(teacherPage);

        return new PageImpl<>(teacherDto, pageable, teacherPage.getTotalElements());
    }

    //학생 DTO 매핑 메소드
    private static List<MangerTeacherDto> getManagerTeacherDto(Page<Teacher> teacherPage) {
        // 학생 정보를 DTO로 변환
        return teacherPage.stream()
                .map(teacher -> new MangerTeacherDto(
                        teacher.getName(),
                        teacher.getCurriculum().getTh(),
                        teacher.getCurriculum().getName(),
                        teacher.getPhone(),
                        teacher.getEmail()
                ))
                .collect(Collectors.toList());
    }
}
