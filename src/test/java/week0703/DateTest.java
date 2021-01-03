package week0703;

import org.junit.jupiter.api.Test;
import week07d03.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {

    @Test
    void dateTest(){
        Date date = new Date(2000, 2, 20);
        assertEquals(23,date.withDay(23).getDay());

    }
}
