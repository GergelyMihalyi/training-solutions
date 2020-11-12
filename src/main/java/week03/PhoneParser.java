package week03;

import java.util.Scanner;

public class PhoneParser {

    private String phoneNumber;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the telephone number: ");
        PhoneParser phoneParser = new PhoneParser();
        phoneParser.phoneNumber = scanner.nextLine();
        phoneParser.parse(phoneParser.phoneNumber);

    }

    private void parse(String phoneNumber) {
        String[] split = phoneNumber.split("-");
        Phone phone = new Phone(split[0],split[1]);
        System.out.println(phone.getPrefix() + "-" + phone.getNumber());

    }


}
