package architecture;

import simplequery.Activity;
import simplequery.Type;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityDao {

    private DataSource dataSource;

    public ActivityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveActivity(Activity activity){
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("insert into activities(start_time,activity_desc,activity_type) values(?,?,?)",
                                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(activity.getStartTime()));
            stmt.setString(2, activity.getDesc());
            stmt.setString(3, activity.getType().toString());
            stmt.executeUpdate();
        }
        catch (SQLException se) {
            throw new IllegalStateException("Cannot insert");
        }
    }

    public Activity findActivityById(long id){
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from activities where id = ?");
        ) {
            ps.setLong(1, id);

            return selectActivityByPreparedStatement(ps);
        }
        catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }

    public List<Activity> listActivities(){
        try (
                Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from activities")
        ) {
            List<Activity> activities = new ArrayList<>();
            while (rs.next()) {
                String desc = rs.getString("activity_desc");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                Type type = Type.valueOf(rs.getString("activity_type"));
                long id = rs.getLong("id");
                Activity activity = new Activity(id,startTime,desc,type);
                activities.add(activity);
            }
            return activities;
        }
        catch (SQLException se) {
            throw new IllegalStateException("Cannot select activities", se);
        }
    }

    public Activity selectActivityByPreparedStatement(PreparedStatement ps) {
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String desc = rs.getString("activity_desc");
                LocalDateTime startTime = rs.getTimestamp("start_time").toLocalDateTime();
                Type type = Type.valueOf(rs.getString("activity_type"));
                long id = rs.getLong("id");
                Activity activity = new Activity(id,startTime,desc,type);
                return activity;
            }
            throw new IllegalArgumentException("Not found");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot query", sqle);
        }
    }
}
