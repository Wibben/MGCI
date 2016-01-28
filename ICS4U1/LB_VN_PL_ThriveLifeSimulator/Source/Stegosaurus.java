/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Stegosaurus extends Dino
{
    public Stegosaurus()
    {
        super(5,1,10,0.2); //uses dino contrsuctor
        code = Name.STEGOSAURUS;
		food = 200;
		carnivore = false;
    }
    
    public Stegosaurus(Stegosaurus s)
    {
        super(s);
    } //copy constructor
}
