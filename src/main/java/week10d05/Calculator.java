package week10d05;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    private int minSum;
    private int maxSum;

    public int getMinSum() {
        return minSum;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void findMinMaxSum(int[] arr){
        Arrays.sort(arr);
        int numbers = arr.length < 4 ? arr.length : 4;
        for(int i=0 ;i<numbers; i++){
            minSum += arr[i];
        }
        for(int i=(arr.length-numbers) ;i<arr.length; i++){
            maxSum += arr[i];
        }

    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many numbers will be?");
        int numbersAmount = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[numbersAmount];
        for( int i=0; i < numbersAmount ;i++)
        {
            System.out.println("Enter the number");
            numbers[i] = scanner.nextInt();
        }
        calculator.findMinMaxSum(numbers);
        System.out.println("minimum Sum:" + calculator.getMinSum() + "\n" + "maximum sum:" + calculator.getMaxSum());
    }
}
