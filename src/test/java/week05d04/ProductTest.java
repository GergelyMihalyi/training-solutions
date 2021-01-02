package week05d04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProductTest {

    @Test
    void productTest(){
        Product product = new Product(100,Currency.USD);
        assertEquals(30000.00,product.convertPrice(Currency.HUF));
    }
}
