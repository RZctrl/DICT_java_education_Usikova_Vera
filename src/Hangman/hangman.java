package Hangman;

import java.util.Random;
import java.util.Scanner;

public class hangman {
    private static final String[] WORDS = {"python", "java", "javascript", "kotlin"};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String secrWord = WORDS[random.nextInt(WORDS.length)];

        StringBuilder hint = new StringBuilder();
        for (int i = 0; i < secrWord.length(); i++) {
            if (i < 2) {
                hint.append(secrWord.charAt(i));
            } else {
                hint.append("-");
            }
        }


        System.out.println("HANGMAN2");
        System.out.print("Guess the word " + hint + ": > ");
        String guess = scanner.nextLine();


        if (guess.equals(secrWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost again!");
        }

        scanner.close();
    }
}
