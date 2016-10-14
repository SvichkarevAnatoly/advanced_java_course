package test.edu.technopolis.homework;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.edu.technopolis.homework.Matrix;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MatrixTest {
    @Test
    public void EAndVectorReturnArray() {
        final int arrA[][] = {{1, 0}, {0, 1}};
        final int arrB[][] = {{-1}, {-1}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final Matrix C = A.multiply(B);

        assertArrayEquals(arrB, C.getArray());
    }

    @Test
    public void EAndVectorReturnString() {
        final int arrA[][] = {{1, 0}, {0, 1}};
        final int arrB[][] = {{-1}, {-1}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final Matrix C = A.multiply(B);

        assertEquals("-1 \n-1 \n", C.toString());
    }

    @Test
    public void two3x3MatrixReturnArray() {
        final int arrA[][] = {{5, 8, -4}, {6, 9, -5}, {4, 7, -3}};
        final int arrB[][] = {{3, 2, 5}, {4, -1, 3}, {9, 6, 5}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final Matrix C = A.multiply(B);

        assertArrayEquals(
                new int[][]{{11, -22, 29}, {9, -27, 32}, {13, -17, 26}},
                C.getArray());
    }

    @Test
    public void two3x3MatrixReturnString() {
        final int arrA[][] = {{5, 8, -4}, {6, 9, -5}, {4, 7, -3}};
        final int arrB[][] = {{3, 2, 5}, {4, -1, 3}, {9, 6, 5}};
        final Matrix A = new Matrix(arrA);
        final Matrix B = new Matrix(arrB);
        final Matrix C = A.multiply(B);

        assertEquals("11 -22 29 \n9 -27 32 \n13 -17 26 \n",
                C.toString());
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