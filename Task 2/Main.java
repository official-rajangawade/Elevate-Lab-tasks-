package Student;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    int marks;
}

public class Main {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Students Menu ");
            System.out.println("1. Add Student");
            System.out.println("2. Show All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            if (choice == 1) addStudent();
            else if (choice == 2) showStudents();
            else if (choice == 3) updateStudent();
            else if (choice == 4) deleteStudent();
            else if (choice == 5) break;
            else System.out.println("Invalid input. Try again.");
        }
        System.out.println("Program ended.");
    }

    // Add student
    public static void addStudent() {
        Student s = new Student();
        System.out.print("Enter ID: ");
        s.id = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter Name: ");
        s.name = sc.nextLine();
        System.out.print("Enter Marks: ");
        s.marks = sc.nextInt();
        students.add(s);
        System.out.println("Student Added!");
    }

    // Show all students
    public static void showStudents() {
        if (students.size() == 0) {
            System.out.println("No students.");
            return;
        }
        for (Student s : students) {
            System.out.println("ID: " + s.id + ", Name: " + s.name + ", Marks: " + s.marks);
        }
    }

    // Update student
    public static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        for (Student s : students) {
            if (s.id == id) {
                sc.nextLine(); 
                System.out.print("Enter new name: ");
                s.name = sc.nextLine();
                System.out.print("Enter new marks: ");
                s.marks = sc.nextInt();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Delete student
    public static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                students.remove(i);
                System.out.println("Student deleted!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
}
