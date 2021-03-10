package covid.Controllers;

import covid.Models.Status;
import covid.Models.TypeOfVaccination;

import java.time.LocalDateTime;

public class Vaccination {
    private long citizenId;
    private Status status;
    private String note;
    private TypeOfVaccination tov;
    private LocalDateTime vaccinationDate;

    public Vaccination(long citizenId, Status status, TypeOfVaccination tov, LocalDateTime vaccinationDate) {
        this.citizenId = citizenId;
        this.status = status;
        this.tov = tov;
        this.vaccinationDate = vaccinationDate;
    }

    public long getCitizenId() {
        return citizenId;
    }

    public Status getStatus() {
        return status;
    }

    public String getNote() {
        return note;
    }

    public TypeOfVaccination getTov() {
        return tov;
    }

    public LocalDateTime getVaccinationDate() {
        return vaccinationDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setVaccinationDate(LocalDateTime vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "citizenId=" + citizenId +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", tov=" + tov +
                ", vaccinationDate=" + vaccinationDate +
                '}';
    }
}
