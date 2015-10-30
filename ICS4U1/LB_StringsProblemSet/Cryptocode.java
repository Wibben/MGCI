/* Bing Li
 * ICS4U1
 * September 22, 2015
 * Strings Problem Set
 */

import java.util.Scanner;

public class Cryptocode
{   
    public static String main(String message)
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        StringBuffer cipher = new StringBuffer(message.toUpperCase());
        StringBuffer alphabet = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        
        // Making a scrambled alphabet by making 50 random character swaps
        for(int i=0; i<50; i++) {
            // Figure out which positions to swap
            int a = (int)(Math.random()*26);
            int b = (int)(Math.random()*26);
            
            // Do the swap
            char temp = alphabet.charAt(a);
            alphabet.setCharAt(a,alphabet.charAt(b));
            alphabet.setCharAt(b,temp);
        }
        
        // Uncomment to test if swapping properly
        // Should output "TWF'N UC RWUER?" with input "HOW’S IT GOING?"
        // alphabet = new StringBuffer("HOAZXJRTUYBIVEWKLSNCDMFGPQ");
        
        // Switch out the characters to produce the cipher
        for(int i=0; i<cipher.length(); i++)
            if(cipher.charAt(i)>='A' && cipher.charAt(i)<='Z')
                cipher.setCharAt(i,alphabet.charAt(cipher.charAt(i)-65));
        
        // The first 26 letters are the scrambled alphabet, the rest is the ciohertext
        return alphabet.toString()+cipher.toString();
    }
}
