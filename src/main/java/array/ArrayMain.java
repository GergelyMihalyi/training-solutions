package array;

public class ArrayMain {
    public static void main(String[] args) {
        String[] days = {"monday", "tuesday", "wednesday", "thurstday", "friday", "saturday", "sunday"};

        System.out.println(days[1] + "  length:" + days.length);

        int[] arrayOfPowersOfTwo = new int[5];
        arrayOfPowersOfTwo[0] = 1;

        for (int i = 1; i < arrayOfPowersOfTwo.length; i++) {
            arrayOfPowersOfTwo[i] = arrayOfPowersOfTwo[i - 1] * 2;
        }

        for (int powerOfTwo : arrayOfPowersOfTwo) {
            System.out.println(powerOfTwo);
        }

        boolean[] arrayOfBooleans = new boolean[6];
        arrayOfBooleans[0] = false;

        for (int i = 1; i < arrayOfBooleans.length; i++) {
            arrayOfBooleans[i] = !arrayOfBooleans[i - 1];
        }

        for (boolean b : arrayOfBooleans) {
            System.out.println(b);
        }
    }
}
