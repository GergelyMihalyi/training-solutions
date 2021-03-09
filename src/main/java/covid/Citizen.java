package covid;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Citizen {
    private long id;
    private String name;
    private String zip;
    private int age;
    private String email;
    private String taj;
    private LocalDateTime lastVaccination;
    private int numberOfVaccination;
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    public Citizen(String name, String zip, int age, String email, String taj) {
        if (isValidName(name) && isValidZip(zip) && isValidAge(age) && isValidEmail(email) && isValidTaj(taj)) {
            this.name = name;
            this.zip = zip;
            this.age = age;
            this.email = email;
            this.taj = taj;

        }

    }

    public static boolean isValidName(String name) {
        return !name.isEmpty();
    }

    public static boolean isValidZip(String zip) {
        return !zip.isEmpty();
    }

    public static boolean isValidAge(int age) {
        return age > 10 && age < 150;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidTaj(String taj) {
        if (taj.isEmpty() || taj.isBlank()) {
            return false;
        }
        String tajWithoutWhiteSpace = taj.replaceAll("\\s+", "");
        char[] ch = new char[tajWithoutWhiteSpace.length()];
        int assistNumber;
        int sumAssist = 0;
        for (int i = 0; i < 7; i++) {
            if (i % 2 == 0) {
                assistNumber = (int) ch[i] * 7;
            } else {
                assistNumber = (int) ch[i] * 3;
            }
            sumAssist += assistNumber;
        }

        return sumAssist % 10 == ch[8];
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
