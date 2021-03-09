package covid;

import java.util.Scanner;

public class CovidMain {

    public static void main(String[] args) {
        CovidMain cm = new CovidMain();
        cm.menuFunction();
    }

    private void menuFunction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Regisztráció\n" +
                "2. Tömeges regisztráció\n" +
                "3. Generálás\n" +
                "4. Oltás\n" +
                "5. Oltás meghiúsulás\n" +
                "6. Riport");

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
