package covid.UI;

import covid.Controllers.CitizenFileOperation;
import covid.Controllers.CitizenVaccination;
import covid.Controllers.Registration;

public class Menu extends UserInterFaceMain {

    public Menu() {
        menuUi();
    }

    private void menuUi() {
        printOutUi( "1. Regisztráció\n" +
                            "2. Tömeges regisztráció\n" +
                            "3. Generálás\n" +
                            "4. Oltás\n" +
                            "5. Oltás meghiúsulás\n" +
                            "6. Riport");

        while (!scanner.hasNextInt()) {
            printOutUi("That's not a number!\nPlease enter valid menu number:");
            scanner.next();
        }
        menuFunction(scanner.nextInt());
    }

    private void menuFunction(int menu) {
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
                printOutUi("Please enter valid menu number:");
                menuUi();
        }
    }
}
