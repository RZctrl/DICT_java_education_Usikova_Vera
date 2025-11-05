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

    public double get(int row, int col) {
        return values[row][col];
    }

    public void set(int row, int col, double value) {
        values[row][col] = value;
    }

    public static Matrix read(Scanner scanner) {
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix matrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.set(i, j, scanner.nextDouble());
            }
        }


        return matrix;
    }

    public void print() {
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
                result.set(i, j, this.get(i, j) + other.get(i, j));
            }
        }
        return result;
    }

    public Matrix multiply(double constant) {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, this.get(i, j) * constant);
            }
        }
        return result;
    }

    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            return null;
        }

        Matrix result = new Matrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    public Matrix transposeMainD() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(j, i, this.get(i, j));
            }
        }
        return result;
    }

    public Matrix transposeSideD() {
        Matrix result = new Matrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(cols - 1 - j, rows - 1 - i, this.get(i, j));
            }
        }
        return result;
    }

    public Matrix transposeVertical() {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, cols - 1 - j, this.get(i, j));
            }
        }
        return result;
    }

    public Matrix transposeHorizontal() {
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(rows - 1 - i, j, this.get(i, j));
            }
        }
        return result;
    }

    public double determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException("Matrix must be square");
        }
        return calculateDet(this.values);
    }
    private double calculateDet(double[][] matrix) {
        int n = matrix.length;


        if (n == 1) {
            return matrix[0][0];
        }



        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;



        for (int j = 0; j < n; j++) {
            double[][] minor = getMinor(matrix, 0, j);
            double sign = (j % 2 == 0) ? 1 : -1;
            det += sign * matrix[0][j] * calculateDet(minor);
        }

        return det;
    }




    private double[][] getMinor(double[][] matrix, int skipRow, int skipCol) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];

        int minorRow = 0;
        for (int i = 0; i < n; i++) {
            if (i == skipRow) continue;

            int minorCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == skipCol) continue;

                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }

        return minor;
    }



    public boolean isSquare() {
        return rows == cols;
    }
}