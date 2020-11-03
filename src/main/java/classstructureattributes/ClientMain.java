package classstructureattributes;

import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        System.out.println("Please enter your name: ");
        client.setName(scanner.nextLine());

        System.out.println("Please enter your address: ");
        client.setAddress(scanner.nextLine());

        System.out.println("Please enter your year of birth: ");
        client.setYearOfBirth(scanner.nextInt());



        System.out.println("name: " + client.getName() + "\n" + "YoB:" + client.getYearOfBirth() + "\n" + "Address:" + client.getAddress() );

    }
}
