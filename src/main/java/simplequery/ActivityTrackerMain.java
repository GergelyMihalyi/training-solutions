package simplequery;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityTrackerMain {

    public static void main(String[] args) {
        ActivityTrackerMain at = new ActivityTrackerMain();
        MysqlDataSource dataSource = at.authenticateDatabase();
        Activity a1 = new Activity(LocalDateTime.of(2021,3,6,12,12,12), "first", Type.BASKETBALL);
        a1.setId(at.insertDataBase(dataSource,a1));
        System.out.println(at.parameterizedQueryWithId(dataSource,1));
    }

    private MysqlDataSource authenticateDatabase(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/activitytracker?useUnicode=true");
        dataSource.setUser("activitytracker");
        dataSource.setPassword("activitytracker");

        return dataSource;
    }

    private Activity parameterizedQueryWithId(MysqlDataSource dataSource, long id){
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select * from activities where id = ?");
        ) {
            stmt.setLong(1, id);

            return queryResult(stmt);
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    private List<Activity> allRows(MysqlDataSource dataSource){
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select * from activities");
        ) {
            return allQueryResultsToList(stmt);
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }


    private List<Activity> allQueryResultsToList(PreparedStatement stmt){

        List<Activity> activities = new ArrayList<>();
        try (
                ResultSet rs = stmt.executeQuery();
        ) {
            while (rs.next()) {
                String desc = rs.getString("activity_desc");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                Type type = Type.valueOf(rs.getString("activity_type"));
                long id = rs.getLong("id");
                Activity activity = new Activity(id,startTime,desc,type);
                activities.add(activity);
            }

            return activities;
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    private Activity queryResult(PreparedStatement stmt){
        try (
                ResultSet rs = stmt.executeQuery();
        ) {
            if (rs.next()) {
                String desc = rs.getString("activity_desc");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                Type type = Type.valueOf(rs.getString("activity_type"));
                long id = rs.getLong("id");
                Activity activity = new Activity(id,startTime,desc,type);
                return activity;
            }
            throw new IllegalArgumentException("No result");
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    private long insertDataBase(MysqlDataSource dataSource, Activity activity){
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
