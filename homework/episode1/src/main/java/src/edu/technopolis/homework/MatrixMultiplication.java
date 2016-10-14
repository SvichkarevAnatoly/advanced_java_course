package src.edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String... args) {
        final MatrixParser parser = new MatrixParser();
        parser.parse(args);

        final Matrix A = parser.getA();
        final Matrix B = parser.getB();
        final Matrix AB = A.multiply(B);

        System.out.println(AB);
    }
}
