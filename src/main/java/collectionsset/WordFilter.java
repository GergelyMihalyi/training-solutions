package collectionsset;

import java.util.Set;
import java.util.TreeSet;

public class WordFilter {

    public Set<String> filterWords(String[] randomStrings){
        Set<String> orderedStrings = new TreeSet<String>();
        for(String str: randomStrings){
            orderedStrings.add(str);
        }
        return orderedStrings;
    }

    public static void main(String[] args) {
        String[] randomStrings = new String[]{"aba","bdb","cbd","aba","kod","dsa","jij"};
        System.out.println(new WordFilter().filterWords(randomStrings));
    }
}
