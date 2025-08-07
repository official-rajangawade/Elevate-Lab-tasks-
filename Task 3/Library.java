package Library;

import java.util.*;

public class Library {
    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void addUser(User u) {
        users.add(u);
    }

    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        if (book != null && !book.isIssued) {
            book.isIssued = true;
            System.out.println("Book issued to user ID " + userId);
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBook(bookId);
        if (book != null && book.isIssued) {
            book.isIssued = false;
            System.out.println("Book returned.");
        } else {
            System.out.println("Book was not issued.");
        }
    }

    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void showUsers() {
        for (User u : users) {
            System.out.println(u);
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id)
                return b;
        }
        return null;
    }
}
