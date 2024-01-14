import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Integer> q;
    private final int capacity;
    final Object lock;
    public BlockingQueue(int cap){
        q = new LinkedList<>();
        capacity=cap;
        lock = new Object();
    }
    public boolean add(int element){
        synchronized (lock){
            while(q.size()==capacity){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            q.add(element);
            lock.notifyAll();
            return true;
        }
    }

    public int remove(){
        synchronized (lock){
            while(q.isEmpty()){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int element = q.poll();
            lock.notifyAll();
            return element;
        }
    }
}
