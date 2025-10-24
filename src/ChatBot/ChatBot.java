package ChatBot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Botik";
        int birthYear = 2025;

        System.out.println("Hello! My name's " + botName + ".");
        System.out.println("I was created in " + birthYear + ".");
        System.out.println("What's ur name?");
        String userName = scanner.nextLine();

        System.out.println("Nice to meet u, " + userName + "!");
        System.out.println("Let me guess ur age!");
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;

        System.out.println("Your age is " + age + "; that's a good time to take a nap!");
        System.out.println("Now I will prove to u that I can count to any number you want!");
        int number = scanner.nextInt();

        for (int i = 0; i <= number; i++) {
            System.out.println(i + "!");
        }

        System.out.println("Hmm... Let's test your programming knowledge.");
        System.out.println("Why are methods used in Java?");
        System.out.println("1. To make code longer and more complicated.");
        System.out.println("2. To repeat the same code multiple times.");
        System.out.println("3. To organize code into reusable blocks.");
        System.out.println("4. Because it's required by the compiler.");

        int answer;
        boolean correctAnswer = false;

        while (!correctAnswer) {
            answer = scanner.nextInt();
            scanner.nextLine();

            if (answer == 3) {
                correctAnswer = true;
                System.out.println("Congratulations, that's correct!");
            } else {
                System.out.println("Nope, try again.");
            }
        }

        System.out.println("Goodbye, have a nice day!");

        scanner.close();
    }
}
