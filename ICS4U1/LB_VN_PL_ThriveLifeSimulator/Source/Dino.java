/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */

import java.util.ArrayList;

abstract class Dino extends Life { //stores dinosaur life form
    protected int size, spd, rng, turns;
    protected Age age;
    protected double reprod; // Reproduction chance
    protected int food;
    protected boolean alive, carnivore, disease;
    protected int diseaseCnt;
    private double closex, closey;
    private double randomX, randomZ; // Used to store how dinos move randomly

    public Dino(int size, int speed, int range, double reproduce) {
        this.size = size;
        spd = speed;
        rng = range;
        reprod = reproduce / 2.0;
        alive = true;
        disease = false;
        setCoord(0, 0);

        double rand = Math.random();
        if (rand < 0.25) age = Age.BABY;
        else if (rand < 0.5) age = Age.JUVENILE;
        else age = Age.ADULT;

        turns = 0; // Every 5+5*age.getValue() turns you go to the next age
        diseaseCnt = 300; // You can spend 30 turns before dying of disease
    }

    // Copy constructor, copies the value of everything
    public Dino(Dino d) {
        code = d.code;
        size = d.size;
        spd = d.spd;
        rng = d.rng;
        reprod = d.reprod;
        food = d.food;
        alive = d.alive;
        x = d.x;
        z = d.z;
        closex = d.closex;
        closey = d.closey;
        randomX = d.randomX;
        randomZ = d.randomZ;
        age = d.age;
        carnivore = d.carnivore;
        turns = d.turns;
        disease = d.disease;
        diseaseCnt = d.diseaseCnt;
    }

    private void findClose(ArrayList<Life> life) {
        for (int i = 0; i < life.size() && age != Age.BABY; i++) {
            Life d = life.get(i);
            // Distance is determined by pythagorean theorem
            int dist = (int) Math.sqrt(Math.pow(d.x - x, 2) + Math.pow(d.z - z, 2));
            int temp = (int) Math.sqrt(Math.pow(closex - x, 2) + Math.pow(closey - z, 2));
            // Make sure it's within range and not interacting with itself
            if ((carnivore && d != this && d.code != Name.TREE && d.code != code && food < 200 && dist <= rng) ||
                    (!carnivore && d.code == Name.TREE && food < 160 && dist <= rng)) {
                if (dist < temp) {
                    closex = d.x;
                    closey = d.z;
                }
            }
        }
    }

    public void move(ArrayList<Life> life, int minx, int maxx, int miny, int maxy, int time) {
        if (time % 25 == 0) {
            closex = closey = rng * rng; // Definitely outside of range, designed to be overwritten
            findClose(life); // Find closex and closey

            // Subtract from food counter and add to turns counter
            if (age != Age.BABY) food--;
            turns++;

            if (disease && age != Age.BABY) diseaseCnt--; // Count down diseaseCnt if dino has disease

            // Determining random movement for x coord
            double randDir;
            double prevrandomx = randomX;
            double prevrandomy = randomZ;
            randomX = -1;
            randomZ = -1;

            randDir = Math.random();
            if (randDir < 0.33 && x + 1 <= maxx) randomX = 0.04;
            else if (randDir > 0.66 && x - 1 >= minx) randomX = -0.04;
            else randomX = 0;
            if (Math.random() < 0.5) randomX = prevrandomx; // Dinos are more inclined to go in the original direction
            if (x + randomX > maxx || x + randomX < minx)
                randomX *= -1; // Make dino go in opposite direction if meet a wall

            // Determining random movement for z coord
            randDir = Math.random();
            if (randDir < 0.33 && z + 1 <= maxy) randomZ = 0.04;
            else if (randDir > 0.66 && z - 1 >= miny) randomZ = -0.04;
            else randomZ = 0;

            if (Math.random() < 0.5) randomZ = prevrandomy; // Dinos are more inclined to go in the original direction
            if (z + randomZ > maxy || z + randomZ < miny)
                randomZ *= -1; // Make dino go in opposite direction if meet a wall
        }

        double dx, dy;
        dx = closex - x;
        dy = closey - z;

        // If it's a baby it cannot move, otherwise it will determine the best option for movement
        for (int i = spd; i > 0 && age != Age.BABY; i--) {
            if (closex == rng * rng || (dx == 0 && dy == 0)) { // Move randomly
                x += randomX;
                z += randomZ;
            } else if (Math.abs(dx) >= Math.abs(dy) && x + dx / Math.abs(dx) >= minx && x + dx / Math.abs(dx) <= maxx) {
                x += (dx / Math.abs(dx)) / 25.0;
            } else if (z + dy / Math.abs(dy) >= miny && z + dy / Math.abs(dy) <= maxy) {
                z += (dy / Math.abs(dy)) / 25.0;
            } else { // Move randomly
                x += randomX;
                z += randomZ;
            }
        }
        // Make sure dinos stay in bounds
        if (x > maxx) x = maxx;
        else if (x < minx) x = minx;
        if (z > maxy) z = maxy;
        else if (z < miny) z = miny;
        //System.out.println("Dinosaur " + this + ": " + x + ", " + z);

        // Check if it is time to go to the next age, if yes, do so and reset turns to 0
        if (turns >= 25 + age.getValue() * 25) {
            age = age.next();
            turns = 0;
        }
        if (age == age.OLD && turns >= 25 + age.getValue() * 25) alive = false; // Kills off dino if it gets too old
    }

