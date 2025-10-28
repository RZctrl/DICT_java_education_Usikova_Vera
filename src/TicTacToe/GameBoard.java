package TicTacToe;

public class GameBoard {
    private char[][] grid;

    public GameBoard() {
        grid = new char[3][3];
        initializeBoard();
    }


    private void initializeBoard() {

        grid[0][0] = 'X';
        grid[0][1] = 'O';
        grid[0][2] = 'X';

        grid[1][0] = 'O';
        grid[1][1] = 'X';
        grid[1][2] = 'O';


        grid[2][0] = 'X';
        grid[2][1] = 'X';
        grid[2][2] = 'O';
    }




    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j < 2) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
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