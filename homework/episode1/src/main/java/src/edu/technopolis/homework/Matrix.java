package src.edu.technopolis.homework;

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

    public Matrix multiply(Matrix second) {
        final Matrix result = new Matrix(n, second.m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < second.m; j++) {
                int scalarProduct = 0;
                for (int k = 0; k < m; k++) {
                    scalarProduct += M[i][k] * second.M[k][j];
                }
                result.setElement(i, j, scalarProduct);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int A[] : M) {
            for (int aij : A) {
                sb.append(aij).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
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
