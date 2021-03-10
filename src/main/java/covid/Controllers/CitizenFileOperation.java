package covid.Controllers;

import covid.Dao.CitizenDao;
import covid.Models.Citizen;
import covid.UI.CitizenFileUi;
import covid.Validators.CitizenValidator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class CitizenFileOperation {
    public static final String FIRST_LINE_TO_CSV = "Időpont;Név;Irányítószám;Életkor;E-mail cím;TAJ szám" + "\n";
    public static final String CAN_NOT_WRITE_FILE = "Can not write file";
    public static final int NAME_INDEX = 0;
    public static final int ZIP_INDEX = 1;
    public static final int EMAIL_INDEX = 3;
    public static final int TAJ_INDEX = 4;
    public static final int AGE_INDEX = 2;
    public static final String SEPARATOR = ";";
    CitizenFileUi cfui;
    CitizenValidator cv;
    CitizenDao cd;

    public CitizenFileOperation() {
        cfui = new CitizenFileUi();
        cv = new CitizenValidator();
        cd = new CitizenDao();
    }

    public void citizensByZipToFile() {
        String zip = zipRegistration();
        Path file = cfui.filePath();
        List<Citizen> citizens = cd.findAndGroupByZip(zip);
        List<Citizen> firstSixTenCitizens = citizens.stream().limit(16).collect(Collectors.toList());
        LocalTime lt = LocalTime.of(8, 0);
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write(
                    FIRST_LINE_TO_CSV);
            for (Citizen citizen : firstSixTenCitizens) {
                writer.write(lt + SEPARATOR
                        + citizen.getName() + SEPARATOR
                        + citizen.getZip() + SEPARATOR
                        + citizen.getAge() + SEPARATOR
                        + citizen.getEmail() + SEPARATOR
                        + citizen.getTaj() + SEPARATOR
                        + "\n");
                lt = lt.plusMinutes(30);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(CAN_NOT_WRITE_FILE, ioe);
        }
    }

    private String zipRegistration() {
        String zip = cfui.zipRegistration();
        while (cv.validZip(zip) == null) {
            zip = cfui.zipRegistrationError();
        }
        cfui.validZip(zip);
        return zip;
    }

    public void massRegistration() {
        Path file = cfui.filePath();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfData = line.split(SEPARATOR);
                String name = arrayOfData[NAME_INDEX];
                String zip = arrayOfData[ZIP_INDEX];
                String email = arrayOfData[EMAIL_INDEX];
                String taj = arrayOfData[TAJ_INDEX];
                int age = Integer.parseInt(arrayOfData[AGE_INDEX]);
                Citizen citizen = new Citizen(name, zip, age, email, taj);
                cd.saveCitizen(citizen);
            }
        } catch (IOException ioe) {
            this.massRegistration();
        }
    }

    public void report() {
        Path file = cfui.filePath();
        List<String> report = cd.reportGroupByZip();
        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write(FIRST_LINE_TO_CSV);
            for (String row : report) {
                writer.write(row +  "\n");
            }
        } catch (IOException ioe) {
            throw new IllegalStateException(CAN_NOT_WRITE_FILE, ioe);
        }
    }

}
