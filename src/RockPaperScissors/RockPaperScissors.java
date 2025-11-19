package RockPaperScissors;

import java.util.*;

interface Strategy {
    String ComputerChoice(String userChoice);
}

class AlwaysWin implements Strategy {
    @Override
    public String ComputerChoice(String userChoice) {
        switch (userChoice.toLowerCase()) {
            case "rock":
                return "paper";
            case "paper":
                return "scissors";
            case "scissors":
                return "rock";
            default:
                throw new IllegalArgumentException("Invalid choice: " + userChoice);
        }
    }
}

class RockPaperScissorsGame {
    private Strategy strategy;

    public RockPaperScissorsGame(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play(String userChoice) {
        String computerChoice = strategy.ComputerChoice(userChoice);
        System.out.println("Sorry, but the computer chose " + computerChoice);
    }
}

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Strategy strategy = new AlwaysWin();
        RockPaperScissorsGame game = new RockPaperScissorsGame(strategy);

        System.out.print("> ");
        String userChoice = scanner.nextLine().trim();

        try {
            game.play(userChoice);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter 'rock', 'paper', or 'scissors'.");
        }

        scanner.close();
    }
}