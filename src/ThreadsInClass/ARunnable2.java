package ThreadsInClass;

public class ARunnable2 extends Thread {

    private String threadName;

    public ARunnable2(String threadName) {
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

        try {
            // Generate a number between 0 and 3999
            int sleepTime = (int) (Math.random() * 4000);
            System.out.println("Sleeping " + threadName + " for " + sleepTime + " miliseconds");
            Thread.sleep(sleepTime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Thread " + threadName + " has finished");
    }
}
