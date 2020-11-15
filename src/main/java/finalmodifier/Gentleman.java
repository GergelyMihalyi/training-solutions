package finalmodifier;

public class Gentleman {
    public static final String MESSAGE_PREFIX = "Hello ";

    public static void main(String[] args) {
        Gentleman gentleman = new Gentleman();
        System.out.printf(gentleman.sayHello("John"));

    }

    private String sayHello(String name){
        return MESSAGE_PREFIX + name;
    }

}
