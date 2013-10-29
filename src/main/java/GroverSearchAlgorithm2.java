/**
 * @author timur.shakurov
 */
public class GroverSearchAlgorithm2 {
    public static void main(String[] args) {
        final int n = 3;
        final int N = (int)Math.round(Math.pow(2, n));
        // Target index
        final int x0 = 5;
        System.out.println("Desired x: " + x0);
        Register register = Register.ZERO;
        for(int i = 2; i <= n + 1; i++) {
            register = register.multiply(Register.ZERO);
        }
        // Building I^n * NOT
        Operator operator = Operator.I;
        for(int i = 2; i <= n; i++) {
            operator = operator.multiply(Operator.I);
        }
        operator = operator.multiply(Operator.NOT);
        //
        register = operator.apply(register);
        //
        // Building H^(n+1)
        Operator operator2 = Operator.H;
        for(int i = 2; i <= n + 1; i++) {
            operator2 = operator2.multiply(Operator.H);
        }
        //
        register = operator2.apply(register);
        //
        Predicate P = PredicateFactory.createPredicate(x0);
        Operator oracle = OracleFactory.createOracle(P, N);
        for(int i = 0; i < (int) ((Math.PI / 4) * Math.sqrt(N)); i++) {
            register = oracle.apply(register);
            System.out.println(register);
            final Operator multiply = dispersion(N).multiply(Operator.I);
            register = multiply.apply(register);
            System.out.println(register);
        }
        //
        System.out.println("Result: " + register.measure2(new int[]{1, 2, 3}));
    }
    public static Operator dispersion(int size) {
        double[][] matrix = new double[size][];
        for (int i = 0; i < size; i++) { matrix[i] = new double[size]; }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i==j) {
                    matrix[i][j] = 2.0 / size - 1;
                } else {
                    matrix[i][j] = 2.0 / size;
                }
            }
        }
        return new Operator(matrix);
    }
}
