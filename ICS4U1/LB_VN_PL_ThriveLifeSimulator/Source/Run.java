import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Run extends JFrame implements ActionListener {
    private JButton earthquake, add, repopulate, plague, options, pause, help;
    private JSpinner addDino; //to choose which dino to add
    private GUI game;

    public Run(int w, int h, int wb, int hb, int d) {
        setTitle("Thrive Life Simulator");
        game = new GUI(w, h, wb, hb, d, this); //creates GUI those specifications
        game.changeSize(w, h);
        JToolBar tb = new JToolBar();
        tb.setLayout(new FlowLayout(FlowLayout.CENTER));
        tb.setFloatable(false);
        addDino = new JSpinner(new SpinnerListModel(new String[]{
                "Ankylosaurus", "Apatosaurus", "Gallimimus", "Spinosaurus", "Stegosaurus", "Triceratops", "T-Rex", "Velociraptor"
        })); //adds all dinosaur names
        addDino.setPreferredSize(new Dimension(90, 20));

        //initialize all the buttons
        earthquake = new JButton("Earthquake");
        add = new JButton("Add Dino: ");
        repopulate = new JButton("Populate");
        plague = new JButton("Plague");
        options = new JButton("Options");
        pause = new JButton("Pause");
        help = new JButton("Help");

        //adds listeners to the button to perform actions
        earthquake.addActionListener(this);
        add.addActionListener(this);
        repopulate.addActionListener(this);
        plague.addActionListener(this);
        options.addActionListener(this);
        pause.addActionListener(this);
        help.addActionListener(this);

        //set tool tip texts
        earthquake.setToolTipText("Cause an earthquake that will kill several dinosaurs.");
        add.setToolTipText("Add a dinosaur of the selected species in a random location.");
        repopulate.setToolTipText("Introduce dinosaurs into the ecosystems randomly.");
        plague.setToolTipText("Start a plague among the dinosaurs. Sick dinosaurs are darker than usual.");
        options.setToolTipText("Change the screen resolution.");
        pause.setToolTipText("Pause/Resume the game.");
        help.setToolTipText("See the game description and instructions.");


        //add buttons to the toolbar
        tb.add(earthquake);
        tb.add(plague);
        tb.addSeparator();
        tb.add(repopulate);
        tb.add(add);
        tb.add(addDino);
        tb.addSeparator();
        tb.add(pause);
        tb.add(options);
        tb.add(help);

        //adds game gui to screen
        add(game);
        add(tb, "South");

        //sets frame properties
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(w, h);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //starts game
        game.start();
    }

    public void clickButton(int btn)
    {
        if (btn == 0) earthquake.doClick();
        else if (btn == 1) plague.doClick();
        else if (btn == 2) repopulate.doClick();
        else if (btn == 3) add.doClick();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == earthquake) { //causes earthquake
            game.getGame().earthquake(false);
        } else if (e.getSource() == plague) { //causes plague
            game.getGame().plague();
        } else if (e.getSource() == repopulate) { //repopulates the area
            game.getGame().repopulate(false);
        } else if (e.getSource() == add) { //adds the requested dinosaur
            game.getGame().addDino((String) addDino.getValue());
        } else if (e.getSource() == options) { //shows options menu for the user to change resolution
            game.stop();
            new Options(game, this);
            repaint();
        } else if (e.getActionCommand().equals("Pause")) { //pauses
            game.stop();
            pause.setText("Resume");
        } else if (e.getActionCommand().equals("Resume")) { //plays
            game.start();
            pause.setText("Pause");
        } else if (e.getSource() == help) { //help shows instruction
            game.stop();
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
            game.start(); //starts the game
        }
    }
}
