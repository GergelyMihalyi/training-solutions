package week10d02;

import week10d01.Hiking;

import java.io.*;


public class Travel {

    public static final int STOP_NUMBER = 0;
    public static final int STOP_NAME = 2;

    public Stop getStopWithMax(InputStream is) {
        Stop stop;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            stop = new Stop("null", 0);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (Integer.parseInt(data[STOP_NUMBER]) > stop.getStopNumber()) {
                    stop = new Stop(data[STOP_NAME], Integer.parseInt(data[STOP_NUMBER]));
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return stop;
    }

}
