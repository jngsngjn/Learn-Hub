package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.user.LoginHistory;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.querydsl.LoginHistoryRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long>, LoginHistoryRepositoryCustom {

    List<LoginHistory> findByLoginDateTimeBetween(LocalDateTime start, LocalDateTime end);

    boolean existsByUser(User user);

    @Query("SELECT COUNT(lh) > 0 FROM LoginHistory lh WHERE lh.user = :user AND DATE(lh.loginDateTime) = CURRENT_DATE")
    boolean existsByUserAndLoginDateTimeIsToday(@Param("user") User user);
}