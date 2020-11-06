package week02;

import java.util.ArrayList;
import java.util.List;

public class Languages {

    public static void main(String[] args) {
        List<String> progList = new ArrayList<>();
        progList.add("Java");
        progList.add("Python");
        progList.add("JAvaScript");

        for (String i : progList) {
            if (i.length() > 5) {
                System.out.println(i);
            }
        }

    }
}
