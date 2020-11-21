package stringbasic.pets;

import java.time.LocalDate;

public class Pet {
    private String name;
    private LocalDate birth;
    private Gender gender;
    private String registrationNumber;

    public Pet(String name, LocalDate birth, Gender gender, String registrationNumber) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Gender getGender() {
        return gender;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
}
