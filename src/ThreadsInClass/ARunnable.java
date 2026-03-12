package ThreadsInClass;

import java.util.concurrent.*;

public class ARunnable implements Runnable {

    private String threadName;

    public ARunnable(String threadName) {
        this.threadName = threadName;
    }

    /**
     * This acts as the main method of this thread.
     * Print out the threadName and says it started, waits
     * and then print out that the thread has finished.
     * NEVER RUN THE RUN() FUNCTION DIRECTLY. ALWAYS USE ExecutorService
     */
    public void run() {

        System.out.println("Thread " + threadName + " has started");

        for (int i = 0; i < Integer.MAX_VALUE; i++);

        System.out.println("Thread " + threadName + " has finished");
    }
}
