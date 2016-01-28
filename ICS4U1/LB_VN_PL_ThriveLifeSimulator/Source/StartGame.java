import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class StartGame extends JFrame implements ActionListener {
    private JPanel window; //content pane
    private JButton enter, cancel;
    private JFrame frame;
    private JTextField bWidth, bHeight, numDino;
    private Configuration config; //configuration for xml files

    private int w, h; //used for game that will be created
    int width = 270, height = 170; //this window's dimensions

    public void drawButtons() {
        //initialize variables
        enter = new JButton("Start");
        cancel = new JButton("Cancel");
        bWidth = new JTextField(config.loadConfiguration("forest width"));
        bHeight = new JTextField(config.loadConfiguration("forest length"));
        numDino = new JTextField(config.loadConfiguration("number of dinosaurs"));
        JLabel label = new JLabel("Enter Forest Dimensions (blocks):");
        JLabel h = new JLabel("Length: ");
        JLabel w = new JLabel("Width: ");
        JLabel d = new JLabel("Number of Dinosaurs:");

        //add listeners so they do things
        enter.addActionListener(this);
        cancel.addActionListener(this);

        //set lcations
        enter.setBounds(290 - 215, 95, 80, 30);
        cancel.setBounds(380 - 215, 95, 80, 30);
        label.setBounds(20, 15, 200, 15);
        w.setBounds(40, 36, 70, 25);
        bWidth.setBounds(80, 39, 50, 20);
        h.setBounds(150, 36, 100, 25);
        bHeight.setBounds(193, 39, 50, 20);
        numDino.setBounds(151, 36 + 32, 93, 20);
        d.setBounds(40, 34 + 33, 110, 20);

        //add components to window
        window.add(enter);
        window.add(cancel);
        window.add(bHeight);
        window.add(bWidth);
        window.add(h);
        window.add(label);
        window.add(w);
        window.add(d);
        window.add(numDino);
        repaint();
    }

    //starts the startup menu
    public StartGame(int w, int h, JFrame f, Configuration c) {
        //initialize variables
        this.w = w;
        this.h = h;
        frame = f;
        config = c;
        window = new JPanel();
        window.setLayout(null);

        //make it look like windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set frame properties
        setTitle("Thrive Life Simulator");
        setSize(new Dimension(width, height));
        setContentPane(window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        drawButtons();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            int w2, h2, d;
            try {
                w2 = Integer.parseInt(bWidth.getText());
                h2 = Integer.parseInt(bHeight.getText());
                d = Integer.parseInt(numDino.getText()); //gets the properties from the textifleds
            } catch (NullPointerException ex) { //displays error if blank
                JOptionPane.showMessageDialog(null, "Please enter values!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (w2 < 1 || h2 < 1 || d < 1) {//checks bounds
                JOptionPane.showMessageDialog(null, "Invalid values! Values must be 1 or greater.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //if ok, it stores these in the xml
            config.saveConfiguration("forest width", w2);
            config.saveConfiguration("forest length", h2);
            config.saveConfiguration("number of dinosaurs", d);

            //closes the frames after initialization of game is complete
            frame.dispose();
            this.dispose();

            //runs game
            new Run(w, h, w2, h2, d);
        } else if (e.getSource() == cancel) {
            this.dispose(); //if they cancel, go back to start screen
        }
    }

    public static void main(String[] args) {
        new Launcher();
    }
}
