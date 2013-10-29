/**
 * @author sala
 */
public class Register {
    protected double[] amplitudes;
    public Register(double... amplitudes){
        this.amplitudes = amplitudes;
    }

    public static Register multiply(Register a, Register b) {
        double[] result = new double[a.amplitudes.length * b.amplitudes.length];
        for(int i = 0; i < a.amplitudes.length; i++) {
            for(int j = 0; j < b.amplitudes.length; j++) {
                result[i*b.amplitudes.length+j] = a.amplitudes[i] * b.amplitudes[j];
            }
        }
        return new Register(result);
    }

    public Register multiply(Register other) {
        return multiply(this, other);
    }

    public static final Register ZERO = new Register(1,0);
    public static final Register ONE = new Register(0,1);

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < amplitudes.length; i++) {
            if(i != 0) {
                if(amplitudes[i] >= 0) {
                    result.append("+");
                } else {
                    result.append("-");
                }
            } else {
                if(amplitudes[i] < 0) {
                    result.append("-");
                }
            }
            result.append(String.format("%4f", Math.abs( amplitudes[i]))).append("*").append("|").append(i).append(">");

        }
        return result.toString();
    }
}
