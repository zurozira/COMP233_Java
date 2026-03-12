package NcDonalds;

import java.util.Random;

/**
 * Represent a customer order in NcDonalds restaurant
 * Order is processed as a thread.
 * Randomly assign a chef behavior that affects how the order is processed
 * @author Vu Cong Bui
 */
public class Order extends Thread {

    private String orderName;
    private static volatile int[] position = new int[4];

    private boolean masterChef;
    private boolean slacker;
    private boolean sleepy;
    private boolean speedy;
    private boolean inTraining;
    private boolean slipperyFeet;

    public Order(String name)
    {
        setOrderName(name);

        Random random = new Random();
        int ran = random.nextInt(6);

        switch(ran) {
            case 0 -> masterChef = true;
            case 1 -> slacker = true;
            case 2 -> sleepy = true;
            case 3 -> speedy = true;
            case 4 -> inTraining = true;
            case 5 -> slipperyFeet = true;
        }
    }

    public String getOrderName() { return orderName; }

    public void setOrderName(String orderName) { this.orderName = orderName; }

    /**
     * Increment the position at the specified stage of the order
     * @param x - the index of the stage
     */
    public void incrementPosition(int x) {
        position[x]++;
    }

    /**
     * Return the position this order reached at a specified stage
     * @param x - the index of the stage
     * @return the position this order reached at stage x
     */
    public int getPosition(int x) {
        return position[x];
    }

    public boolean isMasterChef() { return masterChef; }

    public void setMasterChef(boolean masterChef) { this.masterChef = masterChef; }

    public boolean isSlacker() { return slacker; }

    public void setSlacker(boolean slacker) { this.slacker = slacker; }

    public boolean isSleepy() { return sleepy; }

    public void setSleepy(boolean sleepy) { this.sleepy = sleepy; }

    public boolean isSpeedy() { return speedy; }

    public void setSpeedy(boolean speedy) { this.speedy = speedy; }

    public boolean isInTraining() { return inTraining; }

    public void setInTraining(boolean inTraining) { this.inTraining = inTraining; }

    public boolean isSlipperyFeet() { return slipperyFeet; }

    public void setSlipperyFeet(boolean slipperyFeet) { this.slipperyFeet = slipperyFeet; }

    /**
     * Execute the order across 4 stages:
     * Stage 0 - order picked up,
     * Stage 1 - order made,
     * Stage 2 - order delivered,
     * Stage 3 - customer receives order.
     * Chef behaviors affect timing and output of the order.
     */
    public void run()
    {
        if (isSpeedy())
        {
            setPriority(7);

            System.out.println("The chef is feeling good and speedily takes the order for " +
                    this.getOrderName());
        }

        if (this.isInTraining())
        {
            System.out.println(this.getOrderName() +
                    "'s order was given to the chef in training!");
        }

        System.out.println("Order for " + this.getOrderName() + " is up!");

        for (int i = 0; i < 200000000; i++);
        if (this.isInTraining())
        {
            for (int i = 0; i < 200000000; i++);

        }
        incrementPosition(0);
        System.out.println("\tOrder for " + this.getOrderName() +
                " is picked up by a chef and is in line in position " +
                getPosition(0) + "!");

        if (this.isSleepy())
        {
            int x = (int) (Math.random() * 10) + 1;

            if (x <= 2)
            {
                System.out.println("The chef is feeling sleepy has fallen asleep while on order for " +
                        this.getOrderName());

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException ie)
                {
                    System.out.println(ie.toString());
                }

                System.out.println("The chef woke up and continued order for " +
                        this.getOrderName());
            }
        }

        for (int i = 0; i < 200000000; i++);
        if (this.isInTraining())
        {
            for (int i = 0; i < 200000000; i++);

        }
        incrementPosition(1);
        System.out.println("\t\tOrder for " + this.getOrderName() +
                " has been made and is in line in position " +
                getPosition(1) + "!");

        if (this.isSleepy())
        {
            int x = (int) (Math.random() * 10) + 1;

            if (x <= 2)
            {
                System.out.println("The chef is feeling sleepy has fallen asleep while on order for " +
                        this.getOrderName());

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException ie)
                {
                    System.out.println(ie.toString());
                }

                System.out.println("The chef woke up and continued order for " +
                        this.getOrderName());
            }
        }

        for (int i = 0; i < 200000000; i++);
        if (this.isInTraining())
        {
            for (int i = 0; i < 200000000; i++);

        }
        incrementPosition(2);
        System.out.println("\t\t\tOrder for " + this.getOrderName() +
                " is being delivered in position " +
                getPosition(2) + "!");

        if (isSlipperyFeet())
        {
            System.out.println("\t\t\t\tCRASH! Slippery floors caused the chef to drop " +
                    this.getOrderName()+"'s food on the floor and has to be remade!");

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie)
            {
                System.out.println(ie.toString());
            }

            System.out.println("\t\t\t\t The chef remade " +
                    this.getOrderName()+ "'s meal and is on the way");
        }

        for (int i = 0; i < 200000000; i++);
        if (this.isInTraining())
        {
            for (int i = 0; i < 200000000; i++);

        }
        incrementPosition(3);
        if (getPosition(3) == 1)
        {
            System.out.println("\t\t\t\tOrder for " + this.getOrderName() +
                    " got their order first!");
        }
        else
        {
            System.out.println("\t\t\t\tOrder for " + this.getOrderName() +
                    " got their order in position " +
                    getPosition(3) + "!");

        }
    }
}
