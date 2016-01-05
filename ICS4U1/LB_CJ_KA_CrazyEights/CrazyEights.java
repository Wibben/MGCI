/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import javax.swing.event.*;  // Needed for ActionListener
import javax.imageio.*;
import java.io.*;

public class CrazyEights extends JFrame
{
    // Allow other classes to access this for a second
    /** CHANGE WHEN INTEGRATING GRAPHICS */
    public static final String[] suit = new String[]{"\u2660","\u2665","\u2663","\u2666"};
    public static final String[] rank = new String[]{"2","3","4","5","6","7","8","9","10","J","K","Q","A"};

    private Deck deck,discard;
    private Card inPlay;
    private Hand[] hands;
    private boolean left,endGame;
    private int cur = 0; // Keeps track of current player
    private int computers,players;

    public CrazyEights(int players, int computers)
    {
        // Initializing components
        this.computers = computers;
        this.players = players;
        deck = new Deck();
        discard = new Deck();
        hands = new Hand[players+computers];
        deck.initialize();
        left = true; // Moving clock wise
        endGame = false;

        deck.shuffle(); // Shiffle the deck before starting

        for(int i=0; i<players; i++)
            hands[i] = new Human(this);
        for(int i=players; i<computers+players; i++)
            hands[i] = new Computer();

        for(int i=0; i<8; i++) { // Start out with 8 cards per person
            for(int j=0; j<4; j++) { // Each person is dealt 1 card at a time
                hands[j].add(deal());
            }
        }

        // Deal out the first card into play
        inPlay = deal();

        for(int i=0; i<4; i++) { // Presort and check which cards can be played on the first card
            hands[i].sort(); // Sort the hand
            check(inPlay); // Check which cards can be played after being sorted
        }

        gui(this);
    }

    private Card deal() // Deals the top card and removes it from the deck
    {
        if(deck.length()==0) // If there's no more cards to deal out, make the discard deck the deck
        {
            deck.initialize(discard);
            deck.shuffle();
            discard = new Deck();
        }

        Card c = deck.get(deck.length());
        deck.deal();

        return c;
    }

    public void play() // Begins playing the game
    {
        Card picked = new Card(0); // Used to store the card the player/computer picks

        // Check each player's hands and grey out accordingly as well as allow each player to play the card
        while(!endGame) {
            if(hands[cur].canPlay()==0) hands[cur].add(deal()); // Auto pickup if cannot play

            hands[cur].sort(); // Sort the hand
            check(inPlay); // Check which cards can be played after being sorted
            repaint();
            
            if(hands[cur].canPlay()>0) {
                picked = hands[cur].choose(); // Only allow play if you can play
                discard.add(inPlay); // Put the current card into the discard pile
                inPlay = picked; // Set the chosen card as the current card
                special(inPlay); // invokes the special effects of the card in play
                repaint();
            }

            if(hands[cur].length()==0) {
                endGame = true; // End the game if player has no cards
                End e = new End(cur);
            }

            // Just a little gap after playing
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.printf("Interrupted Exception\n");
            }

            cur += (left ? 1:-1); // Determines next player depending on direction of play
            cur = (cur+4)%(4); // This makes sure cur is withing [0,computers+players-1] and wraps around when it is -1 or computers+players
        }
        
