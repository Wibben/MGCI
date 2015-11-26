/* Bing Li
 * ICS4U1
 * Array Assignment 1: Deck Class
 */
import java.awt.*;
import java.util.*;

class Deck
{
    private Card deck[];

    public Deck()
    {
        deck = new Card[52];
        for(int x=0; x<52; x++) { // for each card in standard deck
            deck[x] = new Card(x); // create card
        }
    }

    public void show(Graphics g)  // draws card face up or face down
    {
        for(int c=0; c<deck.length; c++) {
            deck[c].show(g,c%13*20+150,c/13*50+20);
        }
    }

    public void shuffle() // Shuffles the deck
    {
        if(deck.length==0) return; // Don't do anything is there is nothing in the deck
        
        // Randomly switches the position of 2 cards 50 times
        for(int i=0; i<50; i++) {
            int a = (int)(Math.random()*deck.length);
            int b = (int)(Math.random()*deck.length);
            
            Card temp = deck[a];
            deck[a] = deck[b];
            deck[b] = temp;
        }
    }
    
    public void deal() // Eliminated the top card (position 0) by calling the other deal method
    {
        deal(0);
    }
    
    public void deal(int pos) // Eliminates the card at position pos
    {
        Card[] temp = new Card[Math.max(0,deck.length-1)]; // Make a new card array with 1 less card
        
        // Loop from 0 to length of array, skipping over the card at position pos
        for(int i=0,cnt=0; i<deck.length && deck.length>1; i++,cnt++) { // cnt is a counter to keep track of position in temp
            if(i==pos) cnt--; // Skipping over the card at position pos, when dkipping over, decrement cnt so it will still be continuous
            else temp[cnt] = deck[i];
        }
        
        deck = temp; // Making deck the temp array
    }
    
    public void add(Card c) // Add a card to the end of the deck
    {
        Card[] temp = new Card[deck.length+1]; // Make a new card array with 1 more card
        
        // Copy old array to new array
        for(int i=0; i<deck.length; i++) {
            temp[i] = deck[i];
        }
        temp[temp.length-1] = c; // Add in the new card to the end
        
        deck = temp; // Making deck the temp array
    }
    
    public ArrayList search(Card c)
    {
        ArrayList pos = new ArrayList(0); // Make an arraylist with initial size 0
        
        // Go through the deck and look for the card
        for(int i=0; i<deck.length; i++) {
            if(deck[i].equals(c)) pos.add(i); // Add the position to the arraylist
        }
        
        return pos; // Return the arraylist
    }
    
    public void flip(int pos)
    {
        deck[pos].flip();
    }

    public void quickSort() // Calls the recursive quicksort method
    {
        quickSort(0,deck.length-1);
    }
    
    private void quickSort(int low, int high)
    {
        int l = low;
        int h = high;
        int pivot = deck[(l+h)/2].getRank();
        
        while(l<=h) {
            // Find a card that does not belong to left side of the pivot point
            while(deck[l].getRank()<pivot) {
                l++;
            }
            
            // Find a card that does not belong to the right side of the pivot point
            while(deck[h].getRank()>pivot) {
                h--;
            }
            
            // If we have found both a card that does not belong on the left side and
            // a card that does not belong on the right side, swap their places
            if(l<=h) {
                Card temp = deck[l];
                deck[l] = deck[h];
                deck[h] = temp;
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
        for(int i=0; i<deck.length-1; i++) { // Will take at most length-1 tries
            // Look through the array for the next lowest element
            // Only have to look from cur to the end since everything before cur is sorted
            int min = cur;
            for(int j=cur+1; j<deck.length; j++) {
                // Compare suit*13+rank to get which one has a smaller value
                // but invert rank (12-rank) so it will be A-2
                if(deck[min].getSuit()*13+(12-deck[min].getRank()) > deck[j].getSuit()*13+(12-deck[j].getRank()))
                    min = j;
            }
            
            // Swap positions
            Card temp = deck[min];
            deck[min] = deck[cur];
            deck[cur] = temp;
            
            cur++; // Increment current position
        }
    }
    
    public int length()
    {
        return deck.length;
    }
    
    public boolean getFaceup(int pos)
    {
        return deck[pos].getFaceup();
    }
}