package RockPaperScissors;

import java.util.*;

interface Strategy {
    GameResult playRound(String userChoice);
}



enum Result {
    WIN, LOSE, DRAW
}

class RandomChoice implements Strategy {
    private final Random random;
    private String[] options;

    public RandomChoice(String[] options) {
        this.random = new Random();
        this.options = options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public GameResult playRound(String userChoice) {
        String compChoice = getRandomChoice();
        Result result = getResult(userChoice, compChoice);
        return new GameResult(compChoice, result);
    }

    private String getRandomChoice() {
        int index = random.nextInt(options.length);
        return options[index];
    }

    private Result getResult(String user, String comp) {
        if (user.equals(comp)) {
            return Result.DRAW;
        }

        List<String> optionsList = Arrays.asList(options);
        int userIndex = optionsList.indexOf(user);
        int compIndex = optionsList.indexOf(comp);

        int half = options.length / 2;
        Set<String> losOptions = new HashSet<>();

        for (int i = 1; i <= half; i++) {
            int losIndex = (userIndex + i) % options.length;
            losOptions.add(options[losIndex]);
        }
        if (losOptions.contains(comp)) {
            return Result.WIN;
        } else {
            return Result.LOSE;
        }
    }
}



public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();
        Rating rating = new Rating("rating.txt");


        String[] defOptions = {"rock", "paper", "scissors"};
        Strategy strategy = new RandomChoice(defOptions);
        SmthGameRate game = new SmthGameRate(strategy, rating, playerName);

        System.out.println("Hello, " + playerName);
        System.out.println("Enter game options (comma-separated) or empty for default:");
        String optionsInput = scanner.nextLine().trim();

        String[] gameOptions;
        if (optionsInput.isEmpty()) {
            gameOptions = defOptions;
            System.out.println("Using default options: rock, paper, scissors");
        } else {
            gameOptions = optionsInput.split("\\s*,\\s*");
            for (int i = 0; i < gameOptions.length; i++) {
                gameOptions[i] = gameOptions[i].toLowerCase().trim();
            }
            ((RandomChoice) strategy).setOptions(gameOptions);
            System.out.println("Using custom options: " + String.join(", ", gameOptions));
        }


        System.out.println("\nOkay, let's start");
        System.out.println("Rock-Paper-Scissors. Enter your choice or '!exit' to quit.");
        System.out.println("Available options: " + String.join(", ", gameOptions));




        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (InputValidator.isExit(userInput)) {
                System.out.println("Bye!");
                break;
            } else if (InputValidator.isRating(userInput)) {
                game.showScore();
            } else if (InputValidator.isValidChoice(userInput, gameOptions)) {
                game.playRound(userInput);
            } else {
                System.out.println("Invalid input");
            }
        }
        scanner.close();
    }
}