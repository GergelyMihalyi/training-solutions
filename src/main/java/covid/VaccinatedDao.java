package covid;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VaccinatedDao {

    private DataSource dataSource;

    public VaccinatedDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveVaccinatedCitizen(Vaccination vaccination) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("insert into vaccinations (citizen_id,vaccination_date,status,note,vaccination_type) values(?,?,?,?,?)",
                                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, vaccination.getCitizenId());

            Timestamp ldt = vaccination.getVaccinationDate() != null ? Timestamp.valueOf(vaccination.getVaccinationDate()) : null;
            stmt.setTimestamp(2, ldt);

            String status = vaccination.getStatus() != null ? vaccination.getStatus().name() : null;
            stmt.setString(3, status);
            stmt.setString(4, vaccination.getNote());

            String tov = vaccination.getTov() != null ? vaccination.getTov().getName() : null;
            stmt.setString(5, tov);
            stmt.executeUpdate();
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot insert");
        }
    }

    public Vaccination findVaccinatedByCitizenId(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from vaccinations where citizen_id = ?");
        ) {
            ps.setLong(1, id);
            return selectVaccinationByPreparedStatement(ps);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public Vaccination selectVaccinationByPreparedStatement(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                long citizenId = rs.getLong("citizen_id");
                LocalDateTime vaccinationDate = rs.getTimestamp("vaccination_date").toLocalDateTime();
                Status status = Status.valueOf(rs.getString("status"));
                TypeOfVaccination tov = null;
                for (TypeOfVaccination type : TypeOfVaccination.values()) {
                    if (type.getName().equals(rs.getString("vaccination_type"))) {
                        tov = type;
                    }
                }
                Vaccination vaccination = new Vaccination(citizenId, status, tov, vaccinationDate);
                return vaccination;
            }
            throw new IllegalArgumentException("Not found");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public void updateVaccination(Vaccination vaccination) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("UPDATE vaccinations SET vaccination_date = ? , status = ? where citizen_id = ?")) {
            stmt.setTimestamp(1, Timestamp.valueOf(vaccination.getVaccinationDate()));
            stmt.setString(2, vaccination.getStatus().name());
            stmt.setLong(3, vaccination.getCitizenId());
            stmt.executeUpdate();
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot update");
        }
    }

}
