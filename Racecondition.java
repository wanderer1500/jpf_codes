public class Racecondition {
    static int counter = 0;

    static class MyThread extends Thread {
        public void run() {
            for (int i = 0; i < 100; i++) {
                counter++;  //  Not synchronized
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Expected: 200
        
    }
}
