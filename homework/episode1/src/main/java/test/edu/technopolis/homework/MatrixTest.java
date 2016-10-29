package test.edu.technopolis.homework;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.edu.technopolis.homework.BigMatrix;
import src.edu.technopolis.homework.Matrix;

import static org.junit.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void EAndVectorReturnString() {
        final int arrA[][] = {{1, 0}, {0, 1}};
        final int arrB[][] = {{-1}, {-1}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final BigMatrix C = A.multiply(B);

        assertEquals("-1\n-1", C.toString());
    }

    @Test
    public void two3x3MatrixReturnArray() {
        final int arrA[][] = {{5, 8, -4}, {6, 9, -5}, {4, 7, -3}};
        final int arrB[][] = {{3, 2, 5}, {4, -1, 3}, {9, 6, 5}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final BigMatrix C = A.multiply(B);

        assertEquals("11\t-22\t29\n9\t-27\t32\n13\t-17\t26", C.toString());
    }

    @Test
    public void two3x3MatrixReturnString() {
        final int arrA[][] = {{5, 8, -4}, {6, 9, -5}, {4, 7, -3}};
        final int arrB[][] = {{3, 2, 5}, {4, -1, 3}, {9, 6, 5}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final BigMatrix C = A.multiply(B);

        assertEquals("11\t-22\t29\n9\t-27\t32\n13\t-17\t26", C.toString());
    }

    @Test
    public void bigIntegerElement() throws Exception {
        final int arrA[][] = {{Integer.MAX_VALUE, Integer.MAX_VALUE}, {0, 0}};
        final int arrB[][] = {{Integer.MAX_VALUE}, {Integer.MAX_VALUE}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final BigMatrix C = A.multiply(B);

        assertEquals("9223372028264841218\n0", C.toString());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void nonPositiveDimensions() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Dimensions must be positive numbers");

        new Matrix(-1, -5);
    }

    @Test
    public void notSameRowsLength() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Array must be correct matrix");

        new Matrix(new int[][]{{0}, {1, 1}});
    }

    @Test
    public void notEnoughElements() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Array must have enough elements");

        new Matrix(new int[]{1, 1, 2}, 2, 2);
    }
}