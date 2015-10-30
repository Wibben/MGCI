/* Bing Li
 * ICS4U1
 * September 14, 2015
 * Problem Set 3
 */

import java.util.Scanner;

public class Factorial
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        int num;
        long factorial = 1;
        
        // Get user input
        System.out.printf("Enter a non-negative integer: ");
        num = in.nextInt();
        in.nextLine();
        
        // Check if number is within range, factorial with long should give proper values up to 25
        if(num<0) System.out.printf("ERROR: Number cannot be negative.\n");
        else if(num>25) System.out.printf("ERROR: Number too high.\n");
        else {
            // Calculate factorial
            for(int i = num; i>1; i--) {
                System.out.printf("%d x ", i);
                factorial*=i;
            }
            // Display factorial
            System.out.printf("1 = %d", factorial);
        }
        
        return 0;
    }
}
