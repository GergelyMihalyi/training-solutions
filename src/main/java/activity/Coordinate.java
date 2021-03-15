package activity;

import static java.lang.Math.abs;

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        if(!isValidLatitude(latitude) || !isValidLongitude(longitude)){
            throw new IllegalArgumentException("Not valid data");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private boolean isValidLatitude(double latitude){
        return abs(latitude)<90;
    }

    private boolean isValidLongitude(double longitude){
        return abs(longitude)<180;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
