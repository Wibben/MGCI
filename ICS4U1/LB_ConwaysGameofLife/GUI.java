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
    private int x,y;
    private boolean dragging;

    public GUI()
    {
        // Initialize components
        x = y = -1;
        dragging = false;
        BtnListener btnListener = new BtnListener(); // listener for all buttons

        simulateBtn = new JButton("Simulate");
        simulateBtn.addActionListener(btnListener);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(btnListener);
        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(btnListener);

        // Create content pane, set layout
        JPanel content = new JPanel();        // Create a content pane
        content.setLayout(new BorderLayout()); // Use BorderLayout for panel
        JPanel north = new JPanel();
        north.setLayout(new FlowLayout()); // Use FlowLayout for input area
        _name = new JTextField(10);

        DrawArea board = new DrawArea(500,500);
        board.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                x = e.getX()/5; y = (e.getY()-3)/5; // Update x and y
                colony.affect(y,x); // Flip y and x because y is the row and x is the column
                repaint(); // Refresh frame
            }
        });
        board.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if(x!=e.getX()/5 || y!=(e.getY()-3)/5) { // Update only once per cell
                    x = e.getX()/5; y = (e.getY()-3)/5; // Update x and y
                    colony.affect(y,x); // Flip y and x because y is the row and x is the column
                    repaint(); // Refresh frame
                }
            }
            public void mouseMoved(MouseEvent e) {}
        });

        // Add components to content area
        north.add(simulateBtn);
        north.add(saveBtn);
        north.add(_name);
        north.add(loadBtn);
        north.add(files);

        content.add(north,"North"); // Input area
        content.add(board,"South"); // Output area

        // Set window attributes
        setContentPane(content);
        pack();
        setTitle("Life Simulation");
        setSize(510,570);
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
        window.setVisible (true);
        
        return 0;
    }
}