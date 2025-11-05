package MatrixProcessing;

import java.util.*;

public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] values;

    public Matrix(int rows, int cols) {
        this(rows, cols, new double[rows][cols]);
    }

    public Matrix(int rows, int cols, double[][] values) {
        this.rows = rows;
        this.cols = cols;
        this.values = values;
    }


    public double getValue(int row, int col) {
        return values[row][col];
    }

    public void setValue(int row, int col, double value) {
        values[row][col] = value;
    }

    public static Matrix readMatrix(Scanner scanner) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix matrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setValue(i, j, scanner.nextDouble());
            }
        }

        return matrix;
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double value = values[i][j];

                if (Math.abs(value - Math.round(value)) < 1e-10) {
                    System.out.print((int) Math.round(value));
                } else {
                    System.out.print(value);
                }

                if (j < cols - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Matrix add(Matrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            return null;
        }

        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setValue(i, j, this.getValue(i, j) + other.getValue(i, j));
            }
        }
        return result;
    }

    public Matrix multiplyByConstant(double constant) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.setValue(i, j, this.getValue(i, j) * constant);
            }
        }
        return result;
    }
}