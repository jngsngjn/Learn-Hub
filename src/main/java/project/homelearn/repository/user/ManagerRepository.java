package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.manager.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}