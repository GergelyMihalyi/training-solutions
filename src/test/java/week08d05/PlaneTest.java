package week08d05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    @Test
    void planeTest() {
        Plane plane = new Plane();

        assertEquals(16, plane.flyOverMapWithMaxOcean("map.txt"));
    }
}