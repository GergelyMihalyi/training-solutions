package covid;

import org.mariadb.jdbc.MariaDbDataSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CitizenFileOperation {

    public void citizensByZipToFile() {
        MariaDbDataSource dataSource = new DatabaseConnection().dataSource;
        CitizenDao cd = new CitizenDao(dataSource);
        Scanner scanner = new Scanner(System.in);
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

    public void massRegistration() {
        MariaDbDataSource dataSource = new DatabaseConnection().dataSource;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter file path: ");
        Path file = Path.of(scanner.nextLine());
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfData = line.split(";");
                String name = arrayOfData[0];
                String zip = arrayOfData[1];
                String email = arrayOfData[3];
                String taj = arrayOfData[4];
                int age = Integer.parseInt(arrayOfData[2]);
                Citizen citizen = new Citizen(name, zip, age, email, taj);
                CitizenDao cd = new CitizenDao(dataSource);
                cd.saveCitizen(citizen);
            }
        } catch (IOException ioe) {
            this.massRegistration();
        }
    }

    public void report() {
        MariaDbDataSource dataSource = new DatabaseConnection().dataSource;
        CitizenDao cd = new CitizenDao(dataSource);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter file path: ");
        Path file = Path.of(scanner.nextLine());
        List<String> report = cd.reportGroupByZip();
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write("Időpont;Név;Irányítószám;Életkor;E-mail cím;TAJ szám" + "\n");
            for (String row : report) {
                writer.write(row +  "\n");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write file", ioe);
        }
    }

}
