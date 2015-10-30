/* Bing Li
 * ICS4U1
 * September 11, 2015
 * Problem Set 2
 */

import java.util.Scanner;

public class Paycheque
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        double wage,hour;
        double reg,ot;
        reg = ot = 0;
        
        // Get user input
        System.out.printf("Hourly Wage: ");
        wage = in.nextDouble();
        in.nextLine();
        
        System.out.printf("Number of Hours Worked: ");
        hour = in.nextDouble();
        in.nextLine();
        
        // Calculate wages
        if(hour<40) {
            reg = wage*hour;
        } else {
            reg = wage*40;
            ot = wage*1.5*(hour-40);
        }
        
        // Display wages
        System.out.printf("Regular Pay: %.2f\n", reg);
        System.out.printf("Overtime Pay: %.2f\n", ot);
        System.out.printf("Total Pay: %.2f\n", reg+ot);
        
        return 0;
    }
}
