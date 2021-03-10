package covid.UI;

import covid.Controllers.CitizenFileOperation;
import covid.Controllers.CitizenVaccination;
import covid.Controllers.Registration;

import java.util.Scanner;

public class Menu {

    public Menu() {
        menuFunction();
    }

    private void menuFunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "1. Regisztráció\n" +
                "2. Tömeges regisztráció\n" +
                "3. Generálás\n" +
                "4. Oltás\n" +
                "5. Oltás meghiúsulás\n" +
                "6. Riport");

        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number!");
            scanner.next();
        }
        int menu = scanner.nextInt();
        switch (menu) {
            case 1:
                new Registration().citizenRegistration();
                break;
            case 2:
                new CitizenFileOperation().massRegistration();
                break;
            case 3:
                new CitizenFileOperation().citizensByZipToFile();
                break;
            case 4:
                new CitizenVaccination().vaccination();
                break;
            case 5:
                new CitizenVaccination().refused();
                break;
            case 6:
                new CitizenFileOperation().report();
                break;
            default:
                menuFunction();
        }
    }
}
