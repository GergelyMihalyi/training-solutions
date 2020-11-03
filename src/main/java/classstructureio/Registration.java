package classstructureio;

import java.util.Scanner;

public class Registration {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Please enter your e-mail address: ");
        String email = scanner.nextLine();

        System.out.println("Registration details:" + "\n" + "Name: " + name + "\n" + "E-mail: " + email);


    }
}
