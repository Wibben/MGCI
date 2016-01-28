/**
 * Created by Nicholas on 2016-01-23.
 */

public class Sprite { //stores sprite information
    private double x, y, z;

    public Sprite(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //getter methods
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
}
