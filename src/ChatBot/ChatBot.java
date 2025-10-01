package ChatBot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Botik";
        int birthYear = 2025;

        System.out.println("Hello! My name is " + botName + ".");
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

        scanner.close();
    }
}
