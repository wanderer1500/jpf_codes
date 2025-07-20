import gov.nasa.jpf.vm.Verify;

public class NondetBug {

    public static void main(String[] args) {

        // Get an integer value between -5 and 5 (inclusive)
        int input = Verify.getInt(-5, 5);

        System.out.println("Input = " + input);

        int result = compute(input);

        System.out.println("Computation result = " + result);
    }

    public static int compute(int x) {
        int y = 10 / x; // Division by zero if x == 0 → Runtime bug
        return y + x;
    }
}
