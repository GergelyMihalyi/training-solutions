package classstructureattributes;

import java.util.Scanner;

public class Music {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your favorite band: ");
        String bandName = scanner.nextLine();

        System.out.println("Please enter your favorite song from the previous band: ");
        String songName = scanner.nextLine();
        System.out.println("Please enter your favorite song length: ");
        int songLength = scanner.nextInt();



        Song song = new Song(bandName,songName,songLength);

        System.out.println(song.getBand() + "- " + song.getTitle() + "(" + song.getLength() + ")");

    }
}
