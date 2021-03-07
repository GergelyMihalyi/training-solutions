package architecture;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import simplequery.Activity;
import simplequery.Type;

import java.time.LocalDateTime;
import java.util.List;

public class ActivityMain {

    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/activitytracker?useUnicode=true");
        dataSource.setUser("activitytracker");
        dataSource.setPassword("activitytracker");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.clean();
        flyway.migrate();

        ActivityDao activityDao = new ActivityDao(dataSource);
        Activity a1 = new Activity(LocalDateTime.of(2021,3,6,12,12,12), "first", Type.BASKETBALL);
        activityDao.saveActivity(a1);

        List<Activity> activities = activityDao.listActivities();
        System.out.println(activities);

        Activity activity = activityDao.findActivityById(1);
        System.out.println(activity.getType());
    }
}
