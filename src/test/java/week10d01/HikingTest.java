package week10d01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HikingTest {

    @Test
    void elevationTest(){
        assertEquals(100.00,new Hiking().getPlusElevation().getDownHill());
    }
}
