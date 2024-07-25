package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.manager.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT m.password FROM Manager m WHERE m.username = :username")
    String findPasswordByUsername(@Param("username") String username);
}