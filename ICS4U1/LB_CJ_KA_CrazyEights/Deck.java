/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import java.util.*;

class Deck
{
    protected ArrayList<Card> deck;

    public Deck() // Create the deck 
    {
        deck = new ArrayList<Card>(0);
    }
    
    public void initialize() // Initialize the deck to be a full deck
    {
        for(int x=0; x<52; x++) // for each card in standard deck
            deck.add(new Card(x)); // create card
    }
    
    public void initialize(Deck d) // Initialize the deck to be another deck
    {
        for(int i=0; i<d.length(); i++)
            deck.add(d.get(i+1)); // Get the card from d
    }

    public void shuffle() // Shuffles the deck
    {
        if(deck.size()==0) return; // Don't do anything is there is nothing in the deck
        
        // Randomly switches the position of 2 cards 50 times
        for(int i=0; i<50; i++) {
            int a = (int)(Math.random()*deck.size());
            int b = (int)(Math.random()*deck.size());
            
            Card temp = deck.get(a);
            deck.set(a,deck.get(b));
            deck.set(b,temp);
        }
    }
    
    public void deal() // Eliminated the top card (last position)
    {
        deck.remove(deck.size()-1); // Remove the card
    }
    
    public void flip(int pos)
    {
        deck.get(pos).flip();
    }
    
    public void sort() // Calls the recursive quicksort method
    {
        quickSort(0,deck.size()-1);
    }
    
    private void quickSort(int low, int high)
    {
        int l = low;
        int h = high;
        int pivot = deck.get((l+h)/2).getSuit()*13+(deck.get((l+h)/2).getRank());
        
        while(l<=h) {
            // Find a card that does not belong to left side of the pivot point
            while(deck.get(l).getSuit()*13+deck.get(l).getRank()<pivot) {
                l++;
            }
            
            // Find a card that does not belong to the right side of the pivot point
            while(deck.get(h).getSuit()*13+deck.get(h).getRank()>pivot) {
                h--;
            }
            
            // If we have found both a card that does not belong on the left side and
            // a card that does not belong on the right side, swap their places
            if(l<=h) {
                Card temp = deck.get(l);
                deck.set(l,deck.get(h));
                deck.set(h,temp);
                l++;
                h--;
            }
        }
        
        // Recursive call
        if(low<h) quickSort(low,h);
        if(l<high) quickSort(l,high);
    }
    
    public int length()
    {
        return deck.size();
    }
    
    public Card get(int c)
    {
        return deck.get(c-1);
    }
    
    public void add(Card c)
    {
        deck.add(c);
    }
    
    public void remove(int position)
    {
        deck.remove(position-1); // Remove the card at position
    }
}