package test.edu.technopolis.homework;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import src.edu.technopolis.homework.MatrixParser;

public class MatrixParserTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testArgsLengthLess6() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number of arguments must be greater than 5: " +
                "4 dimensions and at least 2 scalars");

        final String args[] = {"2", "2"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void testNonPositiveDimension() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Dimensions must be positive number");

        final String args[] = {"-1", "1", "1", "1", "5", "6"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void testInequalityDimensionAndArgsNumber() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Number of args must be correct with dimensions");

        final String args[] = {"2", "1", "1", "1", "5", "6"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void testNotInitializedMatrixA() throws Exception {
        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage("Matrix A not initialized");

        final MatrixParser parser = new MatrixParser();
        parser.getA();
    }

    @Test
    public void testNotInitializedMatrixB() throws Exception {
        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage("Matrix B not initialized");

        final MatrixParser parser = new MatrixParser();
        parser.getB();
    }

    @Test
    public void testCannotCastArgsToNumbers() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Could not cast args to numbers");

        final String args[] = {"two", "2", "2", "1",
                "1", "0", "0", "1",
                "-1", "-1"};
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);
    }

    @Test
    public void testColumnsNumberNotEqualRowsNumber() throws Exception {
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