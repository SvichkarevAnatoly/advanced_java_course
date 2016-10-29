package src.edu.technopolis.homework;

/**
 * Matrix multiplication home task.
 * <br/>
 * Matrix dimension and elements are passed as CLI arguments.
 */
public class MatrixMultiplication {
    public static void main(String... args) {
        // no args or run with -h or --help flag
        if (isCallHelp(args)) {
            showFullHelp();
            return;
        }

        final Parser parser = new Parser();
        try {
            parser.parse(args);

            final Matrix A = parser.getA();
            final Matrix B = parser.getB();
            final BigMatrix AB = A.multiply(B);
            System.out.println(AB);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
            showShortHelp();
        }
    }

    private static boolean isCallHelp(String[] args) {
        final String HELP_SHORT_FLAG = "-h";
        final String HELP_LONG_FLAG = "--help";

        if (args.length == 0) {
            return true;
        }

        for (String arg : args) {
            if (HELP_SHORT_FLAG.equals(arg) || HELP_LONG_FLAG.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private static void showFullHelp() {
        final StringBuilder help = new StringBuilder();
        help.append("Usage: java MatrixMultiplication N M X Y A_1_1 ... A_N_M B_1_1 ... B_X_Y\n");
        help.append("\n");
        help.append("Calculate multiplication of A and B matrices.\n");
        help.append("\n");
        help.append("Matrix A consists of A_1_1 ... A_N_M elements with N rows and M columns.\n");
        help.append("Matrix B consists of B_1_1 ... B_X_Y elements with X rows and Y columns.\n");
        help.append("Matrix dimension and elements are passed as CLI arguments\n");
        help.append("\n");
        help.append("Example: java MatrixMultiplication 2 2 2 1 1 0 0 1 -1 -1\n");
        help.append("return:\n");
        help.append("-1\n");
        help.append("-1\n");
        help.append("\n");
        help.append("Report bugs to: svichkarev.anatoly@gmail.com");
        System.out.println(help);
    }

    private static void showShortHelp() {
        System.out.println(
                "\nUsage: java MatrixMultiplication N M X Y A_1_1 ... A_N_M B_1_1 ... B_X_Y\n" +
                        "Try 'java MatrixMultiplication -h' for more information.");
    }
}
