import gov.nasa.jpf.vm.Verify;

public class LTLExample {
    public static void main(String[] args) {
        boolean request = Verify.getBoolean();   // symbolic boolean
        boolean response = Verify.getBoolean();

        if (request) {
            Verify.setProperty("request", true);
            System.out.println("Request occurred.");
        }

        if (response) {
            Verify.setProperty("response", true);
            System.out.println("Response occurred.");
        }
    }
}
