package week06d02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoreTest {

    @Test
    void testStore(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("product1",Category.OTHER,10));
        products.add(new Product("product2",Category.BAKEDGOODS,10));
        products.add(new Product("product3",Category.OTHER,10));
        Store store = new Store(products);
        List<Category> productByCategory = store.getProductsByCategory();
        Category category = productByCategory.get(productByCategory.indexOf(Category.OTHER));
        assertEquals(2,category.getNumber());

    }
}
