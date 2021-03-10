package covid.Controllers;

import covid.Dao.CitizenDao;
import covid.Models.Citizen;
import covid.UI.CitizenUserInterface;
import covid.Validators.CitizenValidator;


public class Registration {
    CitizenUserInterface rui;
    CitizenValidator cv;

    public Registration() {
        rui = new CitizenUserInterface();
        cv = new CitizenValidator();
    }

    public void citizenRegistration() {
        String name = nameRegistration();
        String zip = zipRegistration();
        int age = ageRegistration();
        String email = emailRegistration();
        secondEmail(email);
        String taj = tajRegistration();
        saveCitizenToVaccination(name, zip, age, email, taj);
        rui.registrationSuccess();
    }

    private void saveCitizenToVaccination(String name, String zip, int age, String email, String taj) {
        Citizen citizen = new Citizen(name, zip, age, email, taj);
        CitizenDao cd = new CitizenDao();
        cd.saveCitizen(citizen);
    }

    private String nameRegistration() {
        String name = rui.nameRegistration();
        while (!cv.isValidName(name)) {
            name = rui.nameRegistrationError();
        }
        return name;
    }

    private String zipRegistration() {
        String zip = rui.zipRegistration();
        while (cv.validZip(zip) == null) {
            zip = rui.zipRegistrationError();
        }
        rui.validZip(cv.validZip(zip));
        return zip;
    }

    private int ageRegistration() {
        int age = rui.ageRegistration();
        while (!cv.isValidAge(age)) {
            age = rui.ageRegistrationError();
        }
        return age;
    }

    private String emailRegistration() {
        String email = rui.emailRegistration();
        while (!cv.isValidEmail(email)) {
            email = rui.emailRegistrationError();
        }
        return email;
    }

    private void secondEmail(String email) {
        String secondEmail = rui.secondEmailRegistration();
        while (!cv.isValidSecondEmail(email,secondEmail)) {
            secondEmail = rui.secondEmailRegistrationError();
        }
    }

    private String tajRegistration() {
        String taj = rui.tajRegistration();
        while (!cv.isValidTaj(taj)) {
            taj = rui.tajRegistrationError();
        }
        return taj;
    }


}
