/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import javax.imageio.*;
import java.io.*;

class Card
{
    private int rank, suit;
    private Image image;
    private boolean faceup;
    private static Image cardback; // shared image for back of card

    public Card(int cardNum)  // Creates card from 0-51
    {
        rank = cardNum%13;
        suit = cardNum/13;
        faceup = true;

        image = null;
        try {
            image = ImageIO.read(new File("cards\\"+(cardNum + 1)+".gif")); // load file into Image object
            cardback = ImageIO.read(new File("cards\\b1.gif")); // load file into Image object
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public void show (Graphics g, int x, int y)  // draws card face up or face down
    {
        if(faceup) g.drawImage(image,x,y,null);
        else g.drawImage(cardback,x,y,null);
    }
    
    public void flip()
    {
        faceup = !faceup; // make faceup the opposite of what it is right now
    }
    
    public boolean getFaceup()
    {
        return faceup;
    }
    
    public int getRank()
    {
        return rank;
    }
    
    public int getSuit()
    {
        return suit;
    }
    
    public boolean equals(Card c) // Checks if a card is the same
    {
        return (c.getRank()==rank && c.getSuit()==suit);
    }
}