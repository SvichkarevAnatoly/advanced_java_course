package src.edu.technopolis.homework;

import java.math.BigInteger;
import java.util.StringJoiner;

public class BigMatrix {
    private BigInteger[][] M;

    public BigMatrix(int n, int m) {
        M = new BigInteger[n][m];
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        for (BigInteger[] A : M) {
            final StringJoiner sjRow = new StringJoiner("\t");
            for (BigInteger aij : A) {
                sjRow.add(aij.toString());
            }
            sj.add(sjRow.toString());
        }
        return sj.toString();
    }

    public void setElement(int i, int j, BigInteger newValue) {
        M[i][j] = newValue;
    }
}
