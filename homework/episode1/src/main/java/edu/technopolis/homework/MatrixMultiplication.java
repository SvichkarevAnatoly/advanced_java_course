package edu.technopolis.homework;

import java.util.Scanner;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String... args) {
        final String input = "2 2 2 1 1 0 0 1 -1 -1";
        final Scanner sc = new Scanner(input);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        final int x = sc.nextInt();
        final int y = sc.nextInt();

        final int A[] = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            A[i] = sc.nextInt();
        }
        final Matrix matrix = new Matrix(A, n, m);
        System.out.println(matrix);
    }
}
