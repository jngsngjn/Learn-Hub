package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.user.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}