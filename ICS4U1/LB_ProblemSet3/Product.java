/* Bing Li
 * ICS4U1
 * September 14, 2015
 * Problem Set 3
 */

import java.util.Scanner;

public class Product
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable Declaration
        int process,number;
        int product = 1;
        
        // Get user input
        System.out.printf("How many numbers to process? ");
        process = in.nextInt();
        in.nextLine();
        
        // Loop desired number of times
        System.out.printf("\nEnter %d positive integers\n", process);
        for(int i=1; i<=process; i++) {
            // Get user input
            System.out.printf("#%d: ", i);
            number = in.nextInt();
            in.nextLine();
            // Calculate product if number entered is positive
            if(number<1) System.out.printf("%d not counted\n", number);
            else product*=number;
        }
        
        // Display product
        System.out.printf("\nThe product of the positives is %d.", product);
        
        return 0;
    }
}
