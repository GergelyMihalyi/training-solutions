package covid;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao {

    private DataSource dataSource;

    public CityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public City findCityByZip(String zip) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from cities where zip = ?");
        ) {
            ps.setString(1, zip);
            return selectCityByPreparedStatement(ps);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public City selectCityByPreparedStatement(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String name = rs.getString("name");
                String zip = rs.getString("zip");
                City city = new City(zip, name);
                return city;
            }
            return null;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

}
