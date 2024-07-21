package project.homelearn.entity.student;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

@Entity
@DiscriminatorValue("STUDENT")
@Getter @Setter
public class Student extends User {


}