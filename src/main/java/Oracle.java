/**
 * @author sala
 */
public class Oracle {
    public static Operator getOracle(int expectedValue, int length) {
        if(expectedValue >= length) {
            throw new IllegalStateException("Expected value should be in range [0;length)");
        }
        double[][] matrix = new double[length][length];
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < length; j++) {
                if(i == j) {
                    if(i == expectedValue) {
                        matrix[i][j] = -1;
                    } else {
                        matrix[i][j] = 1;
                    }
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return new Operator(matrix);
    }
}
