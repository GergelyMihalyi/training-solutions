package week07d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserTest {

    @Test
    void userTest(){
        User user = User.of("a","b","c");
        assertEquals("b c",user.getFullName());
    }
}
