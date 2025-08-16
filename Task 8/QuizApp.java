package Quiz;

import java.util.*;

class Question {
    String question;
    String[] options;
    int correctOption;

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public boolean ask(Scanner scanner) {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your answer (1-4): ");

        int userAnswer = 0;
        try {
            userAnswer = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            return false;
        }

        return userAnswer - 1 == correctOption;
    }
}

public class QuizApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
                "Who invented Java Programming?",
                new String[]{"Guido van Rossum", "Dennis Ritchie", "James Gosling", "Romeo"},
                2
        ));
        questions.add(new Question(
                "Which one of the following is not a Java feature?",
                new String[]{"Object-oriented", " Use of pointers", "Portable", "Dynamic and Extensible"},
                1
        ));
        questions.add(new Question(
                "What is the extension of java code files?",
                new String[]{".js", ".java", ".class", ".txt"},
                1
        ));
        questions.add(new Question(
                "Which of the following is NOT a primitive data type in Java?",
                new String[]{"String", "boolean", "int", "float"},
                0
        ));
        questions.add(new Question(
                "Which of the following is not a Java access modifier?",
                new String[]{"package", "protected", "private", "public"},
                0
        ));

        int score = 0;
        for (Question q : questions) {
            if (q.ask(scanner)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
        }

        System.out.println("\nQuiz completed! Your score: " + score + "/" + questions.size());
        scanner.close();
    }
}
