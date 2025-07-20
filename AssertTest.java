public class AssertTest {
    public static void main(String[] args) {
        int x = 3;
        int y = 4;
        assert x + y == 8 : "Sum is not 8!";  // This will fail!
    }
}
