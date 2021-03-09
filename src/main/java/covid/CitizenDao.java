package covid;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenDao {

    private DataSource dataSource;

    public CitizenDao(DataSource dataSource) {
        this.dataSource = dataSource;
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
                return citizen;
            }
            throw new IllegalArgumentException("Not found");
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
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String zip = rs.getString("zip");
                String email = rs.getString("email");
                String taj = rs.getString("taj");
                int age = rs.getInt("age");
                if (Citizen.isValidName(name) && Citizen.isValidZip(zip) && Citizen.isValidAge(age) && Citizen.isValidEmail(email) && Citizen.isValidTaj(taj)) {
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
}
