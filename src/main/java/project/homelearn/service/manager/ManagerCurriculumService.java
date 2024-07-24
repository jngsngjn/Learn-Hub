package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.dto.manager.manage.curriculum.CurriculumUpdateDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.teacher.Teacher;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.ManagerRepository;
import project.homelearn.repository.user.TeacherRepository;

import java.util.NoSuchElementException;

import static project.homelearn.entity.curriculum.CurriculumType.AWS;
import static project.homelearn.entity.curriculum.CurriculumType.NCP;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCurriculumService {

    private final ManagerRepository managerRepository;
    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CurriculumRepository curriculumRepository;

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
}