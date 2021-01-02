package week05d04;

public class Product {
    private long price;
    private Currency currency;

    public Product(long price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public double convertPrice(Currency currency) {
        double price = this.price;
        if(this.currency != currency && currency.name() == "HUF"){
            price = this.price * Currency.USD.getMultiplier();
        }
        else if(this.currency != currency && currency.name() == "USD"){
            price = this.price / Currency.USD.getMultiplier();
        }else{
        }


        return price;
    }

    public static void main(String[] args) {
        Product product = new Product(100, Currency.USD);
        System.out.println(product.convertPrice(Currency.HUF));
    }
}
