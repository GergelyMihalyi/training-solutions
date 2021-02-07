package week12d01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoodsPacker {
    private List<Item> items = new ArrayList<>();
    private List<Item> itemsInBag = new ArrayList<>();

    public int packGoods(int[][] types, int capacity) {
        for (int itemData[] : types) {
            Item item = new Item(itemData[0], itemData[1]);
            items.add(item);
        }
        itemsSetInBag(capacity);
        return maxPriceInBag();
    }

    private void itemsSetInBag(int capacity) {
        for (Item item : items) {
            item.setItemInBag(capacity);
        }
        Collections.sort(items);
        if (items.get(0).getQuantityInBag() > 0) {
            for (int i = 0; i < items.get(0).getQuantityInBag(); i++) {
                itemsInBag.add(items.get(0));
                capacity -= items.get(0).getWeight();
                itemsSetInBag(capacity);
            }

        }
    }

    private int maxPriceInBag() {
        int price = 0;
        for (Item item : itemsInBag) {
            price += item.getPrice();
        }
        return price;
    }

    public static void main(String[] args) {
        System.out.println(new GoodsPacker().packGoods(new int[][]{{7, 160}, {3, 90}, {2, 15}}, 20));
    }
}
