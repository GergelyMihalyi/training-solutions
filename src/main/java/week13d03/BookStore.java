package week13d03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore {

    public String findAuthorWithMaxPage(List<Book> books) {
        if (books.isEmpty()) {
            throw new IllegalArgumentException("No book found");
        }
        Map<String, Integer> authorsWithPageNumbers = new HashMap<>();
        for (Book book : books) {
            String author = book.getAuthor();
            if (!authorsWithPageNumbers.containsKey(author)) {
                authorsWithPageNumbers.put(author, book.getNumberOfPages());
            } else {
                int sum = authorsWithPageNumbers.get(author);
                sum = sum + book.getNumberOfPages();
                authorsWithPageNumbers.put(author, sum);
            }
        }
        int maxNumberOfPages = 0;
        String author = null;
        for (Map.Entry<String, Integer> entry : authorsWithPageNumbers.entrySet()) {
            if (entry.getValue() > maxNumberOfPages) {
                maxNumberOfPages = entry.getValue();
                author = entry.getKey();
            }
        }
        return author;
    }

}
