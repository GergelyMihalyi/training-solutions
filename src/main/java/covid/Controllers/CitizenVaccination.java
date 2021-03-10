package covid.Controllers;

import covid.Dao.CitizenDao;
import covid.Dao.VaccinatedDao;
import covid.Models.Citizen;
import covid.Models.Status;
import covid.Models.TypeOfVaccination;
import covid.UI.CitizenVaccinationUI;
import covid.UI.CitizenUserInterface;
import covid.Validators.CitizenValidator;

import java.time.LocalDateTime;

public class CitizenVaccination {
    CitizenValidator cv;
    CitizenDao cd;
    VaccinatedDao vd;
    CitizenVaccinationUI cvui;

    public CitizenVaccination() {
        cv = new CitizenValidator();
        cd = new CitizenDao();
        cvui = new CitizenVaccinationUI();
        vd = new VaccinatedDao();
    }

    public void vaccination() {
        String taj = tajRegistration();
        Citizen citizen = cd.findCitizenByTaj(taj);
        if (citizen == null) {
            cvui.notRegisteredTaj();
        } else {
            if (citizen.getNumberOfVaccination() == 0) {
                firstVaccination(citizen);
            } else if (citizen.getNumberOfVaccination() == 1) {
                secondVaccination(citizen);
            } else if (citizen.getNumberOfVaccination() == 2) {
                cvui.vaccinatedTwice();
            }
        }

    }

    private String tajRegistration() {
        CitizenUserInterface rui = new CitizenUserInterface();
        String taj = rui.tajRegistration();
        while (!cv.isValidTaj(taj)) {
            taj = rui.tajRegistrationError();
        }
        return taj;
    }

    private void firstVaccination(Citizen citizen) {
        int indexOfVaccination = cvui.vaccinationType();
        TypeOfVaccination vaccine = TypeOfVaccination.values()[indexOfVaccination];
        LocalDateTime lt = LocalDateTime.now();
        Vaccination vaccination = new Vaccination(citizen.getId(), Status.FIRST_VACCINATED, vaccine, lt);
        vd.saveVaccinatedCitizen(vaccination);
        citizen.setNumberOfVaccination(1);
        citizen.setLastVaccination(lt);
        cd.updateCitizen(citizen);
    }

    private void secondVaccination(Citizen citizen) {
        Vaccination vaccination = vd.findVaccinatedByCitizenId(citizen.getId());
        LocalDateTime lastVaccinationPlus = citizen.getLastVaccination().plusDays(15);
        if (lastVaccinationPlus.isAfter(LocalDateTime.now())) {
            cvui.previousVaccination(15);
        } else {
            cvui.vaccinationTypeAndDate(vaccination.getTov(), vaccination.getVaccinationDate());
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
        String taj = tajRegistration();
        Citizen citizen = cd.findCitizenByTaj(taj);
        if (citizen.getNumberOfVaccination() == 2) {
            cvui.vaccinatedTwice();
        }else{
            String note = cvui.failureNote();
            Vaccination vaccination = new Vaccination(citizen.getId(), Status.FAILED, null, null);
            vaccination.setNote(note);
            vd.saveVaccinatedCitizen(vaccination);
        }

    }


}
