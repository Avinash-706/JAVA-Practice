class Customer {
    int amount = 10000;

    synchronized void withdraw(int amt) throws Exception {
        if(amount < amt) wait();
        amount -= amt;
        System.out.println("Withdraw completed");
    }

    synchronized void deposit(int amt) {
        amount += amt;
        notify();
    }
}

public class Inter_Thread_Communication {
    public static void main(String[] args) {
        Customer c = new Customer();

        new Thread(() -> {
            try { c.withdraw(15000); } catch(Exception e){}
        }).start();

        new Thread(() -> c.deposit(10000)).start();
    }
}