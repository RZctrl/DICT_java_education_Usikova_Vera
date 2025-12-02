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
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: > ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addMatrices(scanner);
                    break;
                case 2:
                    multiply(scanner);
                    break;
                case 3:
                    multiplyMatr(scanner);
                    break;
                case 4:
                    transposeMatrix(scanner);
                    break;
                case 5:
                    calculateDeterminant(scanner);
                    break;
                case 6:
                    inverseMatrix(scanner);
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
        Matrix matrix1 = Matrix.read(scanner);

        System.out.print("Enter size of second matrix: > ");
        Matrix matrix2 = Matrix.read(scanner);

        Matrix result = matrix1.add(matrix2);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("\nResult:");
            result.print();
        }
    }

    private static void multiply(Scanner scanner) {
        System.out.print("\nEnter size of matrix: > ");
        Matrix matrix = Matrix.read(scanner);

        System.out.print("Enter constant: > ");
        double constant = scanner.nextDouble();

        Matrix result = matrix.multiply(constant);

        System.out.println("\nResult:");
        result.print();
    }

    private static void multiplyMatr(Scanner scanner) {
        System.out.print("\nEnter size of first matrix: > ");
        Matrix matrix1 = Matrix.read(scanner);

        System.out.print("Enter size of second matrix: > ");
        Matrix matrix2 = Matrix.read(scanner);

        Matrix result = matrix1.multiply(matrix2);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("\nResult:");
            result.print();
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
        Matrix matrix = Matrix.read(scanner);

        Matrix result;
        switch (transposeChoice) {
            case 1:
                result = matrix.transposeMainD();
                break;
            case 2:
                result = matrix.transposeSideD();
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
        result.print();
    }
    private static void calculateDeterminant(Scanner scanner) {
        System.out.print("Enter matrix size: > ");
        Matrix matrix = Matrix.read(scanner);

        if (!matrix.isSquare()) {
            System.out.println("Matrix must be square.");
            return;
        }

        try {
            double determinant = matrix.determinant();

            if (Math.abs(determinant - Math.round(determinant)) < 1e-10) {
                System.out.println("\nResult:");
                System.out.println((int) Math.round(determinant));
            } else {
                System.out.println("\nResult:");
                System.out.println(determinant);
            }
        } catch (Exception e) {
            System.out.println("Error calculating determinant.");
        }
    }



    private static void inverseMatrix(Scanner scanner) {
        System.out.print("Enter matrix size: > ");
        Matrix matrix = Matrix.read(scanner);

        if (!matrix.isSquare()) {
            System.out.println("Matrix must be square.");
            return;
        }

        try {
            Matrix inverse = matrix.inverse();
            System.out.println("\nResult:");
            inverse.print();
        } catch (ArithmeticException e) {
            System.out.println("This matrix doesn't have an inverse.");
        } catch (Exception e) {
            System.out.println("Error calculating inverse matrix.");
        }
    }
}