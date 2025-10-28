package TicTacToe;

import java.util.*;


public class Game {
    private GameBoard board;
    private Scanner scanner;

    public Game() {
        board = new GameBoard();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();

        board.initializeFromString(input);
        board.printFormattedBoard();
    }
}