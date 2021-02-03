package week13d03;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    @Test
    void authorTest() {
        String author = new BookStore().findAuthorWithMaxPage(List.of(
                new Book("first", "BookOne", 100),
                new Book("second", "BookOne", 20),
                new Book("third", "BookOne", 412),
                new Book("second", "BookOne", 400),
                new Book("first", "BookOne", 100)
        ));

        assertEquals("second", author);
    }
}