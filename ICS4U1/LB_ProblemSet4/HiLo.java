/* Bing Li
 * ICS4U1
 * September 15, 2015
 * Problem Set 4
 */

import java.util.Scanner;
 
public class HiLo
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        int random,prevRandom,point=0;
        char guess;
        boolean keepGoing=true;
        
        // Generate number and get user input
        random = 1+(int)(Math.random()*100);
        System.out.printf("The number is %3d. Next one? (h/l) ", random);
        guess = in.nextLine().charAt(0);
        
        // Regenerate and get user input as long as correct
        while(keepGoing) {
            prevRandom = random;
            random = 1+(int)(Math.random()*100);
            System.out.printf("The number is %3d. ", random);
            if((random>=prevRandom && guess=='h') || (random<=prevRandom && guess=='l')) {
                System.out.printf("Next one? (h/l) ");
                guess = in.nextLine().charAt(0);
                point++;
            } else keepGoing=false;
        }
        
        // Display points earned
        System.out.printf("You scored %d points.", point);
        
        return 0;
    }
}
