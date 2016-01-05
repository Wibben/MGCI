/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class End extends JFrame
{
    private String[] names = {"You","Bob","Johnny","Lisa"};
    
    public End(int winner)
    {
        // Initializing components
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        
        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JLabel display = new JLabel("<html><FONT SIZE=24><FONT COLOR=RED>"+names[winner]+" </FONT><FONT COLOR=BLUE>"+(winner==0 ? "win":"wins")+"!</FONT></FONT></html>");

        // Add components to content panes
        content.add(display,"Center");
        content.add(close,"South");
        
        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Choose a Back!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
