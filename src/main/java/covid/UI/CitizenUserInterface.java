package covid.UI;

public class CitizenUserInterface extends UserInterFaceMain {
    public String nameRegistration() {
        printOutUi("Please enter name: ");
        return scanner.nextLine();
    }

    public String nameRegistrationError() {
        printOutUi("The name cannot be empty!");
        return nameRegistration();
    }

    public String zipRegistration() {
        printOutUi("Please enter zip: ");
        return scanner.nextLine();
    }

    public String zipRegistrationError() {
        printOutUi("The zip should be valid!");
        return zipRegistration();
    }

    public int ageRegistration() {
        printOutUi("Please enter age: ");
        return scanner.nextInt();
    }

    public int ageRegistrationError() {
        printOutUi("The age should be between 10 and 150 years!");
        return ageRegistration();
    }

    public void validZip(String zip) {
        printOutUi(zip);
    }

    public void registrationSuccess() {
        printOutUi("The citizen's data saved in vaccination database");
    }

    public String emailRegistration() {
        printOutUi("Please enter email: ");
        return scanner.nextLine();
    }

    public String emailRegistrationError() {
        printOutUi("The email should be valid!");
        return emailRegistration();
    }

    public String secondEmailRegistration() {
        printOutUi("Please enter email again: ");
        return scanner.nextLine();
    }

    public String secondEmailRegistrationError() {
        printOutUi("Email addresses must match!");
        return secondEmailRegistration();
    }

    public String tajRegistration() {
        printOutUi("Please enter taj: ");
        return scanner.nextLine();
    }

    public String tajRegistrationError() {
        printOutUi("The taj number should be valid!");
        return emailRegistration();
    }

}
