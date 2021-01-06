package week08d03;

import java.util.ArrayList;
import java.util.List;

public class StringList {

    public static final int FIRST_WORD = 0;

    public List<String> shortestWords(List<String> words) {
        List<String> shortestWords = new ArrayList<>();
        int shortestWord = words.get(FIRST_WORD).length();
        for (String word : words) {
            if (word.length() < shortestWord) {
                shortestWord = word.length();
                shortestWords.clear();
                shortestWords.add(word);
            } else if (word.length() == shortestWord) {
                shortestWords.add(word);
            }
        }
        return shortestWords;
    }

}
