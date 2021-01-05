package week08d01;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SultanTest {

    @Test
    void doorTest() {
        assertEquals(List.of(1, 4, 9, 16, 25, 36, 49, 64, 81), new Sultan().openDoors(100));
    }
}