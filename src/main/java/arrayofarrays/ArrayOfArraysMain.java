package arrayofarrays;

public class ArrayOfArraysMain {
    public static void main(String[] args) {

    }

    private int[][] multiplicationTable(int size) {
        int[][] numbers = new int[size][size];
        for (int i = 0; i < size; i++){
            for (int y = 0; y < size; y++){
                numbers[i][y]= (i + 1) * (y + 1);
            }

        }

        return numbers;
    }


}
