package week09d03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testOverFourTeen() {
        Person person = new Person("John Doe", 16);
        person.setPresent();

        assertNotEquals(null, person.getPresent());
        assertNotEquals(Present.TOY, person.getPresent());
    }

    @Test
    void testUnderFourTeen() {
        Person person = new Person("John Doe", 12);
        person.setPresent();

        assertNotEquals(null, person.getPresent());
    }

}