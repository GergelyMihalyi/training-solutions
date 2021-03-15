package activity;

public class Report {
    private ActivityType activityType;
    private double distance;

    public Report(ActivityType activityType, double distance) {
        this.activityType = activityType;
        this.distance = distance;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public double getDistance() {
        return distance;
    }

    public void addDistance(double distance){
        this.distance += distance;
    }
}
