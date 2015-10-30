/* Bing Li
 * ICS4U1
 * Line Class Assignment
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
        okayBtn.addActionListener(new okayBtnListener());

        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JTextArea _words = new JTextArea(15,28);
        _words.setLineWrap(true);
        _words.setWrapStyleWord(true); 
        _words.setEditable(false);
        _words.setFont(new Font("Veranda", Font.BOLD, 12));
        _words.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
        _words.setText(
            "Welcome to the Line Graphing Tool!\n\nTo graph a line please put in "+
            "an equation in any form in terms of x and y\n\nYou can graph multiple "+
            "equations by separating subsequent equations with commas\n\nYou can also "+
            "also set your own doman and range by entering the preferred domain and "+
            "range at the bottom of the screen and graphing using the \"Custom Domain Button\"\n\n"+
            "You can also pan using the arrow keys!\nHover over components to see details"
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
    }

    class okayBtnListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    }
}
