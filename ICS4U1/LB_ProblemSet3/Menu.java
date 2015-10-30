/* Bing Li
 * ICS4U1
 * September 14, 2015
 * Problem Set 3
 */

import java.util.Scanner;

public class Menu
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        char choice;
        
        do {
            // Displaying menu
            System.out.printf("\u000C");
            System.out.printf("Please select a Program:\n\n");
            System.out.printf("1 - Product\n");
            System.out.printf("2 - Factorial\n");
            System.out.printf("3 - Invest\n");
            System.out.printf("0 - Exit\n");
            
            // Getting choice
            choice = in.nextLine().charAt(0);
            
            // Running program based on choice
            System.out.printf("\u000C");
            if(choice=='1') Product.main();
            else if(choice=='2') Factorial.main();
            else if(choice=='3') Invest.main();
            
            // Wait for user
            System.out.printf("\nEnter enter to continue...");
            in.nextLine();
        } while(choice!='0');
        
        return 0;
    }
}