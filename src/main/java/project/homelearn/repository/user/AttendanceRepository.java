package project.homelearn.repository.user;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.querydsl.AttendanceRepositoryCustom;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {

    @Query("select count(a) > 0 from Attendance a where a.user =:user and a.date = current_date")
    boolean existsByUser(@Param("user") User user);

    @Query("select count(a) from Attendance a where a.user.id =:studentId and a.type in (project.homelearn.entity.student.AttendanceType.ATTENDANCE, project.homelearn.entity.student.AttendanceType.LATE )")
    int findByStudentId(@Param("studentId") Long studentId);

    @Query("select a from Attendance a where a.user.id = :userId")
    List<Attendance> findByUserId(@Param("userId") Long userId);
}