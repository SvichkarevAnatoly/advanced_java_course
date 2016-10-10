package edu.technopolis.homework;

public class Matrix {
    private int M[][];
    private int n, m;

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
}
