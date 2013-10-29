/**
 * @author timur.shakurov
 */
public abstract class OracleFactory {
    public static Operator createOracle(Predicate p, int size) {
        double[][] matrix = new double[size * 2][size * 2];
        for(int i = 0; i  < size; i++) {
            if(p.P(i)) {
                matrix[2*i][2*i+1] = 1;
                matrix[2*i+1][2*i] = 1;
            } else {
                matrix[2*i][2*i] = 1;
                matrix[2*i+1][2*i+1] = 1;
            }
        }
        return new Operator(matrix);
    }
}
