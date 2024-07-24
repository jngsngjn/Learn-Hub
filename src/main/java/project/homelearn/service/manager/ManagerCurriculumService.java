package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.entity.user.User;
import project.homelearn.repository.curriculum.CurriculumRepository;
import project.homelearn.repository.user.UserRepository;

import static project.homelearn.entity.curriculum.CurriculumType.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ManagerCurriculumService {

    private final UserRepository userRepository;
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
            User user = userRepository.findById(teacherId).orElseThrow();
            user.setCurriculum(curriculum);
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
}