        dispose(); // Close the window
    }

    private void check(Card c) // Checks which cards can be played and disables them accordingly
    {
        for(int i=1; i<=hands[cur].length(); i++) { // Disable unplayable cards and enable everything else
            if(hands[cur].get(i).getRank()!=6 && hands[cur].get(i).getRank()!=c.getRank() && hands[cur].get(i).getSuit()!=c.getSuit()) hands[cur].disable(i-1); // Rank 6 is actually an 8
            else hands[cur].enable(i-1);
        }
        
        // Always check player's hand and update after each card has been played
        for(int i=1; i<=hands[0].length(); i++) {
            if(hands[0].get(i).getRank()!=6 && hands[0].get(i).getRank()!=c.getRank() && hands[0].get(i).getSuit()!=c.getSuit()) hands[0].disable(i-1); // Rank 6 is actually an 8
            else hands[0].enable(i-1);
        }
    }

    private void special(Card c) // Invokes the special effect of the card if it has any
    {
        int increment = (cur+(left ? 5:3))%4; // Determines which player the card affects

        if(c.getRank()==0) { // Make the next player pickup 2
            hands[increment].add(deal());
            hands[increment].add(deal());
            hands[increment].sort();
            check(inPlay);
        } else if(c.getRank()==2) { // Change the directions
            left = !left;
        } else if(c.getRank()==6) { // Let the player pick the suit and change the card in play
            inPlay = hands[cur].chooseSuit();
            check(inPlay);
        } else if(c.getRank()==9) { // Skips the next player's turn
            cur=increment;
        } else if(c.getSuit()==0 && c.getRank()==10) { // Make the next player pick up 5
            hands[increment].add(deal());
            hands[increment].add(deal());
            hands[increment].add(deal());
            hands[increment].add(deal());
            hands[increment].add(deal());
            hands[increment].sort();
            check(inPlay);
        }
    }

    /**************************************************************************************
     * -------   |     |   -------        -------   -------   -------   -------   ------- *
     * |         |     |      |           |     |   |     |   |     |      |      |       *
     * |         |     |      |           |     |   |     |   |     |      |      |       *
     * |   ---   |     |      |           |------   |-----|   |\-----      |      ------- *
     * |     |   |     |      |           |         |     |   | \          |            | *
     * |     |   |     |      |           |         |     |   |  \         |            | *
     * -------   -------   -------        |         |     |   |   \__      |      ------- *
     **************************************************************************************
     */
    private Image back,gear,question;
    private int position;

    private void gui(CrazyEights game)
    {
        // Initializing components
        try {
            question = ImageIO.read(new File("instructions.gif")); // Set the image for the instructions icon
            gear = ImageIO.read(new File("settings.gif")); // Set the image for the settings icon
            back = ImageIO.read(new File("cards\\b1.gif")); // Set the back
        } catch (IOException e) {
            System.out.println("File not found");
        }

        // Setting up GUI components
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        DrawArea board = new DrawArea(1000,700);
        board.addMouseListener(new MouseAdapter() 
            {
                public void mousePressed(MouseEvent e) 
                {
                    int x = e.getX();
                    int y = e.getY();
                    int numCards = hands[0].length(), start = 500 - (numCards*15+58)/2;                    
                    if (x >= start && x <= start + (numCards-1)*15 && y >= 570 && y <= 667 && cur==0) {
                        int num = x-start;                        
                        position = num / 15 +1; //the card position in player's hand
                        if(hands[0].canPlay(position)) cur = 1;
                    } else if(x >= start+(numCards-1)*15 && x <= start+(numCards-1)*15+73 && y >= 570 && y <= 667 && cur==0) {
                        position = numCards;
                        if(hands[0].canPlay(position)) cur = 1;
                    } else if(x>=960 && x<=1000 && y>=0 && y<=40) {
                        Settings s = new Settings(game);
                    } else if(x>=920 && x<=960 && y>=0 && y<=40) {
                        Instructions instruct = new Instructions();
                    }
                }
            });

        content.add(board);

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("CrazyEights");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre window
        setVisible(true);
        
        // Displaying Instructins
        Instructions instruct = new Instructions();
    }
    
    public int getChoice()
    {
        while(cur==0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.printf("Interrupted Exception\n");
            }
        }
        cur--;
        
        return position;
    }

    class DrawArea extends JPanel
    {
        public DrawArea(int width, int height)
        {
            this.setPreferredSize(new Dimension(width,height)); // size
        }

        public void paintComponent(Graphics g)
        {
            gradient(g);

            // Draw card in play and deck
            g.drawImage(back,422,302,null);
            inPlay.show(g,505,302);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Verdana",Font.BOLD,16));

            // Draw player hand (bottom)
            g.drawString("You",480,560);
            g.setColor(new Color(50,50,50,100)); // Set a transparent grey as the colour to grey out unplayable cards
            for(int i=0; i<hands[0].length(); i++) {
                hands[0].get(i+1).show(g,15*i+500-(hands[0].length()*15+58)/2,570);
                // If there is a card the player cannot play, it will be greyed out (layer of transparent grey over card)
                if(!hands[0].canPlay(i+1)) g.fillRect(15*i+500-(hands[0].length()*15+58)/2,570,73,97);
            }

            g.setColor(Color.BLACK); // Make sure the font is black and not trasnparent grey

            // Draw computer 1's hand (left)
            g.drawString("Johnny",120,360);
            for(int i=0; i<hands[1].length(); i++)
                g.drawImage(back,37,15*i+350-(hands[0].length()*15+72)/2,null);

            // Draw computer 2's hand (top)
            g.drawString("Bob",480,150);
            for(int i=0; i<hands[2].length(); i++)
                g.drawImage(back,15*i+500-(hands[0].length()*15+58)/2,33,null);

            // Draw computer 3's hand (right)
            g.drawString("Lisa",855,360);
            for(int i=0; i<hands[3].length(); i++)
                g.drawImage(back,900,15*i+350-(hands[0].length()*15+72)/2,null);
                
            // Draw a little red ball for the current player
            g.setColor(Color.RED);
            if(cur==0) g.fillOval(520,540,20,20);
            else if(cur==1) g.fillOval(190,340,20,20);
            else if(cur==2) g.fillOval(520,130,20,20);
            else if(cur==3) g.fillOval(830,340,20,20);

            // Draw the instructions question mark and settings gear on the top right corner
            g.drawImage(question,920,0,null);
            g.drawImage(gear,960,0,null);
        }

        // Oval gradient from middle out
        private void gradient(Graphics g)
        {
            // Start with solid colour background
            g.setColor(new Color(22,107,0));
            g.fillRect(0,0,1000,700);

            // Begin gradient
            for(int i=0; i<1000; i++) {
                g.setColor(new Color(22+(int)(0.058*i),107+(int)(0.114*i),(int)(0.043*i)));
                g.fillOval((int)(0.5*i),(int)(0.35*i),1000-1*i,700-(int)(0.7*i));
            }
        }
    }

    public void setBack(int choice) 
    {
        try {
            back = ImageIO.read(new File("cards\\b"+choice+".gif")); // Set the back
        } catch (IOException e) {
            System.out.println("File not found");
        }

        repaint(); // Update the GUI with the new backs
    }

    public static void main(String[] args)
    {
        CrazyEights game = new CrazyEights(1,3);

        game.play();
    }
}
