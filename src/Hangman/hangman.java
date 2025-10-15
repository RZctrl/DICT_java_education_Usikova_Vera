package Hangman;
import java.util.Random;
import java.util.Scanner;

public class hangman {
    private static final String[] WORDS = {"python", "java", "javascript", "kotlin"};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String secrWord = WORDS[random.nextInt(WORDS.length)];

        System.out.println("HANGMAN:begin");
        System.out.print("Guess the word: > ");
        String guess = scanner.nextLine();


        if (guess.equals(secrWord)) {
            System.out.println("You survived!");
        } else {
            System.out.println("You lost again!");
        }

        scanner.close();
    }
}