    public Life interact(ArrayList<Life> life, int minx, int maxx, int miny, int maxy) {
        // Survival chance is calculated using the dino's age, size, and speed
        double survival = 1;
        // Make a copy of the current dino
        Dino cur = makeCopy(this);

        for (int i = 0; i < life.size(); i++) { // Go through the arraylist
            Life d = life.get(i);
            int dist = (int) Math.sqrt(Math.pow(x - d.x, 2) + Math.pow(z - d.z, 2)); // Get the distance between the two
            if (d.code != Name.TREE && d != this && dist <= 1) { // Make sure it's a dino and not itself
                if (((Dino) d).code == code && dist <= 1) { // Same dino -> reproduction, make sure they are not baby or juvenile
                    if (((Dino) d).age.getValue() > Age.JUVENILE.getValue() && age.getValue() > Age.JUVENILE.getValue() && Math.random() < reprod)
                        reproduction((int) x, (int) z, life, minx, maxx, miny, maxy);
                } else if ((carnivore || ((Dino) d).carnivore) && ((Dino) d).age != Age.BABY) { // May die if one fo them is a carnivore
                    survival = (1.0 * size * spd * age.ageMulti()) / (2.0 * ((Dino) d).size * ((Dino) d).spd * ((Dino) d).age.ageMulti());
                    if (Math.random() > survival) { // If creature dies all food levels of nearby dinos are replenished
                        cur.alive = false;
                        feed(life);
                    }
                }
                if (((Dino) d).disease && Math.random() < 0.3) infect(); // 30% chance to spread disease
            }
            if (!carnivore && d.code == Name.TREE && dist <= 1) food = 200; // Max out food
        }

        if (food <= 0 || diseaseCnt <= 0) cur.alive = false;

        return cur;
    }

    private Dino makeCopy(Dino d) {
        // Figure out which dino to return
        if (d.code == Name.ANKYLOSAURUS) return new Ankylosaurus((Ankylosaurus) d);
        else if (d.code == Name.APATOSAURUS) return new Apatosaurus((Apatosaurus) d);
        else if (d.code == Name.GALLIMIMUS) return new Gallimimus((Gallimimus) d);
        else if (d.code == Name.STEGOSAURUS) return new Stegosaurus((Stegosaurus) d);
        else if (d.code == Name.TRICERATOPS) return new Triceratops((Triceratops) d);
        else if (d.code == Name.VELOCIRAPTOR) return new Velociraptor((Velociraptor) d);
        else if (d.code == Name.TREX) return new TRex((TRex) d);
        else return new IRex((IRex) d);
    }

    private void reproduction(int x, int y, ArrayList<Life> life, int minx, int maxx, int miny, int maxy) // Make another dino that is the same and set age to baby
    {
        // Make a dino
        Dino d = makeCopy(this);
        d.age = Age.BABY;
        d.setCoord(x, y);
        d.turns = 0; // Set the turns the creature has taken to
        diseaseCnt = 30; // Reset diseaseCnt for new dinos
        if (Math.random() < 0.8) d.disease = false; // 80% chance to not get the disease if original dino has disease

        // Add to life
        life.add(d);
    }

    public void feed(ArrayList<Life> life) {
        for (int i = 0; i < life.size(); i++) { // Go through the life array
            Life d = life.get(i);
            int dist = (int) Math.sqrt(Math.pow(d.x - x, 2) + Math.pow(d.z - z, 2));
            // Feed the carnivorous dinos around it
            if (d.code != Name.TREE && ((Dino) d).carnivore && dist <= 1) ((Dino) life.get(i)).food = 250;
        }
    }

    //Getter methods
    public void infect() {
        disease = true;
    }

    public Age getAge() {
        return age;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isSick() {
        return disease;
    }
}
