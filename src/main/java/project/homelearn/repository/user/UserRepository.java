package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select u.name from User u where u.curriculum.id = :curriculumId and u.role = project.homelearn.entity.user.Role.ROLE_TEACHER")
    String findTeacherNameByCurriculumId(@Param("curriculumId")Long curriculumId);

    @Query("select count(u) from User u where u.curriculum.id = :curriculumId and u.role = project.homelearn.entity.user.Role.ROLE_STUDENT")
    Integer countTotalStudentsByCurriculumId(@Param("curriculumId")Long curriculumId);
}