import java.util.Scanner;

public class Price
{
    public static int main()
    {
        Scanner in = new Scanner(System.in);
        
        double price;
        int quantity;
        
        System.out.printf("Enter item information\n\nPrice: ");
        price = in.nextDouble();
        in.nextLine();
        
        System.out.printf("Quantity: ");
        quantity = in.nextInt();
        in.nextLine();
        
        System.out.printf("--------------------\nHere is your receipt\n\n");
        System.out.printf("%d x %.2f = $%.2f\n\nThank you. Come again.\n", quantity, price, price*quantity);
        
        return 0;
    }
}
