public class CreatingThread extends Thread{
    public void run(){
        System.out.println("Thread Running");
    }
    public static void main(String[] args) {
        CreatingThread ref = new CreatingThread();
        ref.start();
        // ref.sleep();
    }
}