package src.edu.technopolis.homework;

public class Matrix {
    private int M[][];
    private int n, m;

    public Matrix(int n, int m) {
        // TODO: check positive n, m
        M = new int[n][m];
    }

    public Matrix(int A[][]) {
        M = A;
        // suppose has correct dimension
        n = A.length;
        m = A[0].length;
    }

    public Matrix(int A[], int n, int m) {
        // TODO: check n, m
        M = new int[n][m];
        this.n = n;
        this.m = m;
        for (int i = 0; i < n; i++) {
            System.arraycopy(A, i * m, M[i], 0, m);
        }
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

    public Matrix multiply(Matrix second) {
        // TODO: check second
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

    private void setElement(int i, int j, int value) {
        // TODO: check i, j
        M[i][j] = value;
    }
}
