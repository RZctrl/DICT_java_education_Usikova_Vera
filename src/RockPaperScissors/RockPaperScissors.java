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
    private final String[] options = {"rock", "paper", "scissors"};

    public RandomChoice() {
        this.random = new Random();
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

        boolean userWins = (user.equals("rock") && comp.equals("scissors")) ||
                (user.equals("paper") && comp.equals("rock")) ||
                (user.equals("scissors") && comp.equals("paper"));

        return userWins ? Result.WIN : Result.LOSE;
    }
}



public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine().trim();
        Rating rating = new Rating("rating.txt");


        Strategy strategy = new RandomChoice();
        SmthGameRate game = new SmthGameRate(strategy, rating, playerName);

        System.out.println("Hello, " + playerName);
        System.out.println("\nRock-Paper-Scissors. Enter your choice or '!exit' to quit.");



        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (InputValidator.isExit(userInput)) {
                System.out.println("Bye!");
                break;
            } else if (InputValidator.isRating(userInput)) {
                game.showScore();
            } else if (InputValidator.isValidChoice(userInput)) {
                game.playRound(userInput);
            } else {
                System.out.println("Invalid input");
            }
        }
        scanner.close();
    }
}