/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class Velociraptor extends Dino
{
    public Velociraptor() //calls super constructor
    {
        super(1,2,15,0.2);
        code = Name.VELOCIRAPTOR;
        food = 250;
        carnivore = true;
    }
    
    public Velociraptor(Velociraptor v)
    {
        super(v);
    } //copy constructor
}
