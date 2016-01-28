import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */
public class Options extends JFrame implements ActionListener {
    private JPanel window; //content pane
    private int width = 265, height = 165; //dimensions of frame
    private JButton enter, cancel;
    private JTextField inHeight, inWidth; //textfields to enter preferences
    private Configuration config; //configuration to record to XML
    private boolean ifStarted; //check if the game has started
    private GUI game; //game reference to pause/play
    private Run run; //run reference to reformat
    private JCheckBox popControl; //for auto population control

    private void addComponents() {
        window.setLayout(null);

        //Declare and/or initialize necessary
        inHeight = new JTextField(config.loadConfiguration("height"));
        inHeight = new JTextField(config.loadConfiguration("height"));
        inWidth = new JTextField(config.loadConfiguration("width"));
        enter = new JButton("Enter");
        cancel = new JButton("Cancel");
        JLabel label = new JLabel("Set Screen Size (pixels):");
        JLabel h = new JLabel("Height: ");
        JLabel w = new JLabel("Width: ");

        //setting population control up
        try {
            boolean selected = (config.loadConfiguration("population control")).equals("1");
            popControl = new JCheckBox("Automatic Population Control", selected);
        } catch(NullPointerException e)
        {
            config.saveConfiguration("population control", 0);
            popControl = new JCheckBox("Automatic Population Control");
        } catch (Exception e)
        {
        }

        //Set the locations of all the components
        popControl.setBounds(16, 230 - 165, 250, 20);
        enter.setBounds(290 - 215, 230 - 140, 80, 30);
        cancel.setBounds(380 - 215, 230 - 140, 80, 30);
        label.setBounds(20, 15, 150, 15);
        w.setBounds(40, 36, 70, 25);
        inWidth.setBounds(80, 39, 50, 20);
        h.setBounds(150, 36, 100, 25);
        inHeight.setBounds(193, 39, 50, 20);

        //add action listeners so that buttons do something
        enter.addActionListener(this);
        cancel.addActionListener(this);
        popControl.addActionListener(this);

        //set tool tip texts
        enter.setToolTipText("Save the requested settings");
        cancel.setToolTipText("Don't change the settings and return");
        inWidth.setToolTipText("Enter the requested screen width here in pixels.");
        inHeight.setToolTipText("Enter the requested screen height here in pixels");
        popControl.setToolTipText("If this is checked, the program will automatically keep the population size stable.");

        //add components to the screen
        window.add(enter);
        window.add(cancel);
        window.add(inHeight);
        window.add(inWidth);
        window.add(h);
        window.add(label);
        window.add(w);
        window.add(popControl);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) { //enter button will get the requested dimensions
            int w, h;
            try {
                w = Integer.parseInt(inWidth.getText());
                h = Integer.parseInt(inHeight.getText());
            } catch (NullPointerException ex) { //if it's empty it will prompt user and not accept values
                JOptionPane.showMessageDialog(null, "Please enter values!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (h < 100) { //will prevent invalid values
                JOptionPane.showMessageDialog(null, "Invalid values! Height must be 100 or greater.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (w < 520){
                JOptionPane.showMessageDialog(null, "Invalid values! Width must be 520 or greater.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //if everything is ok, save the settings
            config.saveConfiguration("width", w);
            config.saveConfiguration("height", h);
            if (ifStarted) { //change the size of the elements in the game if the game has already started
                game.changeSize(w, h);
                run.pack();
                run.doLayout();
                game.repaint();
                run.repaint();
                game.start(); //unpauses game
            }
            this.dispose(); //closes window
        } else if (e.getSource() == cancel) {
            this.dispose();
            if (ifStarted)
                game.start();
        } else if (e.getSource() == popControl)
        {
            boolean selected = popControl.isSelected();
            game.setAutoControl(selected);
            config.saveConfiguration("population control", (selected ? 1 : 0));
        }
    }

    public Options() {
        //Initializes variables
        config = new Configuration();
        ifStarted = false;
        window = new JPanel();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //makes it look like windows
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sets up JfRAME
        setTitle("Options");
        setSize(new Dimension(width, height));
        setContentPane(window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //adds components
        addComponents();
    }

    public Options(GUI g, Run r) {
        g.stop(); //stops game while options menu is up

        //Initializes elements
        config = new Configuration();
        ifStarted = true;
        game = g;
        run = r;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //makes it look like windows
        } catch (Exception e) {
            e.printStackTrace();
        }

        //sets up components on the frame
        setTitle("Options");
        setSize(new Dimension(width, height));
        window = new JPanel();
        setContentPane(window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addComponents();
    }
}
