package simpleupdate.activitytracker;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.time.LocalDateTime;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        ActivityTrackerMain at = new ActivityTrackerMain();
        MysqlDataSource dataSource = at.authenticateDatabase();
        Activity a1 = new Activity(LocalDateTime.of(2021,3,6,12,12,12), "first",Type.BASKETBALL);
        a1.setId(at.insertDataBase(dataSource,a1));

    }

    private MysqlDataSource authenticateDatabase(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/activitytracker?useUnicode=true");
        dataSource.setUser("activitytracker");
        dataSource.setPassword("activitytracker");

        return dataSource;
    }

    private long insertDataBase(MysqlDataSource dataSource,Activity activity){
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement("insert into activities(start_time,activity_desc,activity_type) values(?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(activity.getStartTime()));
            stmt.setString(2, activity.getDesc());
            stmt.setString(3, activity.getType().toString());
            stmt.executeUpdate();
            return executeAndGetGeneratedKey(stmt);
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot insert");
        }
    }

    private long executeAndGetGeneratedKey(PreparedStatement stmt) {
        try (
                ResultSet rs = stmt.getGeneratedKeys();
        ) {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("No key has generated");
            }
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }
}
