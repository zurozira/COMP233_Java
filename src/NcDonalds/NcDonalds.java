package NcDonalds;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Assign orders for the NcDonalds restaurant.
 * Prompt user for input of how many order the restaurant will get.
 * Then process them using fixed thread pool.
 * Orders are executed in 3 priority order:
 * Masterchef order first,
 * not Masterchef and not Slacker order second,
 * Slacker order last.
 * @author Vu Cong Bui
 */
public class NcDonalds {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("""
                +------------------+
                |    NcDONALDS     |
                +------------------+
                """);
        System.out.print("How many order: ");
        int amount = input.nextInt();

        Order[] order = new Order[amount];

        char c = 'A';
        for (int i = 0; i < order.length; i++)
        {
            order[i] = new Order(String.format("(%c)", c + i));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(order.length);

        for (int i = 0; i < order.length; i++)
        {
            if (order[i].isMasterChef())
            {
                executorService.execute(order[i]);
            }
        }

        for (int i = 0; i < order.length; i++)
        {
            if (!order[i].isMasterChef() && !order[i].isSlacker())
            {
                executorService.execute(order[i]);
            }
        }

        for (int i = 0; i < order.length; i++)
        {
            if (order[i].isSlacker())
            {
                executorService.execute(order[i]);
            }
        }

        executorService.shutdown();
    }
}
