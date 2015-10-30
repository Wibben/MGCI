import java.util.Scanner;

public class Interest
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        double principal,rate,interest;
        
        System.out.printf("Principal ($): ");
        principal = in.nextDouble();
        in.nextLine();
        
        System.out.printf("Interest Rate (%%): ");
        rate = in.nextDouble();
        in.nextLine();
        
        rate/=100.0;
        
        interest = principal*rate; principal+=interest;
        System.out.printf("Year 1 Interest = $%.2f\n", interest);
        interest = principal*rate; principal+=interest;
        System.out.printf("Year 2 Interest = $%.2f\n", interest);
        
        return 0;
    }
}
