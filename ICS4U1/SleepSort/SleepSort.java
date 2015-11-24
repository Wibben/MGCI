/**
 * The Great SleepSort
 * Conceived by the great hacker 4chan and implemented by Bing Li
 * Uses multithreaidng to achieve effect
 * Concept:
 * Given a set of positive integers, sort them in increasing order
 * Assign each new number to a new thread, and have the threads all start almost simultaneously
 * When a thread finished running, the number associated with the thread is outputted
 */
import java.util.*;

class Number implements Runnable 
{
    private Thread t;
    private int n;

    public Number(int n)
    {
        this.n = n;
        t = new Thread(this);
    }

    public void run()
    {
        // Wait until all input is complete to begin counting
        while(!SleepSort.done) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
        
        try Thread.sleep(n*100);
        catch (InterruptedException e) System.out.println("Thread interrupted.");
        
        System.out.printf("%d ", n);
    }

    public void start ()
    {
        t.start();
    }
}

public class SleepSort
{
    private static Scanner in = new Scanner(System.in);
    public static boolean done;
    
    public static int main()
    {
        System.out.printf("How many numbers? ");
        int n = in.nextInt();
        done = false;
        
        for(int i=0; i<n; i++) {
            // Comment out either part A or part B
            // A to produce n random numbers, B to actually use it
            // Part A begin
            int a = (int)(Math.random()*100+1);
            System.out.printf("%d ", a);
            Number x = new Number(a);
            // Part A end
            
            // Part B begin
            //Number x = new Number(in.nextInt());
            // Part B end
            
            x.start();
        } System.out.println();
        
        done = true;

        return 0;
    }   
}