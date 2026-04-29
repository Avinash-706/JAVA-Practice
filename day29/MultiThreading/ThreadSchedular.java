// No gaurantee which thread will start first

public class ThreadSchedular extends Thread{
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadSchedular ref1 = new ThreadSchedular();
        ThreadSchedular ref2 = new ThreadSchedular();
        ThreadSchedular ref3 = new ThreadSchedular();
        ThreadSchedular ref4 = new ThreadSchedular();

        ref1.start();
        ref2.start();
        ref3.start();
        ref4.start();

        // One Thread Can only be start once.
        ref1.start(); //IllegalThreadStateException
    }
}
