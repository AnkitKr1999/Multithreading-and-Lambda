class A{
    int val=1;
    void method1(){
        System.out.println("Inside Method 1");
    }
    class B{
        int val=2;
        void method2(){
            method1();
            System.out.println("Inside Method 2");
        }
    }
    static class C{
        int val=3;
        void method3(){
           System.out.println("Inside method 3");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main is starting");

        A a = new A();
        System.out.println(a.val);
        a.method1();
        A.B b = a.new B();
        System.out.println(b.val);
        b.method2();

        A.C c = new A.C();
        System.out.println(c.val);
        c.method3();

        System.out.println("Main is terminating!");
    }

    private static void createDeadlock() {
        String lock1="ankit";
        String lock2="kumar";
        Thread thread1 = new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("Locks acquired");
                }
            }
        },"Thread1");
        Thread thread2 = new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("Locks acquired");
                }
            }
        },"thread2");
        thread1.start();thread2.start();
        while(true){
            System.out.println("Thread1"+ thread1.getState());
            System.out.println("Thread2"+ thread2.getState());

            Thread.State state = thread1.getState();
            if(state== Thread.State.TERMINATED) break;
        }
    }

    private static void joinThread() {
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread());
        },"Thread1");
        // setPriority() is advisory method JVM is not obliged to always follow the user set priority
        thread.setPriority(1);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread.getPriority());
    }

    private static void threadState() {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1);
                for(int i=10000;i>0;i--);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"States");
        thread.start();
        while(true){
            Thread.State state = thread.getState();
            System.out.println(state);
            if(state== Thread.State.TERMINATED) break;
        }
    }

    private static void producerConsumer() {
        BlockingQueue q = new BlockingQueue(8);
        for(int i=1;i<=5;i++){
            new Thread(()->{
                for(int j=1;j<10;j++){
                    System.out.println(Thread.currentThread() +", " + String.valueOf(q.add(j)));
                }
            },"Producer" + i).start();

            new Thread(()->{
                for(int j=1;j<10;j++){
                    System.out.println(Thread.currentThread() + ", " +String.valueOf(q.remove()));
                }
            },"Consumer" + i).start();
        }
    }

    private static void makeSynchronization() {
        Stack stack = new Stack(5);

        new Thread(()->{
            int cnt=0;
            while(cnt<20){
                System.out.println("Pushed: "+ stack.push(100));
                cnt++;
            }
        },"Pusher").start();

        new Thread(()->{
            int cnt=0;
            while(cnt<20){
                System.out.println("Popper: "+ stack.pop());
                cnt++;
            }
        },"Popper").start();
    }

    private static void createThreads(){

        // Not Preferred of creating thread.
//        Thread thread1 = new Thread1("thread1");
//        thread1.setDaemon(true);
//        thread1.start();

        // Thread creation using runnable interface.
        Thread thread2 = new Thread(new Thread2(), "thread2");
        thread2.start();

        // Thread creation using runnable interface utilizing lambda functions.
        Thread thread3 = new Thread(()->{
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread()+", "+i);
            }
        },"thread3");
        thread3.start();

    }
}