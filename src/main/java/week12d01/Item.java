package week12d01;

public class Item implements Comparable<Item>{
    private int weight;
    private int price;

    private int priceInBag;
    private int quantityInBag;

    public Item(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }



    public int getQuantityInBag() {
        return quantityInBag;
    }

    public void setItemInBag(int capacity) {
        priceInBag = (capacity / weight) * price;
        quantityInBag = capacity / weight;
    }

    @Override
    public int compareTo(Item o) {
        return (int) (o.priceInBag - priceInBag);
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", price=" + price +
                '}';
    }
}
