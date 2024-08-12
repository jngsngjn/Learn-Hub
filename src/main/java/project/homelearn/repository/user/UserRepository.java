package project.homelearn.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.homelearn.entity.inquiry.ManagerInquiry;
import project.homelearn.entity.inquiry.TeacherInquiry;
import project.homelearn.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select u.name from User u where u.curriculum.id = :curriculumId and u.role = project.homelearn.entity.user.Role.ROLE_TEACHER")
    String findTeacherNameByCurriculumId(@Param("curriculumId")Long curriculumId);

    @Query("select count(u) from User u where u.curriculum.id = :curriculumId and u.role = project.homelearn.entity.user.Role.ROLE_STUDENT")
    Integer countTotalStudentsByCurriculumId(@Param("curriculumId")Long curriculumId);

    @Query("select u from User u join u.managerInquiries mi where mi =:inquiry")
    User findUserByManagerInquiry(@Param("inquiry") ManagerInquiry inquiry);

    @Query("select u from User u join u.teacherInquiries ti where ti =:inquiry")
    User findUserByTeacherInquiry(@Param("inquiry") TeacherInquiry inquiry);

    @Query("select u.username from User u where u.email =:email")
    String findUsernameByEmail(@Param("email") String email);

    @Query("select u from User u join fetch u.curriculum where u.username =:username")
    User findUserAndCurriculum(@Param("username") String username);

    boolean existsByEmail(String email);
}