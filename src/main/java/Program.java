/**
 * @author sala
 */
public class Program {
    public static void main(String[] args) {
        Register register = new Register(0.75, 0.25);
        System.out.println(register);
        Operator H = new Operator(new double[][] {
                new double[] { 1/Math.sqrt(2),1/Math.sqrt(2)},
                new double[] { 1/Math.sqrt(2),-1/Math.sqrt(2)}
        });
        Operator I = new Operator(new double[][] {
                new double[] { 1, 0},
                new double[] { 0, 1},
        });
        Operator HH = Operator.multiply(H, H);
        System.out.println(HH);
        Operator NOT = new Operator(new double[][] {
                new double[] { 0, 1},
                new double[] { 1, 0}
        });
        Operator Z = new Operator(new double[][] {
                new double[] { 1, 0},
                new double[] { 0,-1}
        });
        System.out.println(I.apply(register));
        System.out.println(NOT.apply(register));
        System.out.println(Z.apply(register));

    }
}
