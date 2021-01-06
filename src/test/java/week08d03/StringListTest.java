package week08d03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringListTest {

    @Test
    void stringListTest() {
        List<String> words = List.of("aaa", "vvv", "cccc", "dd", "ee");
        List<String> shortestWords = List.of("dd", "ee");
        StringList stringList = new StringList();

        assertEquals(shortestWords, stringList.shortestWords(words));
    }
}