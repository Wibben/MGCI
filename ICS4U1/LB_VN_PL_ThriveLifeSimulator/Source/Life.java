/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */

abstract class Life { //All dinosaurs and trees are based off this
    protected double x, z; //position
    protected Name code; //name

    public Life() {
    }

    public void setCoord(double x, double z) {
        this.x = x;
        this.z = z;
    }
}


