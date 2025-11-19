package RockPaperScissors;

public class GameResult {
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