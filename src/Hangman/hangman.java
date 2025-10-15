package Hangman;

import java.util.*;

public class hangman {
    private static final String[] WORDS = {"python", "java", "javascript", "kotlin"};
    private static final int MAX_ATTEMPTS = 8;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("HANGMAN:final");
            System.out.println();
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: > ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if ("exit".equals(choice)) {
                break;
            } else if ("play".equals(choice)) {
                playGame(scanner);
            }
        }
        scanner.close();
    }

    private static void playGame(Scanner scanner) {

        Random random = new Random();

        String secrWord = WORDS[random.nextInt(WORDS.length)];
        Set<Character> guessLetters = new HashSet<>();
        Set<Character> incorrLetter = new HashSet<>();

        int attemptsLeft = MAX_ATTEMPTS;
        boolean wordGuess = false;

        System.out.println();
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
                System.out.println("You should input a single letter");
                continue;
            }




            char letter = input.charAt(0);

            if (!Character.isLowerCase(letter) || !Character.isLetter(letter)) {
                System.out.println("Please enter a lowercase English letter");
                continue;
            }

            if (guessLetters.contains(letter) || incorrLetter.contains(letter)) {
                System.out.println("You've already guessed this letter");
                continue;
            }


            if (secrWord.indexOf(letter) >= 0) {
                guessLetters.add(letter);
            } else {
                System.out.println("No, that letter doesn't exist in the word.");
                incorrLetter.add(letter);
                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            System.out.println();
        }

        if (wordGuess) {
            System.out.println();
            System.out.println("You won and survive! You guessed the word!");
            System.out.println();
            System.out.println("We'll see how well you did in the next stage...");
        } else {
            System.out.println("You lost again!");
        }



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