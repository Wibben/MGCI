/* Bing Li, Jaitra Chaudhuri, Ahil Khuwaja
 * ICS4U1
 * Crazy Eights
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class Settings extends JFrame
{
    private int choice;
    
    public Settings(CrazyEights game)
    {
        // Initializing components
        choice = 1;
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.setBack(choice);
                dispose(); // Close the window
            }
        });
        
        // Create content pane, set layout
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        DrawArea display = new DrawArea(435,107);

        // Add components to content panes
        content.add(display,"Center");
        content.add(confirm,"South");
        
        // Adding mouselisteners
        display.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) { // Check for the event when mouse is released (finished pressing)
                int x = e.getX();
                int y = e.getY();
                
                // Check which horizontal range it is in and change the value fo choice
                for(int i=1; i<=5 && y>5 && y<103; i++) { // Make sure it is in the vertical range
                    if(x>i*88-83 && x<i*88+1) choice = i;
                }
                
                repaint(); // Update the interface
            }
        });
        
        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Choose a Back!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
            g.setColor(Color.RED); // Draw a bigger red rectangle behind the background to act as a border
            g.fillRoundRect(choice*88-86,2,79,103,3,3);
            
            for(int i=1; i<=5; i++) {
                Image img;
                try {
                    img = ImageIO.read(new File("cards\\b"+i+".gif")); // load file into Image object
                    g.drawImage(img,i*88-83,5,null); // Draw the image
                } catch (IOException e) {
                    System.out.println("File not found");
                }
            }
        }
    }
}
