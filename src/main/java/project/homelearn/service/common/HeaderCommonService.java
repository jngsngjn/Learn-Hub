package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.homelearn.dto.common.HeaderCommonDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class HeaderCommonService {

    private final UserRepository userRepository;

    public HeaderCommonDto getHeaderCommon(String username) {
        User user = userRepository.findUserAndCurriculum(username);
        Curriculum curriculum = user.getCurriculum();

        LocalDate startDate = curriculum.getStartDate();
        LocalDate endDate = curriculum.getEndDate();
        LocalDate currentDate = LocalDate.now();

        double progressRate = calculateProgressRate(startDate, endDate, currentDate);

        return HeaderCommonDto
                .builder()
                .curriculumFullName(curriculum.getFullName())
                .name(user.getName())
                .imagePath(user.getImagePath())
                .progressRate(progressRate)
                .build();
    }

    private double calculateProgressRate(LocalDate startDate, LocalDate endDate, LocalDate currentDate) {
        if (currentDate.isBefore(startDate)) {
            return 0.0;
        } else if (currentDate.isAfter(endDate)) {
            return 100.0;
        } else {
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);
            double progress = (double) daysPassed / totalDays * 100;
            return Math.round(progress * 10) / 10.0;
        }
    }
}