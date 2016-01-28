import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * <p>
 * NOTICE:
 * Many seemingly pointless try catcheswere implemented within this class
 * due to errors occuring when the user earthquakes in the middle of a loop,
 * causing the size to suddenly shrink and ArrayIndexOutOfBounds errors to occur
 */
public class Game {
    private int time;
    private Controller controls;
    private Level level;
    private ArrayList<Life> life = new ArrayList<Life>(0);
    private int max, minX, maxX, minZ, maxZ;
    private Run run;

    public Game(int min, int max, int minx, int maxx, int minZ, int maxZ, Run run) {
        //Initializes variables
        this.run = run;
        this.max = max;
        this.minX = minx;
        this.maxX = maxx;
        this.minZ = minZ;
        this.maxZ = maxZ;
        int rand = (int) (Math.random() * (max - min + 1) + min);

        // Create a random number of creatures in the range [min,max]
        for (int i = 0; i < rand; i++) {
            life.add(randomLife());
            // Start the creature off in a random x and z coordinates within the range
            int randx = (int) (Math.random() * (maxx - minx + 1) + minx);
            int randy = (int) (Math.random() * (maxZ - minZ + 1) + minZ);
            life.get(i).setCoord(randx, randy);
        }
        //create level and controller for GUi
        level = new Level(maxX, maxZ, life);
        controls = new Controller();
    }

    private Life randomLife() { //randomly distributes trees and dinos
        int rand = (int) (Math.random() * 100);

        if (rand < 40) { // 40% chacneto create a herbivore
            rand = (int) (Math.random() * 100);
            if (rand < 20) return new Ankylosaurus();
            else if (rand < 40) return new Apatosaurus();
            else if (rand < 60) return new Gallimimus();
            else if (rand < 80) return new Stegosaurus();
            else return new Triceratops();
        } else if (rand < 60) {
            rand = (int) (Math.random() * 100);
            if (rand < 50) return new Velociraptor();
            else if (rand < 90) return new TRex();
            else return new IRex(); // Make the chance of an IRex occuring 2% (20%*10%)
        } else return new Tree(); // 40% chance of making a tree
    }

    public void advance(int time, boolean autoControl) { //progresses game
        boolean herb, carn;
        herb = carn = false; // These booleans are here to ensure that a food chain still exists, if one type dies off, we will need to spawn more

        // Get all the dinosaurs to move
        for (int i = 0; i < life.size(); i++) {
            try {
                if (life.get(i).code != Name.TREE) {
                    ((Dino) life.get(i)).move(life, minX, maxX, minZ, maxZ, time);
                    if (((Dino) life.get(i)).carnivore) carn = true;
                    else herb = true;
                }
            } catch (Exception e) {
            }
        }

        if (time % 25 == 0) {
            ArrayList<Life> temp = new ArrayList<Life>(0);
            
            if(autoControl && (!carn || ! herb)) { // If carnivores or herbivores are all gone a clear imbalance will occur
                repopulate(true);
            }

            // Trigger interactions if possible
            for (int i = 0; i < life.size(); i++) {
                try {
                    if (life.get(i).code != Name.TREE)
                        temp.add(((Dino) life.get(i)).interact(life, minX, maxX, minZ, maxZ));
                    else temp.add(life.get(i));
                    // Remove if the lifeform is dead
                    if (temp.get(temp.size() - 1).code != Name.TREE && !((Dino) temp.get(temp.size() - 1)).isAlive()) {
                        Render3D.deadX.add(temp.get(temp.size() - 1).x);
                        Render3D.deadZ.add(temp.get(temp.size() - 1).z);
                        Render3D.deadCount.add(0); //allows gravestone to appear
                        temp.remove(temp.size() - 1);
                    }
                } catch (Exception e) {
                }
            }
            life = temp; // Set life to temp
            
            // Population control, if population>max*1.5, call earthquake in in random places to destroy some dinos
            while(autoControl && life.size() > (int) (max * 1.5)) {
                //System.out.print("A earthquake is coming!\n");
                earthquake(true);
            }
        }
    }

    public void repopulate(boolean autoControl) // Adds more dinos when numbers are wearing thin
    {
        if (life.size() >= max) {
            JOptionPane.showMessageDialog(null, "Too many dinosaurs! Use a earthquake or plague to clear some out!");
        } else {
            // Make sure you can actually add some dinos
            while(autoControl && life.size() >= max) {
                earthquake(true);
            }
            
            // Fills it up to max life
            for (int i = life.size(); i < max; i++) {
                try {
                    Life l = randomLife();
                    if (l.code != Name.TREE) life.add(l);
                    else i--; // Undo increment if nothing actually added
                    // Start the creature off in a random x and z coordinates within the range
                    int randx = (int) (Math.random() * (maxX - minX + 1) + minX);
                    int randy = (int) (Math.random() * (maxZ - minZ + 1) + minZ);
                    life.get(i).setCoord(randx, randy);
                } catch (Exception e) {
                }
            }
        }
    }

