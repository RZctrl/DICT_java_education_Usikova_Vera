package Hangman;

import java.util.*;

public class hangman {
    private static final String[] WORDS = {"python", "java", "javascript", "kotlin"};
    private static final int MAX_ATTEMPTS = 8;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String secrWord = WORDS[random.nextInt(WORDS.length)];

        Set<Character> guessLetters = new HashSet<>();
        int attemptsLeft = MAX_ATTEMPTS;
        boolean wordGuess = false;




        System.out.println("HANGMAN5");
        System.out.println("You have only 8 attempts...");
        System.out.println();


        while (attemptsLeft > 0 && !wordGuess) {
            displayWord(secrWord, guessLetters);

            if (isWordGuessed(secrWord, guessLetters)) {
                wordGuess = true;
                break;
            }

            System.out.print("Input a letter: > ");
            String input = scanner.nextLine().trim();

            if (input.length() != 1) {
                continue;
            }

            char letter = input.charAt(0);

            if (secrWord.indexOf(letter) >= 0) {
                if (guessLetters.contains(letter)) {
                    System.out.println("No improvements");
                    attemptsLeft--;
                } else {
                    guessLetters.add(letter);
                }
            } else {
                System.out.println("No, that letter doesn't need here.");
                attemptsLeft--;
            }

            System.out.println();
        }

        if (wordGuess) {
            System.out.println();
            System.out.println("You won and survive!");
            System.out.println("Thanks for playing!");
            System.out.println();
            System.out.println("We'll see how well you did in the next stage...");
        } else {
            System.out.println("You lost again!");
        }


        scanner.close();
    }

    private static void displayWord(String word, Set<Character> guessLetters) {
        for (char c : word.toCharArray()) {
            if (guessLetters.contains(c)) {
                System.out.print(c);
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    private static boolean isWordGuessed(String word, Set<Character> guessLetters) {
        for (char c : word.toCharArray()) {
            if (!guessLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }
}