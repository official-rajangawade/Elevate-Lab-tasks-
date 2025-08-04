package Task_1;

import java.util.Scanner;

public class Calculator {

  
    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return 0;
        } else {
            return a / b;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nCalculator");
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.println("Choose operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.print("Enter your choice (1-4): ");
            int option = scanner.nextInt();

            double result = 0;

            switch (option) {
                case 1:
                    result = add(num1, num2);
                    System.out.println("Answer is : " + result);
                    break;
                case 2:
                    result = subtract(num1, num2);
                    System.out.println("Answer is : " + result);
                    break;
                case 3:
                    result = multiply(num1, num2);
                    System.out.println("Answer is : " + result);
                    break;
                case 4:
                    result = divide(num1, num2);
                    System.out.println("Answer is : " + result);
                    break;
                default:
                    System.out.println("Sorry. Please enter 1 to 4.");
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            scanner.nextLine();  
            choice = scanner.nextLine();

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("Thank you for using the calculator!");
        scanner.close();
    }
}
