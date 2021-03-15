package activity;

public class ActivityWithTrack implements Activity{
    private Track track;
    private ActivityType activityType;

    public ActivityWithTrack(Track track, ActivityType activityType) {
        this.track = track;
        this.activityType = activityType;
    }

    public double getDistance(){
        return track.getDistance();
    }

    public ActivityType getType(){
        return activityType;
    }
}
