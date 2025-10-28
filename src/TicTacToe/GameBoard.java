package TicTacToe;

public class GameBoard {
    private char[][] grid;

    public GameBoard() {
        grid = new char[3][3];
    }


    public void initializeFromString(String input) {
        if (input.length() != 9) {
            return;
        }

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char symbol = input.charAt(index++);
                if (symbol != 'X' && symbol != 'O' && symbol != '_') {
                    symbol = '_';
                }
                grid[i][j] = symbol;
            }
        }
    }





    public void printFormattedBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }



    public char getCell(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return grid[row][col];
        }
        return ' ';
    }



    public void setCell(int row, int col, char value) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            grid[row][col] = value;
        }
    }




    public int countSymbol(char symbol) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == symbol) {
                    count++;
                }
            }
        }
        return count;
    }



    public boolean hasEmptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;
    }



    public boolean hasWinningLine(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol) {
                return true;
            }
        }

        if (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) {
            return true;
        }
        if (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol) {
            return true;
        }

        return false;
    }
}

class GameAnalyzer {
    private GameBoard board;

    public GameAnalyzer(GameBoard board) {
        this.board = board;
    }

    public String analyzeGameState() {
        int xCount = board.countSymbol('X');
        int oCount = board.countSymbol('O');
        int difference = Math.abs(xCount - oCount);

        boolean xWins = board.hasWinningLine('X');
        boolean oWins = board.hasWinningLine('O');
        boolean hasEmptyCells = board.hasEmptyCells();

        if (difference >= 2 || (xWins && oWins)) {
            return "Impossible";
        }

        if (xWins) {
            return "X wins";
        }
        if (oWins) {
            return "O wins";
        }

        if (!hasEmptyCells) {
            return "Draw";
        }

        return "Game not finished";
    }
}