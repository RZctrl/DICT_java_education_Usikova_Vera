package RockPaperScissors;

public class SmthGameRate {
    private Strategy strategy;
    private Rating ratingManager;
    private String player;
    private int playerScore;

    public SmthGameRate(Strategy strategy, Rating ratingManager, String playerName) {
        this.strategy = strategy;
        this.ratingManager = ratingManager;
        this.player = playerName;
        this.playerScore = ratingManager.getScore(playerName);
    }

    public void playRound(String userChoice) {
        try {
            GameResult result = strategy.playRound(userChoice);
            showResult(result);
            updateScore(result.getResult());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input");
        }
    }

    private void showResult(GameResult result) {
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

    private void updateScore(Result result) {
        int points = 0;
        switch (result) {
            case DRAW:
                points = 50;
                break;
            case WIN:
                points = 100;
                break;
            case LOSE:
                points = 0;
                break;
        }

        if (points > 0) {
            ratingManager.updateScore(player, points);
            playerScore += points;
        }
    }

    public void showScore() {
        System.out.println("Your rating: " + playerScore);
    }

    public String getPlayer() {
        return player;
    }
}
