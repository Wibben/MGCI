/* Bing Li
 * ICS4U1
 * September 11, 2015
 * Problem Set 2
 */
import java.util.Scanner;

public class Equation
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        int a,b;
        double ans;
        
        // Get user input
        System.out.printf("Enter values for Ax + B = 0\n");
        System.out.printf("A: ");
        a = in.nextInt();
        in.nextLine();
        
        System.out.printf("B: ");
        b = in.nextInt();
        in.nextLine();
        
        // Calculate answer
        ans = (double)-b/a;
        
        // Display answer
        System.out.printf("%dx + %d = 0\n", a, b);
        System.out.printf("%dx = %d\n", a, -b);
        System.out.printf("x = %d/%d\n", -b, a);
        System.out.printf("x = %.3f\n", ans);
        
        return 0;
    }
}
