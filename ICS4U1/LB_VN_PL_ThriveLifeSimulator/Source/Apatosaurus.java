/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Apatosaurus extends Dino {

    public Apatosaurus() {
        super(16, 1, 10, 0.1);//calls dino constructor
        code = Name.APATOSAURUS;
        food = 200;
        carnivore = false;
    }

    public Apatosaurus(Apatosaurus a) {
        super(a);
    } //copy constructor
}
