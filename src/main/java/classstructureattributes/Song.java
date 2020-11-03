package classstructureattributes;

public class Song {
    private String title;
    private int length;
    private String band;

    public Song(String band,String title, int length) {
        this.title = title;
        this.length = length;
        this.band = band;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public String getBand() {
        return band;
    }
}
