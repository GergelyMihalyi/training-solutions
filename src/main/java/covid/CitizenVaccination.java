package covid;

import org.mariadb.jdbc.MariaDbDataSource;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CitizenVaccination {
    Scanner scanner = new Scanner(System.in);
    MariaDbDataSource dataSource = new DatabaseConnection().dataSource;

    public void vaccination() {
        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        while (!Citizen.isValidTaj(taj)) {
            System.out.println("The taj number should be valid!");
            System.out.println("Please enter taj: ");
            taj = scanner.nextLine();
        }
        CitizenDao cd = new CitizenDao(dataSource);
        Citizen citizen = cd.findCitizenByTaj(taj);
        if (citizen == null) {
            System.out.println("The taj number is not registered!");
        } else {
            if (citizen.getNumberOfVaccination() == 0) {
                firstVaccination(cd, citizen);
            } else if (citizen.getNumberOfVaccination() == 1) {
                secondVaccination(cd, citizen);
            } else if (citizen.getNumberOfVaccination() == 2) {
                System.out.println("This citizen has already been vaccinated twice!");
            }
        }

    }

    private void firstVaccination(CitizenDao cd, Citizen citizen) {
        System.out.println("Please select Vaccination type: \n");
        for (int i = 0; i < TypeOfVaccination.values().length; i++) {
            System.out.println(i + ". " + TypeOfVaccination.values()[i].getName() + "\n");
        }
        int indexOfVaccination = Integer.parseInt(scanner.nextLine());
        TypeOfVaccination vaccine = TypeOfVaccination.values()[indexOfVaccination];
        LocalDateTime lt = LocalDateTime.now();
        Vaccination vaccination = new Vaccination(citizen.getId(), Status.FIRST_VACCINATED, vaccine, lt);
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        vd.saveVaccinatedCitizen(vaccination);
        citizen.setNumberOfVaccination(1);
        citizen.setLastVaccination(lt);
        cd.updateCitizen(citizen);
    }

    private void secondVaccination(CitizenDao cd, Citizen citizen) {
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        Vaccination vaccination = vd.findVaccinatedByCitizenId(citizen.getId());
        LocalDateTime lastVaccinationPlus = citizen.getLastVaccination().plusDays(15);
        if (lastVaccinationPlus.isAfter(LocalDateTime.now())) {
            System.out.println("It has not been 15 days since the previous vaccination");
        } else {
            System.out.println("Vaccinated type: " + vaccination.getTov() + "\n"
                    + "Vaccinated date: " + vaccination.getVaccinationDate());
            citizen.setNumberOfVaccination(2);
            LocalDateTime lt = LocalDateTime.now();
            vaccination.setVaccinationDate(lt);
            vaccination.setStatus(Status.VACCINATED);
            citizen.setLastVaccination(lt);
            vd.updateVaccination(vaccination);
            cd.updateCitizen(citizen);
        }
    }

    public void refused() {
        System.out.println("Please enter taj: ");
        String taj = scanner.nextLine();
        CitizenDao cd = new CitizenDao(dataSource);
        Citizen citizen = cd.findCitizenByTaj(taj);
        if (citizen.getNumberOfVaccination() == 2) {
            System.out.println("This citizen has already been vaccinated twice!");
        }
        System.out.println("Please enter the cause of the failure: ");
        String note = scanner.nextLine();

        Vaccination vaccination = new Vaccination(citizen.getId(), Status.FAILED, null, null);
        vaccination.setNote(note);
        VaccinatedDao vd = new VaccinatedDao(dataSource);
        vd.saveVaccinatedCitizen(vaccination);
    }


}
