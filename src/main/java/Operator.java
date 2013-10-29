/**
 * @author sala
 */
public class Operator {
    protected final double[][] matrix;

    public Operator(double[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Применяет оператор к регистру
     * @param register
     * @return
     */
    public Register apply(Register register) {
        double[] result = new double[register.amplitudes.length];
        for(int i = 0; i  < matrix.length; i++) {
            double[] row = matrix[i];
            double sum = 0;
            for(int j = 0; j < row.length; j++) {
                sum += matrix[i][j] * register.amplitudes[j];
            }
            result[i] = sum;
        }
        return new Register(result);
    }

    public static Operator multiply (Operator a, Operator b) {
        double[][] result = new double[a.matrix.length * b.matrix.length][a.matrix[0].length * b.matrix[0].length];
        for(int i = 0; i < a.matrix.length * b.matrix.length; i++) {
            result[i] = new double[a.matrix[0].length * b.matrix[0].length];
        }
        double[][] matrix1 = a.matrix;
        for (int i = 0; i < matrix1.length; i++) {
            double[] aRpw = matrix1[i];
            for (int j = 0; j < aRpw.length; j++) {
                for(int k = 0; k < b.matrix.length; k++) {
                    double[] bRow = b.matrix[k];
                    for(int l = 0; l < bRow.length; l ++) {
                        result[i *  b.matrix.length + k][j * bRow.length + l] = a.matrix[i][j] * b.matrix[k][l];
                    }
                }

            }
        }
        return new Operator(result);
    }

    public static Operator multiply(Operator a, Operator... operators) {
        Operator result = new Operator(a.matrix);
        for(Operator operator : operators) {
            result = Operator.multiply(result, operator);
        }
        return result;
    }

    public Operator multiply(Operator other) {
        return Operator.multiply(this, other);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(double[] row : matrix) {
            for(double cell : row) {
                result.append(cell).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static final Operator H = new Operator(new double[][] {
            new double[] { 1/Math.sqrt(2),1/Math.sqrt(2)},
            new double[] { 1/Math.sqrt(2),-1/Math.sqrt(2)}
    });

    public static final Operator I = new Operator(new double[][] {
            new double[] { 1, 0},
            new double[] { 0, 1}
    });

    public static final Operator NOT = new Operator(new double[][] {
            new double[] { 0, 1},
            new double[] { 1, 0}
    });

}
