/* Bing Li
 * ICS4U1
 * September 15, 2015
 * Problem Set 4
 */

import java.util.Scanner;
 
public class BankAccount
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        double balance,withdrawl,deposit=0;
        
        // Get user input
        System.out.printf("Starting Balance: ");
        balance = in.nextDouble();
        in.nextLine();
        
        // Get user input until quit
        for(int i=1; balance>=0 && deposit!=-1; i++) {
            // Get deposit and withdrawal
            System.out.printf("\nMonth #%d\tDeposits: ", i);
            deposit = in.nextDouble();
            in.nextLine();
            if(deposit!=-1) {
                balance += deposit;
                
                System.out.printf("\t\tWithdrawls: ");
                withdrawl = in.nextDouble();
                in.nextLine();
                balance -= withdrawl;
            }
        }
        // Display final balance
        System.out.printf("\nYou have $%.2f left.", balance);
        
        return 0;
    }
}
