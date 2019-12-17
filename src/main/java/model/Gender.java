package model;

import java.util.Arrays;

public enum Gender {
    FEMALE("Female"),
    MALE("Male");

    private String gender;

    public static Gender getGender(String name) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.gender.equals(name)).findFirst()
                .get();
    }

    Gender(String gender) {
        this.gender = gender;
    }
}
