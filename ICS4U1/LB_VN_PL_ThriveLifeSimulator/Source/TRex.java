/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */

public class TRex extends Dino
{
    public TRex()
    {
		super(11,1,10,0.1); //calls dino constructor
		code = Name.TREX;
		food = 250;
		carnivore = true;
    }
    
    public TRex(TRex t)
    {
        super(t);
    } //copy constructor
}
