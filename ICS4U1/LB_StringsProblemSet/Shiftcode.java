/* Bing Li
 * ICS4U1
 * September 22, 2015
 * Strings Problem Set
 */

import java.util.Scanner;

public class Shiftcode
{
    public static String main(String message, int shift)
    {
        Scanner in = new Scanner(System.in);
        
        // Variable declaration
        String cipher = "";
        
        // Make message all uppercase to simplify things
        message = message.toUpperCase();
        
        // Every 26 shifts there is no apparent change, so mod 26 to prevent errors on wraparound
        shift = shift%26;
        
        // Go through the message and shift the letters
        for(int i=0; i<message.length(); i++) {
            char temp = message.charAt(i);
            
            // Shift only if a letter, other wise just put it into the cipher
            if(message.charAt(i)>='A' && message.charAt(i)<='Z') {
                temp+=shift;
                // Do a wraparound if needed
                if(temp>'Z') temp-=26;
                if(temp<'A') temp+=26;
            }
            
            cipher+=temp;
        }
        
        return cipher;
    }
}
