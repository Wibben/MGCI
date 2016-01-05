/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChooseSuit extends JFrame
{
    private int choice;
    public boolean choosing;
    
    public ChooseSuit()
    {
        // Initialize components
        choosing  = true;

        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JTextArea _words = new JTextArea(2,2);
        DrawArea display = new DrawArea(229,301);
        
        // Make the text area uneditable and set font and margins
        _words.setEditable(false);
        _words.setFont(new Font("Veranda", Font.BOLD, 12));
        _words.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        _words.setText("Choose suit with with arrowkeys\nEnter to confirm.");

        // Add components to content panes
        content.add(display,"North");
        content.add(_words,"South");
        
        // Setting up keybindings
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "spades");
        content.getActionMap().put("spades", new ChooseAction(1));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "hearts");
        content.getActionMap().put("hearts", new ChooseAction(2));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "clubs");
        content.getActionMap().put("clubs", new ChooseAction(3));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "diamonds");
        content.getActionMap().put("diamonds", new ChooseAction(4));
        content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released ENTER"), "confirm");
        content.getActionMap().put("confirm", new ChooseAction(5));

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Choose a Suit!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    class ChooseAction extends AbstractAction
    {
        private int dir;
        
        public ChooseAction(int direction) 
        {
            // Set direction
            dir = direction;
        }
        
        public void actionPerformed(ActionEvent e) {
            if(dir==5) choosing = false;
            else choice = (dir-1)*13+6;
            
            repaint();
        }
    }
    
    class DrawArea extends JPanel
    {
        public DrawArea(int width, int height)
        {
            // Setting up drawing area
            this.setPreferredSize(new Dimension(width,height));
        }

        public void paintComponent (Graphics g)
        {
            // Put a red border of width 3 around the current card by drawing a bigger rectangle behind it
            g.setColor(Color.RED);
            if(choice==6) g.fillRoundRect(75,2,79,103,3,3);
            else if(choice==19) g.fillRoundRect(148,99,79,103,3,3);
            else if(choice==32) g.fillRoundRect(75,196,79,103,3,3);
            else if(choice==45) g.fillRoundRect(2,99,79,103,3,3);
            
            (new Card(6)).show(g,78,5);
            (new Card(19)).show(g,151,102);
            (new Card(32)).show(g,78,199);
            (new Card(45)).show(g,5,102);
        }
    }
    
    public int suitChange()
    {
        setVisible(true); // Make the window visible
        
        while(choosing){} // Have it freeze as long as the user is still choosing
        
        dispose(); // Get rid of the window
        
        return choice;
    }
}
