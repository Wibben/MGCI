/* Bing Li
 * ICS4U1
 * September 22, 2015
 * Strings Problem Set
 */

import java.util.Scanner;

public class Menu
{
    public static Scanner in;
    
    public static void palindrome()
    {
        // Variable declaration
        String message;
        
        // Get user input
        System.out.printf("What word would you like to check?\n");
        message = in.nextLine();
        
        // Output results
        System.out.printf("\n%s\n", Palindrome.main(message)==true ? "It is a palindrome":"It is not a palindrome");
    }
    
    public static void shiftcode()
    {
        // Variable declaration
        String message;
        int shift;
        
        // Get user input
        System.out.printf("What is the message you want to cipher?\n");
        message = in.nextLine();
        System.out.printf("How much do you want to shift? ");
        shift = in.nextInt();
        in.nextLine();
        
        // Output ciphertext
        System.out.printf("\n%s shifted by %d is %s\n", message, shift, Shiftcode.main(message,shift));
    }
    
    public static void cryptocode()
    {
        // Variabel declaration
        String message,cipher;
        
        // Get user input
        System.out.printf("What is the message you want to cipher?\n");
        message = in.nextLine();
        
        // Assign resutls to cipher since results are in 2 parts
        cipher = Cryptocode.main(message);
        
        // Output cipher
        System.out.printf("\n%s with scrambled alphabet %s becomes %s\n", message, cipher.substring(0,26), cipher.substring(26));
    }
    
    public static int main()
    {
        in = new Scanner(System.in);
        
        // Variable declaration
        char choice;
        
        do {
            // Displaying menu
            System.out.printf("\u000C");
            System.out.printf("Please select a Program:\n\n");
            System.out.printf("1 - Palindrome\n");
            System.out.printf("2 - Shiftcode\n");
            System.out.printf("3 - Cryptocode\n");
            System.out.printf("0 - Exit\n");
            
            // Getting choice
            choice = in.nextLine().charAt(0);
            
            // Running program based on choice
            System.out.printf("\u000C");
            if(choice=='1') palindrome();
            else if(choice=='2') shiftcode();
            else if(choice=='3') cryptocode();
            
            // Wait for user
            System.out.printf("\nEnter enter to continue...");
            in.nextLine();
        } while(choice!='0');
        
        return 0;
    }
}
