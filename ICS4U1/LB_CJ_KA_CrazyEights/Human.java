/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.util.*;

public class Human extends Hand
{
    CrazyEights game;
    
    public Human(CrazyEights g)
    {
        super();
        game = g;
    }
    
    public Card choose() // Let the player choose a card
    {
        int c = game.getChoice();
        
        Card choice = get(c);
        super.remove(c); // Remove the card that was just played
        return choice; // Return the picked card
    }
    
    public Card chooseSuit() // let the player choose a suit
    {
        return new Card((new ChooseSuit()).suitChange());
    }
}
