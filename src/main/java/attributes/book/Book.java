package attributes.book;

public class Book {

    private String title;

    public static void main(String[] args) {
        Book book = new Book("My first book");
        System.out.println(book.getTitle());
        book.setTitle("My second book");
        System.out.println(book.getTitle());

    }
    public Book(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
