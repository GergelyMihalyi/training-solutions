package week06d04;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items = new ArrayList<>();

    public void addItem(String name, int quantity) {
        Item item = existInList(name);
        if (item == null) {
            items.add(new Item(name, quantity));
        } else {
            item.addQuantity(quantity);
        }
    }

    private Item existInList(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public int getItem(String name) {
        Item item = existInList(name);
        int quantity = 0;
        if (item != null) {
            quantity = item.getQuantity();
        }
        return quantity;
    }

}
