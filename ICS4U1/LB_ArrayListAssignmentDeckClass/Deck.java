/* Bing Li
 * ICS4U1
 * Array Assignment 2: Deck Class using ArrayList
 */
import java.awt.*;
import java.util.*;

class Deck
{
    private ArrayList<Card> deck;

    public Deck()
    {
        deck = new ArrayList<Card>(0);
        for(int x=0; x<52; x++) // for each card in standard deck
            deck.add(new Card(x)); // create card
    }

    public void show(Graphics g)  // draws card face up or face down
    {
        for(int c=0; c<deck.size(); c++)
            deck.get(c).show(g,c%13*20+150,c/13*50+20);
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
    
    public void deal() // Eliminated the top card (position 0) by calling the other deal method
    {
        deal(0);
    }
    
    public void deal(int pos) // Eliminates the card at position pos
    {
        deck.remove(pos); // Remove the card
    }
    
    public void add(Card c) // Add a card to the end of the deck
    {
        deck.add(c);
    }
    
    public ArrayList search(Card c)
    {
        ArrayList pos = new ArrayList(0); // Make an arraylist with initial size 0
        
        // Go through the deck and look for the card
        for(int i=0; i<deck.size(); i++) {
            if(deck.get(i).equals(c)) pos.add(i); // Add the position to the arraylist
        }
        
        return pos; // Return the arraylist
    }
    
    public void flip(int pos)
    {
        deck.get(pos).flip();
    }

    public void quickSort() // Calls the recursive quicksort method
    {
        quickSort(0,deck.size()-1);
    }
    
    private void quickSort(int low, int high)
    {
        int l = low;
        int h = high;
        int pivot = deck.get((l+h)/2).getRank();
        
        while(l<=h) {
            // Find a card that does not belong to left side of the pivot point
            while(deck.get(l).getRank()<pivot) {
                l++;
            }
            
            // Find a card that does not belong to the right side of the pivot point
            while(deck.get(h).getRank()>pivot) {
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
    
    public void selectionSort() // Sorts the deck by suit(S,H,D,C) and rank (A,K,..,2)
    {
        int cur = 0; // Current sorted position in array
        
        // Selection sort: find the next element and put it into the current position
        for(int i=0; i<deck.size()-1; i++) { // Will take at most length-1 tries
            // Look through the array for the next lowest element
            // Only have to look from cur to the end since everything before cur is sorted
            int min = cur;
            for(int j=cur+1; j<deck.size(); j++) {
                // Compare suit*13+rank to get which one has a smaller value
                // but invert rank (12-rank) so it will be A-2
                if(deck.get(min).getSuit()*13+(12-deck.get(min).getRank()) > deck.get(j).getSuit()*13+(12-deck.get(j).getRank()))
                    min = j;
            }
            
            // Swap positions
            Card temp = deck.get(min);
            deck.set(min,deck.get(cur));
            deck.set(cur,temp);
            
            cur++; // Increment current position
        }
    }
    
    public int length()
    {
        return deck.size();
    }
    
    public boolean getFaceup(int pos)
    {
        return deck.get(pos).getFaceup();
    }
}