package activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Track {
    List<TrackPoint> trackPoints = new ArrayList<>();

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        trackPoints.add(trackPoint);
    }

    public Coordinate findMaximumCoordinate() {
        double latitude = 0;
        double longitude = 0;
        for (TrackPoint trackPoint : trackPoints) {
            if (trackPoint.getCoordinate().getLatitude() > latitude) {
                latitude = trackPoint.getCoordinate().getLatitude();
            }
            if (trackPoint.getCoordinate().getLongitude() > longitude) {
                longitude = trackPoint.getCoordinate().getLongitude();
            }
        }
        Coordinate coordinate = new Coordinate(latitude, longitude);
        return coordinate;
    }

    public Coordinate findMinimumCoordinate() {
        double latitude = 0;
        double longitude = 0;
        for (TrackPoint trackPoint : trackPoints) {
            if (trackPoint.getCoordinate().getLatitude() < latitude) {
                latitude = trackPoint.getCoordinate().getLatitude();
            }
            if (trackPoint.getCoordinate().getLongitude() < longitude) {
                longitude = trackPoint.getCoordinate().getLongitude();
            }
        }
        Coordinate coordinate = new Coordinate(latitude, longitude);
        return coordinate;
    }

    public double getDistance() {
        double distance = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            distance += trackPoints.get(i - 1).getDistanceFrom(trackPoints.get(i));
        }
        return distance;
    }

    public double getFullDecrease() {
        double elevation = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            if (trackPoints.get(i - 1).getElevation() > trackPoints.get(i).getElevation()) {
                elevation += trackPoints.get(i - 1).getElevation() - trackPoints.get(i).getElevation();
            }
        }
        return elevation;
    }

    public double getFullElevation() {
        double elevation = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            if (trackPoints.get(i - 1).getElevation() < trackPoints.get(i).getElevation()) {
                elevation += trackPoints.get(i).getElevation() - trackPoints.get(i - 1).getElevation();
            }
        }
        return elevation;
    }

    public void getRectangleArea() {

    }

    public void loadFromGpx(InputStream file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            String line;
            Coordinate coordinate = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("   <trkpt")) {
                    String[] pieces = line.split("\"");
                    double latitude = Double.parseDouble(pieces[1]);
                    double altitude = Double.parseDouble(pieces[3]);
                    coordinate = new Coordinate(latitude, altitude);
                }
                if (line.startsWith("    <ele>")) {
                    double elevation = Double.parseDouble(line.substring(9, 14));
                    TrackPoint trackPoint = new TrackPoint(coordinate, elevation);

                    trackPoints.add(trackPoint);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }


}
