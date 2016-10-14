package src.edu.technopolis.homework;

import java.util.Arrays;

public class MatrixParser {
    private static final int NUMBER_OF_DIM = 4;

    private int inputNumbers[];
    private Matrix A;
    private Matrix B;

    public void parse(String input[]) {
        castToNumbers(input);
        check();
        transformToMatrix();
    }

    public Matrix getA() {
        if (A == null) {
            throw new IllegalStateException("Matrix A not initialized");
        }
        return A;
    }

    public Matrix getB() {
        if (B == null) {
            throw new IllegalStateException("Matrix B not initialized");
        }
        return B;
    }

    private void transformToMatrix() {
        final int n = inputNumbers[0];
        final int m = inputNumbers[1];
        final int x = inputNumbers[2];
        final int y = inputNumbers[3];

        final int arrA[] = new int[n * m];
        System.arraycopy(inputNumbers, NUMBER_OF_DIM,
                arrA, 0, n * m);
        A = new Matrix(arrA, n, m);

        final int arrB[] = new int[x * y];
        System.arraycopy(inputNumbers, NUMBER_OF_DIM + n * m,
                arrB, 0, x * y);
        B = new Matrix(arrB, x, y);
    }

    private void check() {
        if (inputNumbers.length < NUMBER_OF_DIM + 2) {
            throw new IllegalArgumentException(
                    "Number of arguments must be greater than 5: " +
                            "4 dimensions and at least 2 scalars");
        }
        for (int i = 0; i < NUMBER_OF_DIM; i++) {
            if (inputNumbers[i] <= 0) {
                throw new IllegalArgumentException(
                        "Dimensions must be positive number");
            }
        }
        if (inputNumbers[0] * inputNumbers[1] + inputNumbers[2] * inputNumbers[3]
                + NUMBER_OF_DIM != inputNumbers.length) {
            throw new IllegalArgumentException(
                    "Number of args must be correct with dimensions");
        }
    }

    private void castToNumbers(String[] input) {
        try {
            inputNumbers = Arrays.asList(input)
                    .stream()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Could not cast args to numbers");
        }
    }
}
