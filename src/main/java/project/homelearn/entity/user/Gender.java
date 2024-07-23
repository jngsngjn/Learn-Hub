package project.homelearn.entity.user;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남"), FEMALE("여");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}