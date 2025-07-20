public class Account {
    static int balance = 100;

    public static void main(String[] args) {
        withdraw(150);
    }

    static void withdraw(int amount) {
        balance -= amount;
    }
}

