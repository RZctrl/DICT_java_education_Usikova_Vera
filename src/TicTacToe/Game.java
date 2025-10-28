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

        makeUserMove();
        board.printFormattedBoard();
    }

    private void makeUserMove() {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();

            try {
                String[] parts = coordinates.split(" ");
                if (parts.length != 2) {
                    System.out.println("You should enter two numbers!");
                    continue;
                }

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }


                int boardRow = 3 - row;
                int boardCol = col - 1;



                if (board.getCell(boardRow, boardCol) != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }



                board.setCell(boardRow, boardCol, 'X');
                break;

            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }
}