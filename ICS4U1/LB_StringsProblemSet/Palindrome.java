/* Bing Li
 * ICS4U1
 * September 22, 2015
 * Strings Problem Set
 */

import java.util.Scanner;

public class Palindrome
{
    public static boolean main(String message)
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        String forward,backward;
        forward = backward = "";
        
        // Convert to uppercase so comparing is easier
        message = message.toUpperCase();
        
        // Copying the message forwards and backwards but leaving out anything that is not a letter
        for(int i=0; i<message.length(); i++) {
            if(message.charAt(i)>='A' && message.charAt(i)<='Z') {
                forward = forward+message.substring(i,i+1);
                backward = message.substring(i,i+1)+backward;
            }
        }
        
        // Returns whether the messages match
        return forward.equals(backward);
    }
}
