package covid.UI;

import covid.Models.TypeOfVaccination;

import java.time.LocalDateTime;

public class CitizenVaccinationUI extends UserInterFaceMain {

    public void notRegisteredTaj() {
        printOutUi("The taj number is not registered!");
    }

    public void vaccinatedTwice() {
        printOutUi("This citizen has already been vaccinated twice!");
    }

    public int vaccinationType() {
        printOutUi("Please select Vaccination type: \n");
        for (int i = 0; i < TypeOfVaccination.values().length; i++) {
            printOutUi(i + ". " + TypeOfVaccination.values()[i].getName() + "\n");
        }
        return scanner.nextInt();
    }

    public void previousVaccination(int day) {
        printOutUi("It has not been " + day + " days since the previous vaccination");
    }

    public void vaccinationTypeAndDate(TypeOfVaccination type, LocalDateTime date) {
        printOutUi("Vaccinated type: " + type.getName() + "\n"
                + "Vaccinated date: " + date.toString());
    }

    public String failureNote() {
        printOutUi("Please enter the cause of the failure: ");
        return scanner.nextLine();
    }
}
