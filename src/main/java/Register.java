import java.util.Random;

/**
 * @author sala
 */
public class Register {
    private static final Random r = new Random();

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

    /**
     * Measures whole register
     *
     * @return state, which was measured.
     */
    public int measure() {
        final double random = r.nextDouble();
        System.out.println("random = " + random);
        final double measured = random;
        Integer result = null;
        double sum = 0;
        for(int i = 0; i < amplitudes.length; i++) {
            sum += Math.pow(Math.abs(amplitudes[i]), 2);
            if(sum >= measured) {
                result = i;
                break;
            }
        }
        if(result == null) {
            result = amplitudes.length - 1;
        }
        // So you cannot do anything with this register after measurement.
        for(int i = 0; i < amplitudes.length; i++) {
            amplitudes[i] = i == result ? 1.0 : 0.0;
        }
        return result;
    }

    /**
     *
     * @param indexes индексы кубитов, которые нужно измерить
     * @return
     */
    public int measure2(int[] indexes) {
        final double random = r.nextDouble();
        // размер маски
        int maskSize = indexes.length;

        double sum = 0;
        // для каждой возможной маски
        for(int i =0 ; i < Math.round(Math.pow(2, maskSize)); i++) {
            // Считаем маску, которую применяем для суммы
            int actualMask = 0;
            for(int j = 0; j < indexes.length; j++) {
                // Берем j-ый бит маски и ставим, на место j-ого индекса.
                actualMask |= 1 << indexes[j];
            }
            int desiredI = 0;
            for(int j = 0; j < indexes.length; j++) {
                desiredI |= ((i & (1 << j)) >>> j) << indexes[j];
            }
            // Считаем сумму для тех индексов, которые подходят под маску
            for(int j = 0; j < amplitudes.length; j++) {
                if((actualMask & j) == desiredI) {
                    sum += Math.pow(amplitudes[j], 2);
                }
            }
            if(sum >= random) {
                return i;
            }
        }
        return (int)Math.round(Math.pow(2, maskSize)) - 1;
    }

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
