package project.homelearn.entity.manager;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import project.homelearn.entity.user.User;

@Entity
@DiscriminatorValue("MANAGER")
@Getter @Setter
public class Manager extends User {
}