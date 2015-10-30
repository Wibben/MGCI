/* Bing Li
 * ICS4U1
 * September 11, 2015
 * Problem Set 2
 */
import java.util.Scanner;

public class Arithmetic
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        int a,b;
        int sum,product,difference;
        double quotient;
        
        // Get user input
        System.out.printf("Operand #1: ");
        a = in.nextInt();
        in.nextLine();
        
        System.out.printf("Operand #2: ");
        b = in.nextInt();
        in.nextLine();
        
        // Calculate answers
        sum = a+b;
        difference = a-b;
        product = a*b;
        quotient = (double)a/b;
        
        // Display answers
        System.out.printf("%d + %d = %d\n", a, b, sum);
        System.out.printf("%d - %d = %d\n", a, b, difference);
        System.out.printf("%d * %d = %d\n", a, b, product);
        System.out.printf("%d / %d = ", a, b);
        if(b==0) System.out.printf("undefined\n");
        else  System.out.printf("%.3f\n", quotient);
        
        return 0;
    }
}
