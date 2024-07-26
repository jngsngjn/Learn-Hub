package project.homelearn.repository.user;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.homelearn.entity.student.Attendance;
import project.homelearn.entity.user.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("select count(a) > 0 from Attendance a where a.user =:user and a.date = current_date")
    boolean existsByUser(@Param("user") User user);
}