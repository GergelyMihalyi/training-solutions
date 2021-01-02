package week06d04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppinCartTest {

    @Test
    void testShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem("alma", 4);
        shoppingCart.addItem("alma", 4);
        assertEquals(8, shoppingCart.getItem("alma"));
    }
}
