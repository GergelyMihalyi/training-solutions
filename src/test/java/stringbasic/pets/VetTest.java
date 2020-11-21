package stringbasic.pets;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VetTest {

    @Test
    public void testAddDifferent(){
        Vet vet = new Vet();
        vet.add(new Pet("Dog", LocalDate.of(2014,12,12),Gender.MALE,"000210"));
        vet.add(new Pet("Cat",LocalDate.of(2020,01,12),Gender.FEMALE,"000121"));
        assertEquals(2,vet.getPets().size());
    }

    @Test
    public void testAddSame() {
        Vet vet = new Vet();

        vet.add(new Pet("Dog", LocalDate.of(2014,12,12),Gender.MALE,"000210"));
        vet.add(new Pet("Cat",LocalDate.of(2020,01,12),Gender.FEMALE,"000210"));

        assertEquals(1, vet.getPets().size());
    }
}
