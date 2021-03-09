package covid;

import org.mariadb.jdbc.MariaDbDataSource;

import java.util.Scanner;

public class Registration {
    Scanner scanner = new Scanner(System.in);

    public void citizenRegistration() {
        String name = nameRegistration();
        String zip = zipRegistration();
        int age = ageRegistration();
        String email = emailRegistration();
        secondEmail(email);
        String taj = tajRegistration();
        saveCitizenToVaccination(name, zip, age, email, taj);
        System.out.println("The citizen's data saved in vaccination database");
    }

    private void saveCitizenToVaccination(String name, String zip, int age, String email, String taj) {
        MariaDbDataSource dataSource = new DatabaseConnection().dataSource;
        Citizen citizen = new Citizen(name, zip, age, email, taj);
        CitizenDao cd = new CitizenDao(dataSource);
        cd.saveCitizen(citizen);
    }

    private String nameRegistration() {
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        while (!Citizen.isValidName(name)) {
            System.out.println("The name cannot be empty!");
            System.out.println("Please enter name: ");
            name = scanner.nextLine();
        }
        return name;
    }

    private String zipRegistration() {
        System.out.println("Please enter zip: ");
        String zip = scanner.nextLine();
        while (Citizen.validZip(zip) == null) {
            System.out.println("The zip should be valid!");
            System.out.println("Please enter zip: ");
            zip = scanner.nextLine();
        }
        System.out.println(Citizen.validZip(zip));
        return zip;
    }

    private int ageRegistration() {
        System.out.println("Please enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        while (!Citizen.isValidAge(age)) {
            System.out.println("The age should be between 10 and 150 years!");
            System.out.println("Please enter age: ");
            age = Integer.parseInt(scanner.nextLine());
        }
        return age;
    }

    private String emailRegistration() {
        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        while (!Citizen.isValidEmail(email)) {
            System.out.println("The email should be valid!");
            System.out.println("Please enter email: ");
            email = scanner.nextLine();
        }
        return email;
    }

    private void secondEmail(String email) {
        System.out.println("Please enter email again: ");
        String secondEmail = scanner.nextLine();
        while (!secondEmail.equals(email)) {
            System.out.println("Email addresses must match");
            System.out.println("Please enter email: ");
            secondEmail = scanner.nextLine();
        }
    }

    private String tajRegistration() {
        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        while (!Citizen.isValidTaj(taj)) {
            System.out.println("The taj number should be valid!");
            System.out.println("Please enter taj: ");
            taj = scanner.nextLine();
        }
        return taj;
    }


}
