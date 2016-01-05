/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.util.*;

public class Computer extends Hand
{
    public Computer()
    {
        super();
    }
    
    public Card choose() // Currently returns the first playable card
    {
        for(int i=0; i<deck.size(); i++) {
            if(on.get(i)) {
                Card choice = deck.get(i);
                deck.remove(i); // Remove the card that was just played
                return choice; // Return the first playable card
            }
        }
        
        return new Card(0); // If somehow no cards picked, return 2 of spades
    }
    
    public Card chooseSuit() // Picks the suit with the most occurances
    {
        int choice = 0; // Ends up being the suit chosen
        int[] count = {0,0,0,0}; // Counts the occurance of the number of suits
        
        for(int i=0; i<deck.size(); i++) {
            count[deck.get(i).getSuit()]++; // Increments the cuit of the current card
            if(count[deck.get(i).getSuit()]>count[choice]) choice = deck.get(i).getSuit(); // Updates choice
        }
        
        return new Card(choice*13+6);
    }
}
