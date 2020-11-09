package week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    Office office = new Office();

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.readOffice();
        controller.printMenu();
        controller.runMenu();
    }

    public void readOffice() {
        int numberOfMeetingRooms;
        System.out.println("Please enter the number of meeting rooms");
        numberOfMeetingRooms = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numberOfMeetingRooms; i++) {
            System.out.println("Please enter name of the number " + i + " meeting room:");
            String name = scanner.nextLine();

            System.out.println("Please enter length of the number " + i + " meeting room:");
            int length = scanner.nextInt();

            System.out.println("Please enter width of the number " + i + " meeting room:");
            int width = scanner.nextInt();
            scanner.nextLine();

            MeetingRoom meetingRoom = new MeetingRoom(name, length, width);
            office.addMeetingRoom(meetingRoom);
        }
    }

    public void printMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("1. Tárgyalók sorrendben");
        menu.add("2. Tárgyalók visszafele sorrendben");
        menu.add("3. Minden második tárgyaló");
        menu.add("4. Területek");
        menu.add("5. Keresés pontos név alapján");
        menu.add("6. Keresés névtöredék alapján");
        menu.add("7. Keresés terület alapján");

        for (String menuItem : menu) {
            System.out.println(menuItem);

        }
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter number of menu: ");
        int menuNumber = scanner.nextInt();
        scanner.nextLine();

        switch (menuNumber) {
            case 1:
                office.printNames();
                break;
            case 2:
                office.printNamesReverse();
                break;
            case 3:
                office.printEventNames();
                break;
            case 4:
                office.printAreas();
                break;
            case 5:
                System.out.println("Search by name. Please enter a name:");
                String name = scanner.nextLine();
                office.printMeetingRoomWithName(name);
                break;
            case 6:
                System.out.println("Search by part of a name. Please enter a part of a name :");
                String part = scanner.nextLine().toLowerCase();
                office.printMeetingRoomWithContains(part);
                break;
            case 7:
                System.out.println("Search by area. Lists all rooms with an area greater than the number you entered. Please enter a number:");
                int area = scanner.nextInt();
                scanner.nextLine();
                office.printAreasLargerThan(area);

                break;
            default:
                System.out.println("The menu system does not include this number");
        }
    }

}
