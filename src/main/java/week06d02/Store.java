package week06d02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    List<Product> products;
    List<Category> categories;

    public Store(List<Product> products) {
        this.products = products;
        categories = Arrays.asList(Category.values());
    }

    public List<Category> getProductsByCategory(){
        for(Product product: products){
            int ordinal = product.getCategory().ordinal();
            categories.get(ordinal).incNumber();
        }
        return List.copyOf(categories);
    }

}
