import jdk.nashorn.internal.scripts.JO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Nicholas on 2016-01-26.
 */
public class Launcher extends JFrame implements ActionListener {
    private JPanel window; //content pane
    private JButton start, options, help, quit;
    private Options op; //options menu
    private Configuration config; //configuration to save XML

    private int width = 480, height = 300; //window size
    private int bWidth = 80, bHeight = 30; //button size

    private void addComponents() {
        //Initialize Buttons
        start = new JButton("Start");
        options = new JButton("Options");
        help = new JButton("Help");
        quit = new JButton("Quit");

        //set button size
        int buttonY = 60;
        start.setBounds(width - bWidth - 20, buttonY, bWidth, bHeight);
        options.setBounds(width - bWidth - 20, buttonY += 40, bWidth, bHeight);
        help.setBounds(width - bWidth - 20, buttonY += 40, bWidth, bHeight);
        quit.setBounds(width - bWidth - 20, buttonY += 40, bWidth, bHeight);

        //Add action listeners so the buttons do something
        start.addActionListener(this);
        options.addActionListener(this);
        help.addActionListener(this);
        quit.addActionListener(this);

        //add tooltiptexts
        start.setToolTipText("Start the Simulator");
        options.setToolTipText("Set a custom resolution");
        help.setToolTipText("See the game description and instructions");
        quit.setToolTipText("Close the game");

        //add buttons to window
        window.add(start);
        window.add(options);
        window.add(help);
        window.add(quit);

        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) { //if they choose to start the game, load their requested settings and open up start
            try {
                new StartGame(Integer.parseInt(config.loadConfiguration("width")), Integer.parseInt(config.loadConfiguration("height")), this, config);
            } catch (Exception ex)
            {
                new StartGame(800, 600, this, config);
            }
            try {
                op.dispose();
            } catch (NullPointerException ex) {
            }
        } else if (e.getSource() == options) { //opens options menu
            op = new Options();
        } else if (e.getSource() == help) {
            JOptionPane.showMessageDialog(null, "<html>" +
                    "<p>Thrive was designed to simulate the lives and interactions of dinosaurs! You can<br>" +
                       "move around in 3D space to observe their actions. Each dinosaur starts out an egg,<br>" +
                       "then hatches into a juvenile, then matures into an adult before finally dying. A <br>" +
                       "dinosaur can be a carnivore or herbivore; Carnivores eat dinosaurs that are smaller<br>" +
                        "and less aggressive than they are, whereas herbivores live off the square trees.</p>" +
                    "<br>" +
                    "<p>Controls: </p>" +
                    "<table border=\"1\">" +
                        "<tr>" +
                            "<td> Key </td>" +
                            "<td> W </td>" +
                            "<td> A </td>" +
                            "<td> S </td>" +
                            "<td> D </td>" +
                            "<td> Up </td>" +
                            "<td> Down </td>" +
                            "<td> Left </td>" +
                            "<td> Right </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Function </td>" +
                            "<td> Forward </td>" +
                            "<td> Backward </td>" +
                            "<td> Left </td>" +
                            "<td> Right </td>" +
                            "<td> Up </td>" +
                            "<td> Down </td>" +
                            "<td> Turn Left </td>" +
                            "<td> Turn Right </td>" +
                        "</tr>" +
                    "</table>" +
                    "<table border=\"1\">" +
                        "<tr>" +
                            "<td> Key </td>" +
                            "<td> Shift + Key </td>" +
                            "<td> 1 </td>" +
                            "<td> 2 </td>" +
                            "<td> 3 </td>" +
                            "<td> 4 </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Function </td>" +
                            "<td> Speed Up </td>" +
                            "<td> Earthquake </td>" +
                            "<td> Plague </td>" +
                            "<td> Populate </td>" +
                            "<td> Add Dino </td>" +
                        "</tr>" +
                    "</table>" +
                    "<p></p>" +
                    "<p>Buttons: </p>" +
                    "<table border=\"1\">" +
                        "<tr>" +
                            "<td> Button </td>" +
                            "<td> Function </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Earthquake </td>" +
                            "<td> Causes a screen-shaking earthquake that kills several dinosaurs. </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Plague </td>" +
                            "<td> <p>Starts a plague that infects few dinosaurs and spread. Infected<br>Dinosaurs darker than usual.</p> </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Populate </td>" +
                            "<td> Introduces several dinosaurs to the ecosystem. </td>" +
                        "</tr>" +
                        "<tr>" +
                            "<td> Add Dino </td>" +
                            "<td> <p>Adds a dinosaur of the species selected on the Spinner to the<br>ecosystem at a random location </p></td>" +
                        "</tr>" +
                    "</table>" +
                    " </html>", "Thrive Description and Controls", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == quit) {
            System.exit(0); //exits the game
        }
    }

    public Launcher() {
        //Initialize variables
        config = new Configuration();
        window = new ImagePanel("images/bg.jpg");//creates a custom panel with bakground
        window.setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //makes the buttons look and feel like windows
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set up the JFrame
        setTitle("Thrive Life Simulator Launcher");
        setSize(new Dimension(width, height));
        setContentPane(window);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //Adds elements to screen
        addComponents();
    }

    //Custom JPanel that has an image background
    private class ImagePanel extends JPanel {
        private Image img;

        public ImagePanel(String s) {
            try {
                img = ImageIO.read(Launcher.class.getResource(s)); // load file into Image object
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ImagePanel file not found");
            }
        }

        protected void paintComponent(Graphics g) { //pains background
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
