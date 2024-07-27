package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.dashboard.CurriculumDto;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.dto.manager.manage.curriculum.*;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.survey.Survey;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.survey.SurveyRepository;
import project.homelearn.repository.user.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static project.homelearn.entity.curriculum.CurriculumType.AWS;
import static project.homelearn.entity.curriculum.CurriculumType.NCP;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCurriculumService {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ManagerRepository managerRepository;

    private final CurriculumRepository curriculumRepository;
    private final AttendanceRepository attendanceRepository;

    public boolean enrollCurriculum(CurriculumEnrollDto curriculumEnrollDto) {
        try {
            CurriculumType type = curriculumEnrollDto.getType();
            Long count = curriculumRepository.findCountByType(type);

            Curriculum curriculum = createCurriculum(curriculumEnrollDto, count, type);

            curriculumRepository.save(curriculum);
            return true;

        } catch (Exception e) {
            log.error("Error adding curriculum", e);
            return false;
        }
    }

    private Curriculum createCurriculum(CurriculumEnrollDto curriculumEnrollDto, Long count, CurriculumType type) {
        Long th = count + 1;

        Curriculum curriculum = new Curriculum();
        curriculum.setTh(th);
        curriculum.setColor(curriculumEnrollDto.getColor());
        curriculum.setStartDate(curriculumEnrollDto.getStartDate());
        curriculum.setEndDate(curriculumEnrollDto.getEndDate());
        curriculum.setType(type);

        Long teacherId = curriculumEnrollDto.getTeacherId();
        if (teacherId != null) {
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            teacher.setCurriculum(curriculum);
        }

        if (type.equals(NCP)) {
            String ncp = NCP.getDescription();
            curriculum.setName(ncp);
            curriculum.setFullName(ncp + " " + th + "기");
        } else {
            String aws = AWS.getDescription();
            curriculum.setName(aws);
            curriculum.setFullName(aws + " " + th + "기");
        }
        return curriculum;
    }

    public boolean updateCurriculum(Long id, CurriculumUpdateDto curriculumUpdateDto) {
        try {
            Curriculum curriculum = curriculumRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Curriculum not found with id: " + id));

            Long teacherId = curriculumUpdateDto.getTeacherId();
            if (teacherId != null) {
                Teacher teacher = teacherRepository.findById(teacherId)
                        .orElseThrow(() -> new NoSuchElementException("Teacher not found with id: " + teacherId));
                teacher.setCurriculum(curriculum);
            }

            curriculum.setColor(curriculumUpdateDto.getColor());
            curriculum.setStartDate(curriculumUpdateDto.getStartDate());
            curriculum.setEndDate(curriculumUpdateDto.getEndDate());

            return true;

        } catch (NoSuchElementException e) {
            log.error("Entity not found: ", e);
            return false;
        } catch (Exception e) {
            log.error("Error updating curriculum: ", e);
            return false;
        }
    }

    public boolean checkPassword(String username, String rawPassword) {
        String encodedPassword = managerRepository.findPasswordByUsername(username);
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void deleteCurriculum(Long id) {
        curriculumRepository.deleteById(id);
    }

    /**
     * 교육 과정 만족도 설문 시작
     * Author : 정성진
     */
    public boolean startSurveyProcess(Long id) {
        try {
            Curriculum curriculum = curriculumRepository
                    .findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Curriculum not found with id: " + id));

            Long curriculumId = curriculum.getId();
            boolean existActiveSurvey = surveyRepository.existActiveSurvey(id);
            if (existActiveSurvey) { // 이미 진행 중인 설문이 있을 때
                return false;
            }

            int result = surveyRepository.findSurveyCount(curriculumId) + 1;

            Survey survey = new Survey();

            // 네이버 클라우드 데브옵스 과정 만족도 설문 조사 1회
            survey.setTitle(curriculum.getName() + " 만족도 설문 조사 " + result + "차");
            survey.setCurriculum(curriculum);
            surveyRepository.save(survey);

            studentRepository.updateSurveyCompletedFalse(curriculumId);
            return true;
        } catch (Exception e) {
            log.error("Error starting survey: ", e);
            return false;
        }
    }

    /**
     * 교육 과정 만족도 설문 마감
     * Author : 정성진
     */
    public boolean stopSurveyProcess(Long id) {
        if (id == null) {
            return false;
        }
        surveyRepository.updateSurveyIsFinishedTrue(id);
        return true;
    }

    public CurriculumTeacherDto getCurriculumTeacherInfo(Long id) {
        return teacherRepository.findByCurriculumId(id);
    }

    public CurriculumProgressDto getCurriculumProgress(Long curriculumId) {
        CurriculumBasicDto basicDto = curriculumRepository.findCurriculumBasic(curriculumId);
        Double progress = basicDto.calculateProgress();

        return new CurriculumProgressDto(basicDto.getName(), basicDto.getTh(), progress);
    }

    public CurriculumSurveyDto getCurriculumSurvey(Long curriculumId) {
        return surveyRepository.findCurriculumSurvey(curriculumId);
    }

    /**
     * 대시보드 교육과정 section
     * Author : 김승민
     * 0. NCP/AWS 따로 추출 ✅
     * 1. 교육과정이름 + 기수 ✅
     * 2. 강사명 ✅
     * 3. 학생총원 ✅
     * 4. 출석한 학생인원 ✅
     * */
    public List<CurriculumDto> getCurriculumList(CurriculumType type) {
        List<Curriculum> curriculums = curriculumRepository.findByCurriculumType(type);
        LocalDateTime now = LocalDateTime.now();

        return curriculums.stream()
                .map(curriculum -> {
                    CurriculumDto curriculumDto = new CurriculumDto();
                    curriculumDto.setId(curriculum.getId());
                    curriculumDto.setName(curriculum.getName());
                    curriculumDto.setTh(curriculum.getTh());
                    curriculumDto.setTeacherName(userRepository.findTeacherNameByCurriculumId(curriculum.getId()));
                    curriculumDto.setAttendance(attendanceRepository.countAttendanceByCurriculumId(curriculum.getId(), now.toLocalDate()));
                    curriculumDto.setTotal(userRepository.countTotalStudentsByCurriculumId(curriculum.getId()));

                    return curriculumDto;
                })
                .collect(Collectors.toList());
    }
}