package MatrixProcessing;

import java.util.*;

public class MatrixProcessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Matrix matrixA = Matrix.readMatrix(scanner);

        Matrix matrixB = Matrix.readMatrix(scanner);


        Matrix result = matrixA.add(matrixB);


        if (result != null) {
            result.printMatrix();
        } else {
            System.out.println("ERROR");
        }

        scanner.close();
    }
}