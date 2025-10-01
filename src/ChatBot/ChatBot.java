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

        scanner.close();
    }
}
