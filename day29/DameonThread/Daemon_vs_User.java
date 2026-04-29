// Daemon Thread in Java :
// 	A Daemon Thread is a background thread that runs to support user threads.
// 	When all user (non-daemon) threads finish, the JVM automatically terminates daemon threads.
// 	Daemon thread runs in background
// 	JVM does not wait for daemon threads
// 	setDaemon(true) must be called before start()
// 	It is a low priority thread.


public class Daemon_vs_User {

    public static void main(String[] args) {

        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon thread running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Daemon interrupted");
                }
            }
        });
        daemonThread.setDaemon(true);

        daemonThread.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Main thread running: " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Main thread interrupted");
            }
        }

        System.out.println("Main thread finished");
    }
}