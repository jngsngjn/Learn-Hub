package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}