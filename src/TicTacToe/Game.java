package TicTacToe;

import java.util.*;


public class Game {
    private GameBoard board;
    private GameAnalyzer analyzer;
    private Scanner scanner;

    public Game() {
        board = new GameBoard();
        analyzer = new GameAnalyzer(board);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();

        board.initializeFromString(input);
        board.printFormattedBoard();

        String gameState = analyzer.analyzeGameState();
        System.out.println(gameState);
    }
}