package week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Office {
    private List<MeetingRoom> meetingRooms = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addMeetingRoom(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }


    public void printNames() {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println(meetingRoom.getName());

        }
    }

    public void printNamesReverse() {
        for (int i = meetingRooms.size(); i <= 0; --i) {
            System.out.println(meetingRooms.get(i).getName());
        }
    }

    public void printEventNames() {
        for (int i = 0; i < meetingRooms.size(); ++i) {
            if (i % 2 == 0) {
                System.out.println(meetingRooms.get(i).getName());
            }
        }
    }

    public void printAreas() {
        for (MeetingRoom meetingRoom : meetingRooms) {
            System.out.println("Meeting room:" + meetingRoom.getName() + "area:" + meetingRoom.getArea());
        }
    }

    public void printMeetingRoomWithName(String name) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().equals(name)) {
                System.out.println("meeting room: " + meetingRoom.getName() + "\n" + "width:" + meetingRoom.getWidth() + "\n" + "length" + meetingRoom.getLength());
            }
        }
    }

    public void printMeetingRoomWithContains(String part) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getName().toLowerCase().contains(part)) {
                System.out.println("meeting room: " + meetingRoom.getName() + "\n" + "width:" + meetingRoom.getWidth() + "\n" + "length" + meetingRoom.getLength() + "\n" + "area" + meetingRoom.getArea());
            }
        }
    }

    public void printAreasLargerThan(int area) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if (meetingRoom.getArea() > area) {
                System.out.println("meeting room: " + meetingRoom.getName() + "\n" + "width:" + meetingRoom.getWidth() + "\n" + "length" + meetingRoom.getLength() + "\n" + "area" + meetingRoom.getArea());
            }
        }
    }
}
