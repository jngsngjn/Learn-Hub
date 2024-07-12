package project.homelearn.entity.admin;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

@Entity
@DiscriminatorValue("ADMIN")
@Getter @Setter
public class Admin extends User {
}