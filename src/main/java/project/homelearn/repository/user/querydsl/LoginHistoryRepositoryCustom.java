package project.homelearn.repository.user.querydsl;

import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

public interface LoginHistoryRepositoryCustom {
    LocalDateTime findUserLoginDateTime(User user);

    long countConsecutiveLoginDatesByUser(User user);
}