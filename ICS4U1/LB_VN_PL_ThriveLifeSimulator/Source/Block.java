import java.util.ArrayList;
import java.util.List;

/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */
public class Block {

    protected boolean solid = false; //does it exist/ is it solid
    public final static Block solidWall = new SolidBlock(); //this is a subclass of Block that generates a tre
    private List<Sprite> sprites = new ArrayList<Sprite>(); //one block can have many sprites on it.

    public void addSprite(Sprite sprite) { //adds a sprite to the Block
        sprites.add(sprite);
    }

    public Sprite getSprite (int i) {
        return sprites.get(i);
    }
    public void setSprites (List<Sprite> s) { sprites = s;}
}
