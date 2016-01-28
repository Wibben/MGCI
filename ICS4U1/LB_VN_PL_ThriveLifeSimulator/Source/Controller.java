/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */
public class Controller { //This class converst he play's input from Input Controller into actual game movement
    private double x, y, z, rotation, xa, za, rotationa = 0.001; //rotationa is set to 0.001 to rotate the player
    //slightly at the beginning so that Textures render fully

    //The below data fields are for an earthquake event
    private boolean earth = false;
    private int earthTime;

    //Getter methods
    public double getX() { return x; }
    public double getZ() { return z; }
    public double getY() { return y; }
    public double getRotation() {return rotation;}

    //if an earthquake happens, this method will be called
    public void earthquake() {
        earth = true;
        earthTime = 0;
    }

    //gets called every time the game advances
    public void tick(boolean forward,
                     boolean back,
                     boolean left,
                     boolean right,
                     boolean turnLeft,
                     boolean turnRight,
                     boolean jump,
                     boolean down,
                     boolean speed)
    {
        double multiplier = speed ? 3 : 1; //allows for speed up

        //Below variables control the limits of the user's movement
        double rotationSpeed = 0.025 * multiplier;
        double walkSpeed = 1 * multiplier;
        double jumpHeight = 0.5 * multiplier;
        double xMove = 0;
        double zMove = 0;

        //below statements move the character based on input
        if (forward) zMove++;
        if (back) zMove--;
        if (left) xMove--;
        if (right) xMove++;
        if (turnLeft) rotationa -= rotationSpeed;
        if (turnRight) rotationa += rotationSpeed;
        if (jump && y < 15 && !earth) y += jumpHeight;
        if (down && y > -Render3D.floorPosition + 2 && !earth) y -= jumpHeight;

        //Calculates the z and x rotation
        xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
        za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
        x += xa;
        //z *= 0.9; //maximum height
        z += za;
        xa *= 0.1;
        za *= 0.1;
        rotation += rotationa;
        rotationa *= 0.5;

        //during earthquake, move character rapidly up and down
        if (earth) {
            y = 0;
            if (earthTime % 3 == 0) { //moves up sometimes, moves down sometimes
                y += 4;
            } else if (earthTime % 4 == 1) {
                y -= 4;
            }
            earthTime++;
            if (earthTime > 200) { //at the end of the earthquake, reset earthquake variables
                earth = false;
                earthTime = 0;
                y = 0;
            }
        }
    }
}
