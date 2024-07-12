package project.homelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.homelearn.entity.admin.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}