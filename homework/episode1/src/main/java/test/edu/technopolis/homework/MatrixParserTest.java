package test.edu.technopolis.homework;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.edu.technopolis.homework.Matrix;
import src.edu.technopolis.homework.MatrixParser;

import static org.junit.Assert.assertArrayEquals;

public class MatrixParserTest {
    @Test
    public void simpleExample() throws Exception {
        final String args[] = "2 2 2 1 1 0 0 1 -1 -1".split("\\s+");
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);

        final Matrix A = parser.getA();
        assertArrayEquals(new int[][]{{1, 0}, {0, 1}}, A.getArray());

        final Matrix B = parser.getB();
        assertArrayEquals(new int[][]{{-1}, {-1}}, B.getArray());
    }

    @Test
    public void example3x3() throws Exception {
        final String args = "3 3 3 3 " +
                "5 8 -4 6 9 -5 4 7 -3 " +
                "3 2 5 4 -1 3 9 6 5";
        final MatrixParser parser = new MatrixParser();
        parser.parse(args.split("\\s+"));

        final Matrix A = parser.getA();
        assertArrayEquals(new int[][]{{5, 8, -4}, {6, 9, -5}, {4, 7, -3}}, A.getArray());

        final Matrix B = parser.getB();
        assertArrayEquals(new int[][]{{3, 2, 5}, {4, -1, 3}, {9, 6, 5}}, B.getArray());
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void argsLengthLess6() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number of arguments must be greater than 5: " +
                "4 dimensions and at least 2 scalars");

        final String args[] = {"2", "2"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void nonPositiveDimension() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Dimensions must be positive number");

        final String args[] = {"-1", "1", "1", "1", "5", "6"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void inequalityDimensionAndArgsNumber() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number of args must be correct with dimensions");

        final String args[] = {"2", "1", "1", "1", "5", "6"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void notInitializedMatrixA() throws Exception {
        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage("Matrix A not initialized");

        final MatrixParser parser = new MatrixParser();
        parser.getA();
    }

    @Test
    public void notInitializedMatrixB() throws Exception {
        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage("Matrix B not initialized");

        final MatrixParser parser = new MatrixParser();
        parser.getB();
    }

    @Test
    public void cannotCastArgsToNumbers() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Could not cast args to integers");

        final String args[] = {"two", "2", "2", "1",
                "1", "0", "0", "1",
                "-1", "-1"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void columnsNumberNotEqualRowsNumber() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Two matrices can be multiplied only" +
                "when the number of columns in the first" +
                "equals the number of rows in the second");

        final String args[] = {"3", "3", "2", "2",
                "1", "0", "0", "1", "0", "1", "1", "0", "1",
                "-1", "-1", "-1", "-1"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }
}