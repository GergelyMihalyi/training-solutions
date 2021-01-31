package week10d02;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class TravelTest {

    @Test
    void getStopWithMaxTest(){
        Travel travel = new Travel();
        InputStream is = Travel.class.getResourceAsStream("utasadat.txt");
        assertEquals("3134404",travel.getStopWithMax(is).getName());
    }
}