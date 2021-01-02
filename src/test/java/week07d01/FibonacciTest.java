package week07d01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciTest {

    @Test
    void fibTest(){
        assertEquals(13,Fibonacci.fib(7));
    }
}
