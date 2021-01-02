package week05d04;

public enum Currency {
    HUF(1),USD(300);

    private int multiplier;

    Currency(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
