package src.edu.technopolis.homework;

import java.math.BigInteger;
import java.util.StringJoiner;

public class Matrix {
    private int M[][];
    private int n, m;

    public Matrix(int n, int m) {
        checkDimensions(n, m);
        M = new int[n][m];
    }

    public Matrix(int A[][]) {
        checkArrayDimensions(A);
        M = A;
        n = A.length;
        m = A[0].length;
    }

    public Matrix(int A[], int n, int m) {
        checkDimensions(n, m);
        if (A.length != n * m) {
            throw new IllegalArgumentException(
                    "Array must have enough elements");
        }

        M = new int[n][m];
        this.n = n;
        this.m = m;
        for (int i = 0; i < n; i++) {
            System.arraycopy(A, i * m, M[i], 0, m);
        }
    }

    public BigMatrix multiply(Matrix second) {
        final BigMatrix result = new BigMatrix(n, second.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < second.m; j++) {
                BigInteger scalarProduct = BigInteger.ZERO;
                for (int k = 0; k < m; k++) {
                    final BigInteger Mik = BigInteger.valueOf(M[i][k]);
                    final BigInteger Mkj = BigInteger.valueOf(second.M[k][j]);
                    final BigInteger product = Mik.multiply(Mkj);
                    scalarProduct = scalarProduct.add(product);
                }
                result.setElement(i, j, scalarProduct);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        for (int A[] : M) {
            final StringJoiner sjRow = new StringJoiner("\t");
            for (int aij : A) {
                sjRow.add(String.valueOf(aij));
            }
            sj.add(sjRow.toString());
        }
        return sj.toString();
    }

    public int[][] getArray() {
        return M.clone();
    }

    private void checkDimensions(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException(
                    "Dimensions must be positive numbers");
        }
    }

    private void checkArrayDimensions(int[][] A) {
        if (A == null) {
            throw new IllegalArgumentException(
                    "Array must be correct matrix");
        }
        int m = A[0].length;
        for (int[] row : A) {
            if (row.length != m) {
                throw new IllegalArgumentException(
                        "Array must be correct matrix");
            }
        }
    }

    private void setElement(int i, int j, int value) {
        M[i][j] = value;
    }
}
