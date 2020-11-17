package introconstructors;

public class RestaurantMain {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("La tre piatti" ,4);
        System.out.println("name: " + restaurant.getName() + " capacity: " + restaurant.getCapacity() + " menu: " + restaurant.getMenu());
    }
}
