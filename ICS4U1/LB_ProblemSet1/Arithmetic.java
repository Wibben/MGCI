import java.util.Scanner;

public class Arithmetic
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        double a,b;
        
        System.out.printf("Operand #1: ");
        a = in.nextDouble();
        in.nextLine();
        
        System.out.printf("Operand #2: ");
        b = in.nextDouble();
        in.nextLine();
        
        System.out.printf("%d + %d = %d\n", (int)a, (int)b, (int)(a+b));
        System.out.printf("%d - %d = %d\n", (int)a, (int)b, (int)(a-b));
        System.out.printf("%d * %d = %d\n", (int)a, (int)b, (int)(a*b));
        System.out.printf("%d / %d = %.3f\n", (int)a, (int)b, a/b);
        
        return 0;
    }
}
