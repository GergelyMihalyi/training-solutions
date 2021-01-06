package week0804;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamplesStoreTest {

    @Test
    void exampleStoreTest() {
        ExamplesStore examplesStore = new ExamplesStore();
        List<String> titles = List.of("Első feladat", "Második feladat");

        assertEquals(titles, examplesStore.getTitlesOfExamples());
    }

}