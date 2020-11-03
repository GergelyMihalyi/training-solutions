package classstructureio;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the first number: ");
        int a = scanner.nextInt();

        System.out.println("Please enter the second number: ");
        int b = scanner.nextInt();

        System.out.println( a + " + " + b);
        int result = a+b;
        System.out.println("result: " + result);
    }
}
