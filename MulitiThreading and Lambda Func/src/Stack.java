public class Stack {
    private int[] array;
    private int stackTop;
    Object lock;
    public Stack(int capacity){
        array = new int[capacity];
        stackTop=-1;
        lock = new Object();
    }

    // declaring a block as synchronized
//    public boolean push(int element){
//        synchronized (lock){
//            if(isFull()) return false;
//            ++stackTop;
//            try {Thread.sleep(1000);} catch (Exception e){e.printStackTrace();}
//            array[stackTop]=element;
//            return true;
//        }
//       // some extra code
//    }

    // synchronizing entire method (this object is used as a lock)
    public synchronized boolean push(int element){
        if(isFull()) return false;
        ++stackTop;
        try {Thread.sleep(1000);} catch (Exception e){e.printStackTrace();}
        array[stackTop]=element;
        return true;
    }
    public synchronized int pop(){
        if(isEmpty()) return Integer.MIN_VALUE;
        int value=array[stackTop];
        array[stackTop]=Integer.MIN_VALUE;
        try {Thread.sleep(1000);} catch (Exception e){e.printStackTrace();}
        stackTop--;
        return value;
    }
    public boolean isEmpty(){
        return stackTop<0;
    }
    public boolean isFull(){
        return stackTop >= array.length - 1;
    }
}
