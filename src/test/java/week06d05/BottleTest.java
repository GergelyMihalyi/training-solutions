package week06d05;

import org.junit.jupiter.api.Test;
import week06d03.Series;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BottleTest {

    @Test
    void testBottle(){
        Bottle bottle = Bottle.of(BottleType.PET_BOTTLE);
        assertThrows(IllegalArgumentException.class, ()-> {
            bottle.fill(50);
        });
    }
}
