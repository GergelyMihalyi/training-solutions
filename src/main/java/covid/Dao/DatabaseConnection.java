package covid.Dao;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/activitytracker?useUnicode=true";
    private static final String DB_USER = "activitytracker";
    private static final String DB_PASSWORD = "activitytracker";
    private MariaDbDataSource dataSource = new MariaDbDataSource();

    public DatabaseConnection() {
        try {
            dataSource.setUrl(DB_URL);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);
        } catch (SQLException se) {
            throw new IllegalStateException("Can not create data source", se);
        }
    }

    public MariaDbDataSource getDataSource() {
        return dataSource;
    }
}
