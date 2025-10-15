package Hangman;

import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("HANGMAN:begin");
        System.out.print("Guess the word: > ");
        String guess = scanner.nextLine();

        String secrWord = "java";

        if (guess.equals(secrWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost!");
        }

        scanner.close();
    }
}
