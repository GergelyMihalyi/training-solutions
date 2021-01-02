package week06d03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeriesTest {

    @Test
    void TestSeries(){
        Series series = new Series();
        List<Integer> numbers = Arrays.asList(5,2,4);
        assertEquals(Type.DECREASE,series.calculateSeriesType(numbers));
    }

    @Test
    void TestSeriesException(){
        assertThrows(IllegalArgumentException.class, ()-> {
            new Series().calculateSeriesType(List.of(0));
        });
    }
}
