package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.user.EmailCode;

public interface EmailCodeRepository extends JpaRepository<EmailCode, Long> {
    EmailCode findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}