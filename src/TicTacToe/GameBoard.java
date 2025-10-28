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
}