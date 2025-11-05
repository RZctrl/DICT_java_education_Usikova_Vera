package MatrixProcessing;

import java.util.*;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: > ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMatrices(scanner);
                    break;
                case 2:
                    multiplyByConstant(scanner);
                    break;
                case 3:
                    multiplyMatrices(scanner);
                    break;
                case 4:
                    transposeMatrix(scanner);
                    break;
                case 0:
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void addMatrices(Scanner scanner) {
        System.out.print("\nEnter size of first matrix: > ");
        Matrix matrix1 = Matrix.readMatrix(scanner);

        System.out.print("Enter size of second matrix: > ");
        Matrix matrix2 = Matrix.readMatrix(scanner);

        Matrix result = matrix1.add(matrix2);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("\nResult:");
            result.printMatrix();
        }
    }

    private static void multiplyByConstant(Scanner scanner) {
        System.out.print("\nEnter size of matrix: > ");
        Matrix matrix = Matrix.readMatrix(scanner);

        System.out.print("Enter constant: > ");
        double constant = scanner.nextDouble();

        Matrix result = matrix.multiplyByConstant(constant);

        System.out.println("\nResult:");
        result.printMatrix();
    }

    private static void multiplyMatrices(Scanner scanner) {
        System.out.print("\nEnter size of first matrix: > ");
        Matrix matrix1 = Matrix.readMatrix(scanner);

        System.out.print("Enter size of second matrix: > ");
        Matrix matrix2 = Matrix.readMatrix(scanner);

        Matrix result = matrix1.multiply(matrix2);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("\nResult:");
            result.printMatrix();
        }
    }

    private static void transposeMatrix(Scanner scanner) {
        System.out.println("\n1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: > ");

        int transposeChoice = scanner.nextInt();

        System.out.print("\nEnter matrix size: > ");
        Matrix matrix = Matrix.readMatrix(scanner);

        Matrix result;
        switch (transposeChoice) {
            case 1:
                result = matrix.transposeMainDiagonal();
                break;
            case 2:
                result = matrix.transposeSideDiagonal();
                break;
            case 3:
                result = matrix.transposeVertical();
                break;
            case 4:
                result = matrix.transposeHorizontal();
                break;
            default:
                System.out.println("\nInvalid choice");
                return;
        }

        System.out.println("\nResult:");
        result.printMatrix();
    }
}