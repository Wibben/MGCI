/* Bing Li
 * ICS4U1
 * Inheritance Reading Assignment
 */
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame
{
    Entity[] enemy = new Entity[3];
    Player player;
    JButton[] attackBtn = new JButton[3];
    JLabel playerStats;
    
    public GUI()
    {
        /**
         * Although the following two lines of code can be condensed into one line:
         *      player = new Player();, 
         * I am using it to demonstrate the fact that an object typed Entity
         * can be used to store information of any of its subclasses
         * 
         * However, if I were to simply assign p to player, it would be illegal since p is typed Entity,
         * which means that it could represent any of Entity's subclasses, therefore I must
         * type cast p into a Player type to assign it to the player variable
         */
        // Initialize components
        Entity p = new Player();
        player = (Player)(p);
        playerStats = new JLabel("Player: "+player.showStats());
        
        // There will be 3 enemies
        // Randomly picked out of goblins and skeletons
        for(int i=0; i<3; i++) {
            if(0.5>Math.random()) {
                enemy[i] = new Skeleton();
            } else {
                enemy[i] = new Goblin();
            }
        }
        
        // Create content panes and set layouts
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        JPanel buttons  = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.LEFT, 45, 10));
        DrawArea display = new DrawArea(400,220);
        
        // Add componets to content panes
        for(int i=0; i<3; i++) {
            attackBtn[i] = new JButton("Attack");
            attackBtn[i].addActionListener(new attackBtnListener(i));
            buttons.add(attackBtn[i]);
        }
        content.add(playerStats, "North");
        content.add(display, "Center");
        content.add(buttons, "South");
        
        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    class attackBtnListener implements ActionListener
    {
        private int n;
        
        public attackBtnListener(int n)
        {
            this.n = n;
        }
        
        public void actionPerformed(ActionEvent e)
        {
            boolean alldead = true;
            System.out.printf("\u000c");
            player.attack(enemy[n]);
            
            for(int i=0; i<3; i++) {
                enemy[i].attack(player);
                if(!enemy[i].isDead()) {
                    alldead = false;
                    attackBtn[i].setEnabled(true);
                } else attackBtn[i].setEnabled(false);
            }
            
            if(alldead) endGame("You win!");
            else if(player.isDead()) endGame("You lose!");
            
            // Update graphics
            repaint();
        }
        
        public void endGame(String message)
        {
            // Changes the current window instead of creating a new window
            JLabel msg = new JLabel(message);
            msg.setFont(new Font("Serif", Font.BOLD, 32));
            
            JPanel content = new JPanel();
            content.add(msg);
            
            setContentPane(content);
            pack();
            setLocationRelativeTo(null);
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
            playerStats.setText("Player: "+player.showStats());
            // Sprite sizes:
            // Skeleton: 131 x 191
            // Goblin: 131 x 190
            // Therefore for the sprites to be evenly spaced out
            // they must be placed at x = 0, 134, 269
            enemy[0].redraw(g,0,20);
            enemy[1].redraw(g,134,20);
            enemy[2].redraw(g,269,20);
        }
    }
    
    public static int main()
    {
        GUI window = new GUI();
        
        window.setVisible(true);
        
        return 0;
    }
}
