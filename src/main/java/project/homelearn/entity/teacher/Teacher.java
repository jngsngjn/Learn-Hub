package project.homelearn.entity.teacher;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

@Entity
@DiscriminatorValue("TEACHER")
@Getter @Setter
public class Teacher extends User {

}