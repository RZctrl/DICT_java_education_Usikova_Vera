package RockPaperScissors;

public class InputValidator {
    public static boolean isValidChoice(String input, String[] validOptions) {
        for (String option : validOptions) {
            if (option.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExit(String input) {
        return "!exit".equals(input);
    }

    public static boolean isRating(String input) {
        return "!rating".equals(input);
    }
}