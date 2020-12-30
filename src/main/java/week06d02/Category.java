package week06d02;

public enum Category {
    FROZEN("Frozen"), DAIRY("Dairy"), BAKEDGOODS("Bakegoods"), OTHER("Other");
    private int number;
    private String name;

    Category(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void incNumber() {
        number++;
    }

    @Override
    public String toString() {
        return name +
                "(" + number +
                ')';
    }
}
