package objects;

public class ObjectsMain {


    public static void main(String[] args) {
        Book emptyBook = null;

        System.out.println(new Book());
        System.out.println(emptyBook);

        if (emptyBook == null) {
            System.out.println(emptyBook);
        }
        Book book = new Book();
        System.out.println(book);

        Book anotherBook = new Book();

        System.out.println(book == anotherBook);
        System.out.println(anotherBook instanceof Book);
    }
}
