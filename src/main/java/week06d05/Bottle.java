package week06d05;

public class Bottle {
    private BottleType type;
    private int filledUntil;

    public Bottle(BottleType type) {
        this.type = type;
    }

    public static Bottle of(BottleType type){
        return new Bottle(type);
    }

    public void fill(int fillAmount){
        if(filledUntil + fillAmount > type.getMaximumAmount()){
            throw new IllegalArgumentException("Overflow");
        }else{
            filledUntil += fillAmount;
        }
    }

    public static void main(String[] args) {

    }
}
