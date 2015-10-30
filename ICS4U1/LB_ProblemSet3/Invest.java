/* Bing Li
 * ICS4U1
 * September 14, 2015
 * Problem Set 3
 */

import java.util.Scanner;

public class Invest
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        double deposit,rate,balance,interest;
        int term;
        balance = 0;
        
        // Get user input
        System.out.printf("Monthly Deposit\t\t: ");
        deposit = in.nextDouble();
        in.nextLine();
        System.out.printf("Annual Interest Rate (%%): ");
        rate = in.nextDouble();
        in.nextLine();
        System.out.printf("Term (Months)\t\t: ");
        term = in.nextInt();
        in.nextLine();
        rate/=1200;
        
        // Display template and run for desired number of terms
        System.out.printf("Starting   Interest   Monthly   Ending\n");
        System.out.printf("Balance    Earned     Deposit   Balance\n");
        System.out.printf("--------   --------   -------   -------\n");
        for(int i=0; i<term; i++) {
            // Calculate and display interest and balance
            System.out.printf("%8.2f   ", balance);
            interest = balance*(rate);
            balance += deposit+interest;
            System.out.printf("%8.2f   %7.2f   %7.2f\n", interest, deposit, balance);
        }
        
        return 0;
    }
}
