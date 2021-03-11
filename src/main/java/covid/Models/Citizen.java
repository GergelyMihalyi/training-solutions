package covid.Models;

import java.time.LocalDateTime;

public class Citizen {
    private long id;
    private String name;
    private String zip;
    private int age;
    private String email;
    private String taj;
    private LocalDateTime lastVaccination;
    private int numberOfVaccination;

    public Citizen(String name, String zip, int age, String email, String taj) {
            this.name = name;
            this.zip = zip;
            this.age = age;
            this.email = email;
            this.taj = taj;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZip() {
        return zip;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTaj() {
        return taj;
    }

    public LocalDateTime getLastVaccination() {
        return lastVaccination;
    }

    public int getNumberOfVaccination() {
        return numberOfVaccination;
    }

    public void setLastVaccination(LocalDateTime lastVaccination) {
        this.lastVaccination = lastVaccination;
    }

    public void setNumberOfVaccination(int numberOfVaccination) {
        this.numberOfVaccination = numberOfVaccination;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zip='" + zip + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", taj='" + taj + '\'' +
                ", lastVaccination=" + lastVaccination +
                ", numberOfVaccination=" + numberOfVaccination +
                '}';
    }
}