    public void addDino(String dino) {
        if (life.size() == max) {
            JOptionPane.showMessageDialog(null, "Too many dinos! Use a earthquake or plague to clear some out!");
        } else {
            // Figure out which dino it is and make it
            Life d;
            if (dino.equals("Ankylosaurus")) d = new Ankylosaurus();
            else if (dino.equals("Apatosaurus")) d = new Apatosaurus();
            else if (dino.equals("Gallimimus")) d = new Gallimimus();
            else if (dino.equals("Stegosaurus")) d = new Stegosaurus();
            else if (dino.equals("Triceratops")) d = new Triceratops();
            else if (dino.equals("Velociraptor")) d = new Velociraptor();
            else if (dino.equals("T-Rex")) d = new TRex();
            else if (dino.equals("Spinosaurus")) d = new IRex();
            else d = new Tree();

            //puts dino in random location
            int randx = (int) (Math.random() * (maxX - minX + 1) + minX);
            int randz = (int) (Math.random() * (maxZ - minZ + 1) + minZ);
            d.setCoord(randx, randz);

            // Make sure the user seleced a valid option
            if (d.code != Name.TREE) life.add(d);
            else JOptionPane.showMessageDialog(null, "Dinosaur does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void earthquake(int x, int z, int rad) // Destroys everything in the radius centred at x,z
    {
        ArrayList<Life> temp = new ArrayList<Life>(0);

        for (int i = 0; i < life.size(); i++) {
            Life d = life.get(i);
            int dist = (int) Math.sqrt(Math.pow(x - d.x, 2) + Math.pow(z - d.z, 2));

            if (!(d.code != Name.TREE) || dist > rad) temp.add(d); // Add to temp if outside radius
        }
        //JOptionPane.showMessageDialog(null, life.size() - temp.size() + " dino(s) died from the earthquake"); //DELETE
        life = temp; // Set life to temp
    }

    public void earthquake(boolean autoControl) {
        if(!autoControl) controls.earthquake(); //visuals for earthqake, only appear if user induced
        int randx = (int) (Math.random() * (maxX - minX + 1) + minX);
        int randz = (int) (Math.random() * (maxZ - minZ + 1) + minZ);
        earthquake(randx, randz, (int) ((maxX - minX + maxZ - minZ) / 5.0)); // Make the radius big enough to affect some but small enough to not kill off everything
    }

    public void plague() // Gives a random healthy dino disease, if all dinos have disease, does nothing
    {
        boolean proceed = false;
        for (int i = 0; i < life.size(); i++)
            if (life.get(i).code != Name.TREE && !((Dino) life.get(i)).disease) proceed = true;

        if (proceed) { // Only try to assign a disease if there is a healthy dino
            int rand = (int) (Math.random() * life.size());
            while (life.get(rand).code == Name.TREE || ((Dino) life.get(rand)).disease) { // Make sure you infect a healthy dino
                rand = (int) (Math.random() * life.size());
            }
            ((Dino) life.get(rand)).infect(); // Infect the dino
        }
    }

    public void tick(boolean[] key, boolean autoControl) {
        time++;
        advance(time,autoControl);

        boolean forward = key[KeyEvent.VK_W]; //when pressed true, when released falase
        boolean back = key[KeyEvent.VK_S];
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];
        boolean turnLeft = key[KeyEvent.VK_LEFT];
        boolean turnRight = key[KeyEvent.VK_RIGHT];
        boolean jump = key[KeyEvent.VK_UP];
        boolean down = key[KeyEvent.VK_DOWN];
        boolean speed = key[KeyEvent.VK_SHIFT];

        //checking for keyboard input for buttons:
        if (key[KeyEvent.VK_1]) run.clickButton(0);
        if (key[KeyEvent.VK_2]) run.clickButton(1);
        if (key[KeyEvent.VK_3]) run.clickButton(2);
        if (key[KeyEvent.VK_4]) run.clickButton(3);

        controls.tick(forward, back, left, right, turnLeft, turnRight, jump, down, speed);
    }

    //Getter and Setter methods
    public Controller getController() { return controls; }
    public Level getLevel() { return level; }
    public ArrayList<Life> getLife() { return life; }
}
