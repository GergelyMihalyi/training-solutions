package exam02.photo;

public enum Quality {
    NO_STAR(0),ONE_STAR(1),TWO_STAR(2);

    private int value;

    Quality(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
