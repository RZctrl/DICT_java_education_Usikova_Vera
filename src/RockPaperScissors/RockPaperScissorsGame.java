package RockPaperScissors;

public class RockPaperScissorsGame {
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