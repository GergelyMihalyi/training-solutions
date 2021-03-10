package covid.Validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CitizenValidator {
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    public boolean isValidName(String name) {
        return !name.isEmpty();
    }

    public String validZip(String zip) {
        CityValidator cityValidator = new CityValidator();
        return cityValidator.searchValidCityByZip(zip);
    }

    public boolean isValidAge(int age) {
        return age > 10 && age < 150;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidSecondEmail(String email, String secondEmail) {
        return secondEmail.equals(email);
    }

    public boolean isValidTaj(String taj) {
        if (taj.isEmpty() || taj.isBlank()) {
            return false;
        }
        String tajWithoutWhiteSpace = taj.replaceAll("\\s+", "");

        if (tajWithoutWhiteSpace.length() < 9) {
            return false;
        }

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
}
