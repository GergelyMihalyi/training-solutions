package covid;

import org.mariadb.jdbc.MariaDbDataSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CovidMain {
    public static final String DB_URL = "jdbc:mariadb://localhost:3306/activitytracker?useUnicode=true";
    public static final String DB_USER = "activitytracker";
    public static final String DB_PASSWORD = "activitytracker";
    Scanner scanner = new Scanner(System.in);
    MariaDbDataSource dataSource = new MariaDbDataSource();

    public static void main(String[] args) {
        CovidMain cm = new CovidMain();
        cm.connectData();
        cm.menuFunction();
    }

    private void connectData() {
        try {
            dataSource.setUrl(DB_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
        } catch (SQLException se) {
            throw new IllegalStateException("Can not create data source", se);
        }
    }

    private void menuFunction() {

        System.out.println("1. Regisztráció\n" +
                "2. Tömeges regisztráció\n" +
                "3. Generálás\n" +
                "4. Oltás\n" +
                "5. Oltás meghiúsulás\n" +
                "6. Riport");

        int menu = scanner.nextInt();
        switch (menu) {
            case 1:
                registration();
                break;
            case 2:
                massRegistration();
                break;
            case 3:
                citizensByZipToFile();
                break;
            case 4:
                vaccination();
                break;
            case 5:
                refused();
                break;
            case 6:
                // code block
                break;
            default:
                menuFunction();
        }
    }


    private void registration() {
        System.out.println("Please enter name: ");
        String name = scanner.nextLine();
        while (!Citizen.isValidName(name)) {
            System.out.println("The name cannot be empty!");
            System.out.println("Please enter name: ");
            name = scanner.nextLine();
        }
        System.out.println("Please enter zip: ");
        String zip = scanner.nextLine();
        while (!Citizen.isValidZip(zip)) {
            System.out.println("The zip cannot be empty!");
            System.out.println("Please enter zip: ");
            zip = scanner.nextLine();
        }

        System.out.println("Please enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        while (!Citizen.isValidAge(age)) {
            System.out.println("The age should be between 10 and 150 years!");
            System.out.println("Please enter age: ");
            age = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Please enter email: ");
        String email = scanner.nextLine();
        while (!Citizen.isValidEmail(email)) {
            System.out.println("The email should be valid!");
            System.out.println("Please enter email: ");
            email = scanner.nextLine();
        }

        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        while (!Citizen.isValidTaj(taj)) {
            System.out.println("The taj number should be valid!");
            System.out.println("Please enter taj: ");
            taj = scanner.nextLine();
        }

        Citizen citizen = new Citizen(name, zip, age, email, taj);
        CitizenDao cd = new CitizenDao(dataSource);
        cd.saveCitizen(citizen);
        System.out.println("The citizen's data saved in vaccination database");
    }


    private void massRegistration() {
        System.out.println("Please enter file path: ");
        Path file = Path.of(scanner.nextLine());
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] arrOfData = line.split(";");
                String name = arrOfData[0];
                String zip = arrOfData[1];
                String email = arrOfData[3];
                String taj = arrOfData[4];
                int age = Integer.parseInt(arrOfData[2]);
                Citizen citizen = new Citizen(name, zip, age, email, taj);
                CitizenDao cd = new CitizenDao(dataSource);
                cd.saveCitizen(citizen);
            }
        } catch (IOException ioe) {
            this.massRegistration();
        }
    }

    private void citizensByZipToFile() {
        CitizenDao cd = new CitizenDao(dataSource);
        System.out.println("Please enter zip: ");
        String zip = scanner.nextLine();
        System.out.println("Please enter file path: ");
        Path file = Path.of(scanner.nextLine());
        List<Citizen> citizens = cd.findAndGroupByZip(zip);
        List<Citizen> firstSixTenCitizens = citizens.stream().limit(16).collect(Collectors.toList());
        LocalTime lt = LocalTime.of(8, 0);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("Időpont;Név;Irányítószám;Életkor;E-mail cím;TAJ szám" + "\n");
            for (Citizen citizen : firstSixTenCitizens) {
                writer.write(lt + ";"
                        + citizen.getName() + ";"
                        + citizen.getZip() + ";"
                        + citizen.getAge() + ";"
                        + citizen.getEmail() + ";"
                        + citizen.getTaj() + ";"
                        + "\n");
                lt = lt.plusMinutes(30);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write file", ioe);
        }
    }

    private void vaccination() {
        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        CitizenDao cd = new CitizenDao(dataSource);
        Citizen citizen = cd.findCitizenByTaj(taj);
        if (citizen.getNumberOfVaccination() == 0) {
            firstVaccination(cd, citizen);
        }else if(citizen.getNumberOfVaccination() == 1){
            secondVaccination(cd, citizen);
        }else if(citizen.getNumberOfVaccination() == 2) {
            System.out.println("This citizen has already been vaccinated twice!");
        }

    }

    private void firstVaccination(CitizenDao cd, Citizen citizen){
        System.out.println("Please select Vaccination type: \n");
        for (int i = 0; i < TypeOfVaccination.values().length; i++) {
            System.out.println(i + ". " + TypeOfVaccination.values()[i].getName() +  "\n");
        }
        int indexOfVaccination = Integer.parseInt(scanner.nextLine());
        TypeOfVaccination vaccine = TypeOfVaccination.values()[indexOfVaccination];
        LocalDateTime lt = LocalDateTime.now();
        Vaccination vaccination = new Vaccination(citizen.getId(),Status.FIRST_VACCINATED,vaccine,lt);
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        vd.saveVaccinatedCitizen(vaccination);
        citizen.setNumberOfVaccination(1);
        citizen.setLastVaccination(lt);
        cd.updateCitizen(citizen);
    }

    private void secondVaccination(CitizenDao cd, Citizen citizen){
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        Vaccination vaccination = vd.findVaccinatedByCitizenId(citizen.getId());
        System.out.println("Vaccinated type: " + vaccination.getTov() + "/n"
                + "Vaccinated date: " + vaccination.getVaccinationDate());
        citizen.setNumberOfVaccination(2);
        LocalDateTime lt = LocalDateTime.now();
        vaccination.setVaccinationDate(lt);
        vaccination.setStatus(Status.VACCINATED);
        citizen.setLastVaccination(lt);
        vd.updateVaccination(vaccination);
        cd.updateCitizen(citizen);
    }

    private void refused() {
        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        CitizenDao cd = new CitizenDao(dataSource);
        Citizen citizen = cd.findCitizenByTaj(taj);
        System.out.println("Please enter the cause of the failure: ");
        String note = scanner.nextLine();

        Vaccination vaccination = new Vaccination(citizen.getId(),Status.FAILED,null,null);
        vaccination.setNote(note);
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        vd.saveVaccinatedCitizen(vaccination);

    }
}
