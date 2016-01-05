/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import java.util.*;

abstract class Hand extends Deck
{
    protected ArrayList<Boolean> on;
    
    public Hand() // Initialize hand to nothing
    {
        super();
        on = new ArrayList<Boolean>(0);
    }
    
    public void add(Card c) // Add a card to the hand and also increase the on arraylist
    {
        super.add(c);
        on.add(true);
    }
    
    public void remove(int position) // Remove the card at position, used after a card has been played
    {
        super.remove(position); // Remove the card from the hand itself
        on.remove(position-1); // Remove the boolean for the corresponding card
    }
    
    public void disable(int index) // Disable the card
    {
        on.set(index,false);
    }
    
    public void enable(int index) // Enable the card
    {
        on.set(index,true);
    }
    
    public int canPlay() // Returns how many cards can be played from the hand
    {
        int playable = 0;
        
        for(int i=0; i<on.size(); i++) // Loops through the on ArrayList
            if(on.get(i)) playable++; // Increments playable if the card can be played
            
        return playable;
    }
    
    public boolean canPlay(int position) // Checks if a specific card can play
    {
        return on.get(position-1);
    }
    
    abstract Card choose(); // Choosing a card to play
    
    abstract Card chooseSuit(); // Choosing a suit when an 8 is played
}
