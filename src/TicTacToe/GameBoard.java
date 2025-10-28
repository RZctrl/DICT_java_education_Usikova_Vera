package TicTacToe;

public class GameBoard {
    private char[][] cells;



    public GameBoard() {
        cells = new char[3][3];
    }



    public void setupEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = '_';
            }
        }
    }


    public void setupFromString(String startingPosition) {
        if (startingPosition.length() != 9) {
            return;
        }

        int pos = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char symbol = startingPosition.charAt(pos++);
                if (symbol != 'X' && symbol != 'O' && symbol != '_') {
                    symbol = '_';
                }
                cells[i][j] = symbol;
            }
        }
    }



    public void showBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }



    public char getCell(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return cells[row][col];
        }
        return ' ';
    }

    public void setCell(int row, int col, char value) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            cells[row][col] = value;
        }
    }


    public int countSymbol(char symbol) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == symbol) {
                    total++;
                }
            }
        }
        return total;
    }


    public boolean hasEmptyCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasWinner(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == symbol && cells[i][1] == symbol && cells[i][2] == symbol) {
                return true;
            }
        }


        for (int j = 0; j < 3; j++) {
            if (cells[0][j] == symbol && cells[1][j] == symbol && cells[2][j] == symbol) {
                return true;
            }
        }


        if (cells[0][0] == symbol && cells[1][1] == symbol && cells[2][2] == symbol) {
            return true;
        }
        if (cells[0][2] == symbol && cells[1][1] == symbol && cells[2][0] == symbol) {
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

    public String checkGameState() {
        int xCount = board.countSymbol('X');
        int oCount = board.countSymbol('O');
        int difference = Math.abs(xCount - oCount);

        boolean xWon = board.hasWinner('X');
        boolean oWon = board.hasWinner('O');
        boolean hasEmptyCells = board.hasEmptyCells();

        if (difference >= 2 || (xWon && oWon)) {
            return "Impossible";
        }

        if (xWon) {
            return "X wins";
        }
        if (oWon) {
            return "O wins";
        }

        if (!hasEmptyCells) {
            return "Draw";
        }

        return "Game not finished";
    }
}