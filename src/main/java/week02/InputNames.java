package week02;

import java.util.Scanner;

public class InputNames {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arrayOfNames = new String[5];



        for(int i = 0; i < 5; i++){
            System.out.println("Please enter your name: ");
            arrayOfNames[i]=scanner.nextLine();
        }


        for(int i = 0; i < arrayOfNames.length; i++){
            System.out.println(arrayOfNames[i]);
        }

    }
}
