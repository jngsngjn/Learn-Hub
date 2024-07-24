package project.homelearn.service.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.enroll.CurriculumEnrollDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.CurriculumType;
import project.homelearn.repository.curriculum.CurriculumRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class ManagerCurriculumServiceTest {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private ManagerCurriculumService managerCurriculumService;

    CurriculumEnrollDto curriculumEnrollDto1;
    CurriculumEnrollDto curriculumEnrollDto2;

    @BeforeEach
    void before() {
        managerCurriculumService = new ManagerCurriculumService(curriculumRepository);

        curriculumEnrollDto1 = new CurriculumEnrollDto();
        curriculumEnrollDto1.setType(CurriculumType.NCP);
        curriculumEnrollDto1.setColor("Blue");
        curriculumEnrollDto1.setStartDate(LocalDate.of(2024, 1, 1));
        curriculumEnrollDto1.setEndDate(LocalDate.of(2024, 12, 31));

        curriculumEnrollDto2 = new CurriculumEnrollDto();
        curriculumEnrollDto2.setType(CurriculumType.NCP);
        curriculumEnrollDto2.setColor("Blue");
        curriculumEnrollDto2.setStartDate(LocalDate.of(2024, 1, 1));
        curriculumEnrollDto2.setEndDate(LocalDate.of(2024, 12, 31));
    }

    @Test
    void enrollCurriculum_success() {
        managerCurriculumService.enrollCurriculum(curriculumEnrollDto1);
        managerCurriculumService.enrollCurriculum(curriculumEnrollDto2);

        List<Curriculum> result = curriculumRepository.findAll();
        Curriculum th1 = result.get(0);
        Curriculum th2 = result.get(1);

        assertThat(th1.getTh()).isEqualTo(1);
        assertThat(th2.getTh()).isEqualTo(2);
    }
}