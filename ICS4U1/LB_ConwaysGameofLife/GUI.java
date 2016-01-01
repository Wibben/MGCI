/* Bing Li
 * ICS4U1
 * Conway's Game of Life
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // Needed for ActionListener
import javax.swing.event.*;  // Needed for ActionListener

class GUI extends JFrame
{
    private Colony colony = new Colony(0.5);
    private Timer t;
    private JComboBox<String> files = new JComboBox<String>(new String[]{"Clear","Still Life","Gosper Glider Gun","Flower","Random"});
    private JButton simulateBtn;
    private JTextField _name;
    private JLabel mode;
    private boolean dragging,pop;
    private Color highlight = new Color(176,213,230,75); // A light blue with 75% opacity
    private int x,y,ex,ey;

    public GUI()
    {
        // Initialize components
        dragging = false;
        pop = true;
        BtnListener btnListener = new BtnListener(); // listener for all buttons
        x = y = ex = ey = -1;

        simulateBtn = new JButton("Simulate");
        simulateBtn.addActionListener(btnListener);
        JButton modeBtn = new JButton("Change Mode");
        modeBtn.addActionListener(btnListener);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(btnListener);
        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(btnListener);

        // Create content pane, set layout
        mode = new JLabel("Mode: Populate");
        JPanel content = new JPanel();        // Create a content pane
        content.setLayout(new BorderLayout()); // Use BorderLayout for panel
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout()); // Use FlowLayout for input area
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout()); // Use FlowLayout for input area
        _name = new JTextField(10);

        DrawArea board = new DrawArea(500,500);
        board.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX()/5; y = e.getY()/5; // Get starting values
                dragging = true;
            }
            public void mouseReleased(MouseEvent e) {
                ex = e.getX()/5; ey = e.getY()/5; // Get ending values
                if(pop) colony.populate(y,x,ey,ex); // Populate or eradicate the calls within range
                else colony.eradicate(y,x,ey,ex);
                dragging = false;
                repaint();
            }
        });
        board.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                ex = e.getX()/5; ey = e.getY()/5; // Get current value
                repaint();
            }
            public void mouseMoved(MouseEvent e){}
        });

        // Add components to content area
        north.add(simulateBtn);
        north.add(mode);
        north.add(modeBtn);
        south.add(saveBtn);
        south.add(_name);
        south.add(loadBtn);
        south.add(files);
        
        content.add(north,"North");
        content.add(board,"Center"); // Output area
        content.add(south,"South");

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Life Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);           // Center window.
    }

    class BtnListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getActionCommand().equals("Simulate")) {
                simulateBtn.setText("Stop"); // Set the text to read Stop
                Movement moveColony = new Movement(); // ActionListener for timer
                t = new Timer(200,moveColony); // set up Movement to run every 200 milliseconds
                t.start(); // start simulation
            } else if(e.getActionCommand().equals("Stop")) {
                t.stop(); // Stop simulati
                simulateBtn.setText("Simulate"); // Set the text to read Simulate
            } else if(e.getActionCommand().equals("Change Mode")) {
                pop = !pop; // Invert the populate boolean
                mode.setText("Mode: "+(pop ? "Populate":"Eradicate"));
            } else if(e.getActionCommand().equals("Save")) {
                colony.save(_name.getText());
                // Remove the current one and put in the new one
                files.removeItem(_name.getText());
                files.addItem(_name.getText());
            } else if(e.getActionCommand().equals("Load")) {
                if(!((String)(files.getSelectedItem())).equals("Random")) colony.load((String)(files.getSelectedItem()));
                else colony = new Colony(0.5);
            }
            
            repaint(); // refresh display of colony
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
            colony.show(g); // display current state of colony
			
			// Draw box to indicate affected area
            if(dragging) {
                g.setColor(highlight);
                g.fillRect(Math.min(x*5,ex*5),Math.min(y*5,ey*5),Math.abs(ex*5-x*5),Math.abs(ey*5-y*5));
            }
        }
    }

    class Movement implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            colony.advance(); // advance to the next time step
            repaint(); // refresh 
        }
    }

    public static int main()
    {
        GUI window = new GUI();
        window.setVisible(true);
        
        return 0;
    }
}