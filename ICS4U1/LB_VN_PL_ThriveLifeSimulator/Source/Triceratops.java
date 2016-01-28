/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Triceratops extends Dino
{
    public Triceratops()
    {
		super(5,1,10,0.2); //calls dinos constructor
		code = Name.TRICERATOPS;
		food = 200;
		carnivore = false;
    }
    
    public Triceratops(Triceratops t)
    {
        super(t);
    } //copy constructor
}
