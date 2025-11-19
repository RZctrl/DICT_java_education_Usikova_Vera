package RockPaperScissors;

public class InputValidator {
    public static boolean isValidChoice(String input) {
        return input.equals("rock") || input.equals("paper") || input.equals("scissors");
    }

    public static boolean isExit(String input) {
        return input.equals("!exit");
    }

    public static boolean isRating(String input) {
        return input.equals("!rating");
    }
}