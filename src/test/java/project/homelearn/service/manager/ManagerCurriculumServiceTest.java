package project.homelearn.service.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.CurriculumAddDto;
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

    CurriculumAddDto curriculumAddDto1;
    CurriculumAddDto curriculumAddDto2;

    @BeforeEach
    void before() {
        managerCurriculumService = new ManagerCurriculumService(curriculumRepository);

        curriculumAddDto1 = new CurriculumAddDto();
        curriculumAddDto1.setType(CurriculumType.NCP);
        curriculumAddDto1.setColor("Blue");
        curriculumAddDto1.setStartDate(LocalDate.of(2024, 1, 1));
        curriculumAddDto1.setEndDate(LocalDate.of(2024, 12, 31));

        curriculumAddDto2 = new CurriculumAddDto();
        curriculumAddDto2.setType(CurriculumType.NCP);
        curriculumAddDto2.setColor("Blue");
        curriculumAddDto2.setStartDate(LocalDate.of(2024, 1, 1));
        curriculumAddDto2.setEndDate(LocalDate.of(2024, 12, 31));
    }

    @Test
    void addCurriculum_success() {
        managerCurriculumService.addCurriculum(curriculumAddDto1);
        managerCurriculumService.addCurriculum(curriculumAddDto2);

        List<Curriculum> result = curriculumRepository.findAll();
        Curriculum th1 = result.get(0);
        Curriculum th2 = result.get(1);

        assertThat(th1.getTh()).isEqualTo(1);
        assertThat(th2.getTh()).isEqualTo(2);
    }
}