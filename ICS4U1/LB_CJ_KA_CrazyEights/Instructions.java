/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Instructions extends JFrame
{
    public Instructions()
    {
        // Initialize components
        JButton okayBtn = new JButton("OK");
        okayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JTextArea _words = new JTextArea(18,58);
        _words.setLineWrap(true);
        _words.setWrapStyleWord(true); 
        _words.setEditable(false);
        _words.setFont(new Font("Consolas", Font.BOLD, 12));
        _words.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        _words.setText(
            "Welcome to Crazy Eights!\n\nHere are the rules of the game:\n"+
            "1. The objective is to get rid of all your cards first\n"+
            "2. You must play a card the same suit or rank as the card shown in the middle\n"+
            "3. You can only play 1 card at a time\n"+
            "4. If you cannot play, you will draw a card automatically\n"+
            "5. If you still cannot play, your turn will be skipped\n"+
            "6. There are some special cards, they are:\n"+
            "    8  = Wild Card, you can play this on any card\n"+
            "    2  = Pick Up 2, the next player must pick up 2 cards\n"+
            "    J  = Skip Turn, skip the next player's turn\n"+
            "    4  = Flip Flow, the direction of play is reversed\n"+
            "    \u2660Q = Pick Up 4, the next player must pick up 5 cards\n"+
            "7. Have Fun!\n\nClick any non-greyed out cards to play it."
        );

        // Add components to content panes
        content.add(_words,"Center");
        content.add(okayBtn,"South");

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Instructions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
