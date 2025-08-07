package Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        // Add some books and users manually
        lib.addBook(new Book(1, "BAcis Java", "XYZ"));
        lib.addBook(new Book(2, "Spring Boot", "Abc"));
        lib.addBook(new Book(3, "The Software Developer", "Monu"));
        lib.addBook(new Book(4, "J2EE Fundamentals", "Sonu"));
        lib.addUser(new User(1, "Rajan Gawade"));
        lib.addUser(new User(2, "Sunil Patil"));

        int choice;

        do {
            System.out.println("\nLibrary Menu");
            System.out.println("1. Show Books");
            System.out.println("2. Show Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    lib.showBooks();
                    break;
                case 2:
                    lib.showUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    lib.issueBook(bookId, userId);
                    break;
                case 4:
                    System.out.print("Enter Book ID: ");
                    int returnId = sc.nextInt();
                    lib.returnBook(returnId);
                    break;
                case 5:
                    System.out.println("Thank You !");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
