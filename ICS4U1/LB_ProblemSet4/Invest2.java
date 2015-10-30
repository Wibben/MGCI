/* Bing Li
 * ICS4U1
 * September 15, 2015
 * Problem Set 4
 */

import java.util.Scanner;

public class Invest2
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        double deposit,rate,target,interest,balance=0;
        int month;
        
        // Get user input
        System.out.printf("Monthly Deposit\t\t: ");
        deposit = in.nextDouble();
        in.nextLine();
        System.out.printf("Annual Interest Rate (%%): ");
        rate = in.nextDouble();
        in.nextLine();
        System.out.printf("Target Value\t\t: ");
        target = in.nextDouble();
        in.nextLine();
        rate/=1200;
        
        // Display template and run for desired number of terms
        System.out.printf("Starting   Interest   Monthly   Ending\n");
        System.out.printf("Balance    Earned     Deposit   Balance\n");
        System.out.printf("--------   --------   -------   -------\n");
        for(month=0; balance<=target; month++) {
            // Calculate and display interest and balance
            System.out.printf("%8.2f   ", balance);
            interest = balance*(rate);
            balance += deposit+interest;
            System.out.printf("%8.2f   %7.2f   %7.2f\n", interest, deposit, balance);
        }
        // Display months taken
        System.out.printf("\nIt took %d months to reach your target.\n", month);
        
        return 0;
    }
}
