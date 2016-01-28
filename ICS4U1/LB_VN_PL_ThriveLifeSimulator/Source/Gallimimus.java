/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Gallimimus extends Dino
{
    public Gallimimus()
    {
		super(1,2,15,0.4); //calls Dino constructor
		code = Name.GALLIMIMUS;
		food = 200;
		carnivore = false;
    }
    
    public Gallimimus(Gallimimus g)
    {
        super(g);
    } //copy constructor
}
