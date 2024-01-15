import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import hotels.FilterCondition;
import hotels.Hotel;
import hotels.HotelService;
import hotels.HotelType;

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
    private int field;

    private List<Hotel> testLambda(){
        HotelService hotelService = new HotelService();
        final int PRICE = 2000; // variable used inside lambda expression must be final
        List<Integer> arr = new ArrayList<>(); // arr is a refernce to a list hence mutation is allowed
        FilterCondition lambdaExp = hotel -> { // making a lambda expression reference.
            // return hotel.getRating()<=arr.size(); // mutation of arr is allowed
            return hotel.getPricePerNight() <= PRICE; // mutation of PRICE is not allowed
        };
        arr.add(1);
        return hotelService.filterHotels(lambdaExp);
    }

    public static void main(String[] args) {
        // underStandingLambdaFunctions();
        
        // combiningPredicates();

        // differentInbuiltInterfaces();

        workingWithStreams();
    }

    private static void workingWithStreams() {
       List<Integer> arr = Arrays.asList(5,4,3,2,1,0);

       arr.stream()
       .filter(n-> n%2==1)
       .sorted()
       .map(n-> n*n)
       .forEach(System.out::println);

       int sumOfOddValues = arr.stream()
       .filter(n-> n%2==1)
       .sorted()
       .map(n-> n*2)
       .reduce(0,(c,e)->c+e);
       System.out.println("Sum of odd values in arr: "+ sumOfOddValues);

       List<Integer> values = Arrays.asList(5,4,5,1,2,0,1,0,0,1);
       values.stream().distinct().forEach(System.out::println);;
    }

    private static void differentInbuiltInterfaces() {
       
        List<Integer> lst = new ArrayList<>(List.of(5,4,3,2,1));
    
       Consumer<Integer> consumer = value -> System.out.println(value);
       IntConsumer intConsumer = value -> System.out.println(value); //  this can also be used
       lst.forEach(consumer);

       Supplier<Double> supplier = () -> Math.random();
       System.out.println("Random values from supplier: " + supplier.get());

       Function<String,Integer> getLen =  val -> val.length();
       System.out.println("Length of string from Function interface: "+  getLen.apply("Ankit Kumar"));

       Function<Integer,Integer> square = a -> a*a;
       Function<Integer,Integer> addOne = a -> a+1; 
       Integer value = square.andThen(addOne).apply(6);
       System.out.println("value of combined interface: "+ value);
    }

    private static void combiningPredicates() {
        
        // predicate is a functional interface which also contains test abstract method.
        Predicate<Integer> divisibleBy2 = (number) -> { return number%2 == 0;}; 
        // this can be used primitive data type and will have better performance
        IntPredicate intDivisibleBy2 = (number) -> { return number%2 == 0;};  
        Predicate<Integer> divisibleBy3 = number -> number%3 == 0;

        Predicate<Integer> divisibleBy6 = divisibleBy2.and(divisibleBy3);
        System.out.println(divisibleBy6.test(12));
        System.out.println(divisibleBy6.test(15));
    }

    private static void underStandingLambdaFunctions(){
        HotelService hotelService = new HotelService();

        // Using Anonymous inner class to pass function in a method.

        // List<Hotel> fiveStarHotels = hotelService.filterHotels(new FilterCondition() {
        //     @Override
        //     public boolean test(Hotel hotel) {
        //         return hotel.getHotelType() == HotelType.FIVE_STAR;
        //     }
        // });

        // using lamdba function to pass function in a method.
        // Below Hotel is a inferred parameter, explicit declaration can be omitted.
        List<Hotel> fiveStarHotels = hotelService.filterHotels((Hotel hotel) -> {
            return hotel.getHotelType() == HotelType.FIVE_STAR;
        });
        System.out.println("Five Star Hotels " + fiveStarHotels);

        List<Integer> lst = new ArrayList<>(List.of(5,4,3,2,1));
        // below Integer declaration of a,b is omitted and {} braces can be omitted for single line of code.
        Collections.sort(lst,(a,b) -> a-b); 

        System.out.println(lst);

        // Using Anonymous inner class to pass function in a method.
        List<Hotel> hotelsMoreThan2Ratings = hotelService.filterHotels(new FilterCondition() {
            @Override
            public boolean test(Hotel hotel) {
                return hotel.getRating() >= 3;
            }
        });
        System.out.println("More than 2 rating Hotels " + hotelsMoreThan2Ratings);
        System.out.println();

        // using lambda function with predicate
        List<Hotel> hotelsMoreThan3Rating = hotelService.filterHotelsWithPredicate((hotel) -> {
            return hotel.getRating() > 3;
        });
        System.out.println("More than 3 rating hotels: " + hotelsMoreThan3Rating);
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