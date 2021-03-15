package activity;

import java.util.ArrayList;
import java.util.List;

public class Activities {
    private List<Activity> activities;

    public Activities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public List<Report> distancesByTypes(){
        Report basketballReport = new Report(ActivityType.BASKETBALL,0);
        Report hikingReport = new Report(ActivityType.HIKING,0);
        Report bikingReport = new Report(ActivityType.BIKING,0);
        Report runningReport = new Report(ActivityType.RUNNING,0);
        List<Report> reports = new ArrayList<>();
        for(Activity activity: activities){
            if(activity.getType() ==  ActivityType.BASKETBALL){
               basketballReport.addDistance(activity.getDistance());
            }
            if(activity.getType() ==  ActivityType.HIKING){
                hikingReport.addDistance(activity.getDistance());
            }
            if(activity.getType() ==  ActivityType.BIKING){
                bikingReport.addDistance(activity.getDistance());
            }
            if(activity.getType() ==  ActivityType.RUNNING){
                runningReport.addDistance(activity.getDistance());
            }
        }
        reports.add(basketballReport);
        reports.add(hikingReport);
        reports.add(bikingReport);
        reports.add(runningReport);

        return reports;

    }

    public int numberOfTrackActivities(){
        int numberOfTrackActivities=0;
        for(Activity activity: activities){
            if(activity.getType() !=  ActivityType.BASKETBALL){
                numberOfTrackActivities++;
            }
        }
        return  numberOfTrackActivities;
    }

    public int numberOfWithoutTrackActivities(){
        int numberOfWithoutTrackActivities=0;
        for(Activity activity: activities){
            if(activity.getType() ==  ActivityType.BASKETBALL){
                numberOfWithoutTrackActivities++;
            }
        }
        return  numberOfWithoutTrackActivities;
    }
}
