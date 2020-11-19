package introexception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PatientTest {

    @Test
    public void inValidPatient() {
        assertThrows(IllegalArgumentException.class, () -> new Patient("John Doe", "abc", 1845));
        assertThrows(IllegalArgumentException.class, () -> new Patient("", "abc", 1901));
    }
     @Test
    public void validPatient() {
        Patient patient = new Patient("John Doe", "123456788", 2000);
        assertEquals("John Doe", patient.getName());
        assertEquals("123456788", patient.getSocialSecurityNumber());
        assertEquals(2000, patient.getYearOfBirth());
    }
}
