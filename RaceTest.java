public class RaceTest {
    static int counter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            counter++;
        });

        Thread t2 = new Thread(() -> {
            counter--;
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert counter == 0 : "Race condition detected!";
    }
}
