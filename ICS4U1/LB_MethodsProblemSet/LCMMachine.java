/* Bing Li
 * ICS4U1
 * September 17, 2015
 * Methods Problem Set
 */

import java.util.Scanner;

public class LCMMachine
{
    public static Scanner in;

    public static void title(String display)
    {
        // Display the borders with the message inside
        for(int i=0; i<display.length()+4; i++)
            System.out.printf("-");
        System.out.printf("\n| %s |\n", display);
        for(int i=0; i<display.length()+4; i++)
            System.out.printf("-");
        System.out.printf("\n\n");
    }

    public static int getRandom(int lower, int upper)
    {
        // Return a random number in the range
        return lower+(int)(Math.random()*(upper-lower+1));
    }

    public static char readChar(char lower, char upper)
    {
        // Variable declaration
        char number;
           
        // Get initial input
        number = in.nextLine().charAt(0);
        
        // Ask user to input value until within range
        while(lower>=number || number>=upper) {
            System.out.printf("Error, %c-%c only: ", lower, upper);
            number = in.nextLine().charAt(0);
        }

        return number;
    }

    public static char readChar(String valid)
    {
        // Variable declaration
        char choice;
        
        // Get initial input
        choice = in.nextLine().charAt(0);

        // Ask user to input value until within range
        while(valid.indexOf(choice)==-1) {
            System.out.printf("Error, %s only: ", valid);
            choice = in.nextLine().charAt(0);
        }

        return Character.toUpperCase(choice);
    }

    public static int lcm(int a, int b)
    {
        // Return 0 if any value less than or equal to 0
        if(a<=0 || b<=0) return 0;

        // Variable declaration
        int gcf,tempa,tempb;
        tempa = Math.max(a,b);
        tempb = Math.min(a,b);

        // Use Euclid's algorithm to find gcf
        while(tempb!=0) {
            int temp = tempb;
            tempb = tempa%tempb;
            tempa = temp;
        }
        gcf = tempa;

        // Calculate and return lcm
        return a*b/gcf;
    }

    public static int main()
    {
        in = new Scanner(System.in);

        System.out.printf("\u000c");

        // Variable declaration
        char choice;
        int pairs;

        do{
            // Display title
            title("The LCM Machine");

            // Ask for pairs to process
            System.out.printf("How many pairs to process (1-9)? ");
            pairs = (int)(readChar('1','9')-48);

            // Calculate and display desired pairs of lcms
            System.out.printf("Numbers    LCM\n");
            for(int i=0; i<pairs; i++) {
                int a = getRandom(1,100);
                int b = getRandom(1,100);
                System.out.printf("%3d%4d%7d\n", a, b, lcm(a,b));
            }
            System.out.printf("\n");

            // Ask if user wants to keep going
            System.out.printf("Want to go again? ");
            choice = readChar("yYnN");
        } while(choice=='Y');

        return 0;
    }
}
