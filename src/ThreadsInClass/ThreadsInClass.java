package ThreadsInClass;

import java.util.concurrent.*;

public class ThreadsInClass {

    public static void main(String[] args) {
        ARunnable2 run1 = new ARunnable2("Alpha");
        ARunnable2 run2 = new ARunnable2("Beta");
        ARunnable2 run3 = new ARunnable2("Steve");

        // Find the resources to execute (run) threads concurrently
        // Fixed thread pool is the number of threads that can run at the same time
        ExecutorService runThreads = Executors.newFixedThreadPool(2);

        runThreads.execute(run1);
        runThreads.execute(run2);
        runThreads.execute(run3);

        // Finish the current threads and then accept no more
        runThreads.shutdown();

        System.out.println("The main method is done");
    }
}
