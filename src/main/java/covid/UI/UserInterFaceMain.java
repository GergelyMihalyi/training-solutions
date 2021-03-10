package covid.UI;

import java.util.Scanner;

public class UserInterFaceMain {
    Scanner scanner;

    public UserInterFaceMain() {
        this.scanner = new Scanner(System.in);
    }

    public void printOutUi(String sentence) {
        System.out.println(sentence);
    }
}
