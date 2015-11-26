/* Bing Li
 * ICS4U1
 * Array Assignment 2: Deck Class using ArrayList
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class GUI extends JFrame
{
    private Deck deck = new Deck();
    private JComboBox<String> sortOp = new JComboBox<String>(new String[]{"by Rank","by Suit and Rank"});
    private String[] suit = new String[]{"\u2660","\u2665","\u2663","\u2666"};
    private String[] rank = new String[]{"2","3","4","5","6","7","8","9","10","J","K","Q","A"};
    private JComboBox<String> searchSuit = new JComboBox<String>(suit),addSuit = new JComboBox<String>(suit);
    private JComboBox<String> searchRank = new JComboBox<String>(rank),addRank = new JComboBox<String>(rank);
    private JComboBox<Integer> position;
    private JButton shuffleBtn = new JButton("Shuffle");
    private JButton sortBtn = new JButton("Sort");
    private JButton searchBtn = new JButton("Search");
    private JButton dealBtn = new JButton("Deal");
    private JButton addBtn = new JButton("Add");

    public GUI()
    {
        // Initialize components
        BtnListener btnListener = new BtnListener(); // listener for all buttons

        shuffleBtn.addActionListener(btnListener);
        sortBtn.addActionListener(btnListener);
        searchBtn.addActionListener(btnListener);
        dealBtn.addActionListener(btnListener);
        
        addBtn.addActionListener(btnListener);
        
        Integer[] numbers = new Integer[52];
        for(int i=0; i<52; i++)
            numbers[i] = i+1;
        position = new JComboBox<Integer>(numbers);

        // Create content pane, set layout
        JPanel content = new JPanel();        // Create a content pane
        content.setLayout(new BorderLayout()); // Use BorderLayout for panel
        JPanel north = new JPanel(); // Shuffle, Sort, Search
        north.setLayout(new FlowLayout()); // Use FlowLayout for input area
        JPanel south = new JPanel(); // Deal, Add
        south.setLayout(new FlowLayout());

        DrawArea board = new DrawArea(600,400); // Area for cards to be displayed

        // Add the components to the input area.
        north.add(shuffleBtn);
        north.add(sortBtn);
        north.add(sortOp);
        north.add(searchBtn);
        north.add(searchSuit);
        north.add(searchRank);
        
        south.add(dealBtn);
        south.add(position);
        south.add(addBtn);
        south.add(addSuit);
        south.add(addRank);
        
        content.add(north,"North"); // Input area
        content.add(board,"Center"); // Deck display area
        content.add(south,"South");

        // Set this window's attributes.
        setContentPane(content);
        pack();
        setTitle("Deck Demo");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           // Center window.
    }

    // put ActionListener class for your buttons here
    class BtnListener implements ActionListener // Button menu
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Shuffle")) deck.shuffle(); // shuffle deck
            else if(e.getActionCommand().equals("Sort") && ((String)sortOp.getSelectedItem()).equals("by Rank")) deck.quickSort(); // sort deck
            else if(e.getActionCommand().equals("Sort") && ((String)sortOp.getSelectedItem()).equals("by Suit and Rank")) deck.selectionSort(); // sort deck
            else if(e.getActionCommand().equals("Add")) {
                if(deck.length()==0) { // Enable everything if it was disabled
                    shuffleBtn.setEnabled(true);
                    sortBtn.setEnabled(true);
                    sortOp.setEnabled(true);
                    searchBtn.setEnabled(true);
                    searchSuit.setEnabled(true);
                    searchRank.setEnabled(true);
                    dealBtn.setEnabled(true);
                    position.setEnabled(true);
                }
                deck.add(new Card(parseSuit("Add")*13+parseRank("Add"))); // Add the selected card
                position.addItem(new Integer(deck.length())); // Add to the end of the combobox
            } else if(e.getActionCommand().equals("Deal")) {
                deck.deal((int)position.getSelectedItem()-1); // Deal the card at the selected position
                position.removeItemAt(deck.length()); // Take away from the end of the combobox
                if(deck.length()==0) { // Disable buttons if there is nothing left
                    shuffleBtn.setEnabled(false);
                    sortBtn.setEnabled(false);
                    sortOp.setEnabled(false);
                    searchBtn.setEnabled(false);
                    searchSuit.setEnabled(false);
                    searchRank.setEnabled(false);
                    dealBtn.setEnabled(false);
                    position.setEnabled(false);
                }
            } else if(e.getActionCommand().equals("Search")) { // Find the positions of the cards and turn every other card upside down
                ArrayList foundPos = deck.search(new Card(parseSuit("Search")*13+parseRank("Search")));
                
                // FLip everything, then flip the searched cards again
                for(int i=0; i<deck.length(); i++)
                    deck.flip(i);
                for(int i=0; i<foundPos.size(); i++)
                    deck.flip((int)foundPos.get(i));
                
                searchBtn.setText("Reset"); // Update button text
            } else if(e.getActionCommand().equals("Reset")) { // Reset after search// Flip everything that is not face up
                for(int i=0; i<deck.length(); i++)
                    if(!deck.getFaceup(i)) deck.flip(i);
                
                searchBtn.setText("Search"); // Update button text
            }

            repaint(); // do after each action taken to update deck
        }
        
        private int parseSuit(String box)
        {
            String s = (String)(box.equals("Search") ? searchSuit:addSuit).getSelectedItem(); // Read from the correct combobox
            
            if(s.equals("\u2660")) return 0;
            else if(s.equals("\u2665")) return 1;
            else if(s.equals("\u2663")) return 2;
            else return 3;
        }
        
        private int parseRank(String box)
        {
            String r = (String)(box.equals("Search") ? searchRank:addRank).getSelectedItem(); // Read from the correct combobox
            
            if(r.equals("2")) return 0;
            else if(r.equals("3")) return 1;
            else if(r.equals("4")) return 2;
            else if(r.equals("5")) return 3;
            else if(r.equals("6")) return 4;
            else if(r.equals("7")) return 5;
            else if(r.equals("8")) return 6;
            else if(r.equals("9")) return 7;
            else if(r.equals("10")) return 8;
            else if(r.equals("J")) return 9;
            else if(r.equals("Q")) return 10;
            else if(r.equals("K")) return 11;
            else return 12;
        }
    }

    class DrawArea extends JPanel
    {
        public DrawArea(int width, int height)
        {
            this.setPreferredSize(new Dimension(width,height)); // size
        }

        public void paintComponent(Graphics g)
        {
            deck.show(g);
        }
    }

    public static int main()
    {
        GUI window = new GUI();
        window.setVisible(true);

        return 0;
    }
}