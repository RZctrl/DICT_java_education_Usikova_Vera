package RockPaperScissors;

import java.util.*;

interface Strategy {
    GameResult playRound(String userChoice);
}

class GameResult {
    private final String computerChoice;
    private final Result result;

    public GameResult(String computerChoice, Result result) {
        this.computerChoice = computerChoice;
        this.result = result;
    }

    public String getComputerChoice() {
        return computerChoice;
    }

    public Result getResult() {
        return result;
    }
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

class RockPaperScissorsGame {
    private final Strategy strategy;

    public RockPaperScissorsGame(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play(String userChoice) {
        try {
            GameResult result = strategy.playRound(userChoice);
            printResult(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter 'rock', 'paper', or 'scissors'.");
        }
    }

    private void printResult(GameResult result) {
        String compChoice = result.getComputerChoice();

        switch (result.getResult()) {
            case LOSE:
                System.out.println("Sorry, but the computer chose " + compChoice);
                break;
            case DRAW:
                System.out.println("There is a draw (" + compChoice + ")");
                break;
            case WIN:
                System.out.println("Well done. The computer chose " + compChoice + " and failed");
                break;
        }
    }
}

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Strategy strategy = new RandomChoice();
        RockPaperScissorsGame game = new RockPaperScissorsGame(strategy);

        System.out.print("> ");
        String userInput = scanner.nextLine().trim().toLowerCase();

        if (!isValidChoice(userInput)) {
            System.out.println("Invalid input. Please enter 'rock', 'paper', or 'scissors'.");
        } else {
            game.play(userInput);
        }

        scanner.close();
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }
}