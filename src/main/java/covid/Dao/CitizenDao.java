package covid.Dao;

import covid.Models.Citizen;
import covid.Validators.CitizenValidator;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitizenDao {

    private DataSource dataSource;

    public CitizenDao() {
        this.dataSource = new DatabaseConnection().dataSource;
    }

    public void saveCitizen(Citizen citizen) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("insert into citizens(name,zip,age,email,taj) values(?,?,?,?,?)",
                                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, citizen.getName());
            stmt.setString(2, citizen.getZip());
            stmt.setInt(3, citizen.getAge());
            stmt.setString(4, citizen.getEmail());
            stmt.setString(5, citizen.getTaj());
            stmt.executeUpdate();
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot insert");
        }
    }

    public Citizen findCitizenByTaj(String taj) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from citizens where taj = ?");
        ) {
            ps.setString(1, taj);
            return selectCitizenByPreparedStatement(ps);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public Citizen selectCitizenByPreparedStatement(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                String zip = rs.getString("zip");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String taj = rs.getString("taj");
                Citizen citizen = new Citizen(name, zip, age, email, taj);
                citizen.setId(rs.getLong("id"));
                citizen.setNumberOfVaccination(rs.getInt("number_of_vaccination"));
                LocalDateTime lastVaccination = rs.getTimestamp("last_vaccination") != null ? rs.getTimestamp("last_vaccination").toLocalDateTime() : null;
                citizen.setLastVaccination(lastVaccination);
                return citizen;
            }
            return null;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public List<Citizen> findAndGroupByZip(String zip) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from citizens where zip = ? and (number_of_vaccination = 0 or last_vaccination <= CURDATE() - INTERVAL 15 DAY) ORDER BY `age` DESC, `age` DESC");
        ) {
            ps.setString(1, zip);
            return findAndGroupByByPreparedStatement(ps);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public List<Citizen> findAndGroupByByPreparedStatement(PreparedStatement ps) {
        List<Citizen> citizens = new ArrayList<>();
        CitizenValidator cv = new CitizenValidator();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String zip = rs.getString("zip");
                String email = rs.getString("email");
                String taj = rs.getString("taj");
                int age = rs.getInt("age");
                if (cv.isValidName(name) && cv.validZip(zip) != null && cv.isValidAge(age) && cv.isValidEmail(email) && cv.isValidTaj(taj)) {
                    Citizen citizen = new Citizen(name, zip, age, email, taj);
                    citizens.add(citizen);
                }
            }
            return citizens;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public void updateCitizen(Citizen citizen) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("UPDATE citizens SET number_of_vaccination = ? ,last_vaccination = ? where id = ?")) {
            stmt.setInt(1, citizen.getNumberOfVaccination());
            stmt.setTimestamp(2, Timestamp.valueOf(citizen.getLastVaccination()));
            stmt.setLong(3, citizen.getId());
            stmt.executeUpdate();
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot update");
        }
    }

    public List<String> reportGroupByZip() {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select zip, COUNT(case number_of_vaccination when 1 then 1 else null end) as once_vaccinated, COUNT(case number_of_vaccination when 2 then 1 else null end) as twice_vaccinated, COUNT(case number_of_vaccination when 0 then 1 else null end) as not_vaccinated from citizens GROUP BY zip");
        ) {
            List<String> vaccineReport = new ArrayList<>();
            vaccineReport.add("zip;once_vaccinated;twice_vaccinated;not_vaccinated");
            while (rs.next()) {
                String zip = rs.getString("zip");
                String once = rs.getString("once_vaccinated");
                String twice = rs.getString("twice_vaccinated");
                String not = rs.getString("not_vaccinated");
                String row = zip + ";" + once + ";" + twice + ";" + not + ";";
                vaccineReport.add(row);
            }

            return vaccineReport;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }
}
