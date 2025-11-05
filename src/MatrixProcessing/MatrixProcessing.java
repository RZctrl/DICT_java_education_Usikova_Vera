package MatrixProcessing;

import java.util.*;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter size of matrix: > ");
        Matrix matrix = Matrix.readMatrix(scanner);

        System.out.print("Enter constant: > ");
        double constant = scanner.nextDouble();

        Matrix result = matrix.multiplyByConstant(constant);

        System.out.println("Result:");
        result.printMatrix();

        scanner.close();
    }
}