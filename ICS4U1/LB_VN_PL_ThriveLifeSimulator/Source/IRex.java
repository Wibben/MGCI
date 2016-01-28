/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class IRex extends Dino { //Indominus Rex, but in the Gme it is seen as a Spinosaurus
    public IRex() {
        super(50, 1, 15, 0.01); // Very large size because invincible and eats everything
        code = Name.IREX;
        food = 250;
        carnivore = true;
    }

    public IRex(IRex i) {
        super(i);
    } //copy constructor
}
