package week06d05;

public enum BottleType {
    GLASS_BOTTLE(10),PET_BOTTLE(20);

    private final int maximumAmount;

    BottleType(int maximumAmount){
        this.maximumAmount = maximumAmount;
    }

    public int getMaximumAmount() {
        return maximumAmount;
    }
}
