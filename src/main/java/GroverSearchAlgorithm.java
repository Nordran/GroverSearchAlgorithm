import java.util.Random;

/**
 * @author sala
 */
public class GroverSearchAlgorithm {
    public static void main(String[] args) {
        final Random r = new Random();
        final int n = 2;
        final int N = (int)Math.pow(2, n);
        final Register base = new Register(1, 0);
        Register register = new Register(1, 0);
        for(int i = 2; i <= n; i++) {
            register = Register.multiply(register, base);
        }
        System.out.println(register);
        Operator wolshHadamard = Operator.H;
        for(int i = 2; i <= n; i++) {
            wolshHadamard = Operator.multiply(wolshHadamard, Operator.H);
        }
        //
        final int expectedValue = r.nextInt(N);
        System.out.println("Desired: ");
        System.out.println(expectedValue);
        //
        final Operator oracle = Oracle.getOracle(expectedValue, N);
        Register working = wolshHadamard.apply(register);
        System.out.println("Before cycle:" + working);
        for (int i = 0; i < (int)(Math.PI * Math.sqrt(N) / 4); i++) {
            final Register oracled = oracle.apply(working);
            working = dispersion(N).apply(oracled);
            System.out.println("After step " + i + ":");
            System.out.println(working);
        }
        System.out.println("Result:");
        System.out.println(working);


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
