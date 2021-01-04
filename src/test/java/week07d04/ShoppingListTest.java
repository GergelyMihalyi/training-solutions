package week07d04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingListTest {

    @Test
    void calculateSumTest() {
        assertEquals(320,new ShoppingList().calculateSum());
    }
}