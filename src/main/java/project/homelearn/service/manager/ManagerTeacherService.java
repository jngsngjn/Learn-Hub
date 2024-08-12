package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.enroll.TeacherEnrollDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumWithoutTeacherDto;
import project.homelearn.dto.manager.manage.teacher.ManagerTeacherDto;
import project.homelearn.dto.manager.manage.teacher.SpecificTeacherDto;
import project.homelearn.dto.manager.manage.teacher.TeacherUpdateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.EnrollListRepository;
import project.homelearn.repository.user.TeacherRepository;
import project.homelearn.service.common.EmailService;

import java.util.List;
import java.util.stream.Collectors;

import static project.homelearn.config.common.MailType.ENROLL;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerTeacherService {

    private final EmailService emailService;
    private final TeacherRepository teacherRepository;
    private final CurriculumRepository curriculumRepository;
    private final EnrollListRepository enrollListRepository;

    /**
     * 강사조회
     * Author : 김승민
     * */
    // 필터링 x : 전체 강사 조회
    public Page<ManagerTeacherDto> getTeachers(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<ManagerTeacherDto> teacherDto = getManagerTeacherDto(teacherPage);

        return new PageImpl<>(teacherDto, pageable, teacherPage.getTotalElements());
    }

    // 필터링 o : 교육과정명 기준 강사 조회
    public Page<ManagerTeacherDto> getTeachersWithCurriculumName(int size, int page, String curriculumName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findByCurriculumName(pageable, curriculumName);

        List<ManagerTeacherDto> teacherDto = getManagerTeacherDto(teacherPage);

        return new PageImpl<>(teacherDto, pageable, teacherPage.getTotalElements());
    }

    // 배정안되어 있는 강사들만 조회
    public Page<ManagerTeacherDto> getTeachersCurriculumIsNull(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findByCurriculumIdIsNull(pageable);

        List<ManagerTeacherDto> teacherDto = getManagerTeacherDto(teacherPage);

        return new PageImpl<>(teacherDto, pageable, teacherPage.getTotalElements());
    }

    // 학생 DTO 매핑 메소드
    private static List<ManagerTeacherDto> getManagerTeacherDto(Page<Teacher> teacherPage) {
        // 학생 정보를 DTO로 변환
        return teacherPage.stream()
                .map(teacher -> new ManagerTeacherDto(
                        teacher.getId(),
                        teacher.getName(),
                        teacher.getCurriculum() != null ? teacher.getCurriculum().getTh() : null,
                        teacher.getCurriculum() != null ? teacher.getCurriculum().getName() : null,
                        teacher.getPhone(),
                        teacher.getEmail()
                ))
                .collect(Collectors.toList());
    }

    /**
     * 강사 등록
     * Author : 정성진
     */
    public boolean enrollTeacher(TeacherEnrollDto teacherEnrollDto) {
        Curriculum curriculum = curriculumRepository.findByFullName(teacherEnrollDto.getCurriculumFullName());
        if (curriculum == null) {
            log.error("curriculum is null.");
            return false;
        }

        boolean exists = teacherRepository.existsByCurriculum(curriculum);
        if (exists) {
            log.error("커리큘럼에 이미 배정된 강사가 있습니다.");
            return false;
        }

        String email = teacherEnrollDto.getEmail();
        boolean existsByEmail = teacherRepository.existsByEmail(email);
        if (existsByEmail) {
            return false;
        }

        String code = emailService.sendCode(email, ENROLL);
        if (code == null) {
            return false;
        }

        EnrollList enrollList = new EnrollList();
        enrollList.setName(teacherEnrollDto.getName());
        enrollList.setEmail(email);
        enrollList.setCode(code);
        enrollList.setCurriculum(curriculum);
        enrollList.setPhone(teacherEnrollDto.getPhone());
        enrollListRepository.save(enrollList);
        return true;
    }

    /**
     * 강사 수정
     * Author : 정성진
     */
    public boolean updateTeacher(Long teacherId, TeacherUpdateDto teacherUpdateDto) {
        try {
            Curriculum curriculum = curriculumRepository.findById(teacherUpdateDto.getCurriculumId()).orElseThrow();

            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            teacher.setName(teacherUpdateDto.getName());
            teacher.setEmail(teacherUpdateDto.getEmail());
            teacher.setPhone(teacherUpdateDto.getPhone());
            teacher.setCurriculum(curriculum);
            return true;
        } catch (Exception e) {
            log.error("Error update teacher", e);
            return false;
        }
    }

    /**
     * 강사 1명 삭제
     * Author : 정성진
     */
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    /**
     * 강사 여러 명 삭제
     * Author : 정성진
     */
    public void deleteTeachers(List<Long> ids) {
        teacherRepository.deleteAllById(ids);
    }

    /**
     * 강사 상세 페이지
     * 1. 일반 정보 ✅ getTeacherBasic()
     * 2. 강사가 배정되지 않은 교육 과정 ✅ getCurriculumWithoutTeacher()
     */
    public SpecificTeacherDto getTeacherBasic(Long teacherId) {
        return teacherRepository.findSpecificTeacher(teacherId);
    }

    public List<CurriculumWithoutTeacherDto> getCurriculumWithoutTeacher() {
        return curriculumRepository.findCurriculumWithoutTeacher();
    }
}