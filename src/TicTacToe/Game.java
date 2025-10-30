package TicTacToe;

import java.util.*;


public class Game {
    private GameBoard board;
    private GameAnalyzer analyzer;
    private Scanner input;
    private char activePlayer;

    public Game() {
        board = new GameBoard();
        analyzer = new GameAnalyzer(board);
        input  = new Scanner(System.in);
        activePlayer  = 'X';
    }

    public void start() {
        board.setupEmpty();
        board.showBoard();

        while (true) {
            System.out.print("Enter the coordinates: ");
            String userInput  = input.nextLine();

            if (!makeMove(userInput)) {
                continue;
            }



            board.showBoard();


            String result  = analyzer.checkGameState();
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;

            }


            switchPlayer();
        }
    }

    private boolean makeMove(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length != 2) {
                System.out.println("You should enter two numbers!");
                return false;
            }

            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);


            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                return false;
            }


            int boardRow = row - 1;
            int boardCol = col - 1;


            if (board.getCell(boardRow, boardCol) != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                return false;
            }


            board.setCell(boardRow, boardCol, activePlayer );
            return true;

        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return false;
        }
    }

    private void switchPlayer() {
        activePlayer  = (activePlayer  == 'X') ? 'O' : 'X';
    }
}