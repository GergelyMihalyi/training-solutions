package introjunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GentlemanTest {

    @Test
    void testName(){
        Gentleman gentleman = new Gentleman();

        assertEquals("Hello John Doe", gentleman.sayHello("John Doe"));
    }
}
