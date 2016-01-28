/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1
 */

public class Ankylosaurus extends Dino {

    public Ankylosaurus() {
        super(5, 1, 10, 0.2); //calls dino constructor
        code = Name.ANKYLOSAURUS;
        food = 200;
        carnivore = false;
    }

    public Ankylosaurus(Ankylosaurus a) {
        super(a);
    } //copy constructor
}
