package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.user.EnrollList;

public interface EnrollListRepository extends JpaRepository<EnrollList, Long> {
    EnrollList findByEmail(String email);

    void deleteByEmail(String email);
}