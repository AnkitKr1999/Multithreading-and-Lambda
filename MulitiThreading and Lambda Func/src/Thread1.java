// Not preferred as Multiple inheritance is not supported in java
// It will add constraints in design.
public class Thread1 extends Thread {
    public Thread1(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Inside " + Thread.currentThread() + " " + i);
//            System.out.println("Inside " + Thread.currentThread().getName() + " " + i);
        }
    }
}
