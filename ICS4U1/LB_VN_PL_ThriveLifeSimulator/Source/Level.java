import java.util.ArrayList;
import java.util.Random;

/* Bing Li, Nicholas Vadivelu, Lawrence Pang
 * Thrive
 * ICS4U1
 */
public class Level {
    private Block[] blocks;
    private final int width, height;
    private ArrayList<Life> life;

    public Level(int w, int h, ArrayList<Life> life) {
        //Initializes variables
        width = w + 1;
        height = h + 1;
        blocks = new Block[width * height];
        this.life = life;
        for (int i = 0; i < height * width; i++) {
            blocks[i] = new Block();
        }
        //Converts Game data into Level information
        for (int i = 0; i < life.size(); i++) {
            if (life.get(i) instanceof Dino) {
                blocks[0].addSprite(new Sprite((int) life.get(i).x, 0, (int) life.get(i).z)); //dinosaurs become sprites
            } else if (life.get(i) instanceof Tree) {
                blocks[(int) life.get(i).x + (int) life.get(i).z * width] = new SolidBlock(); //trees are solid
            }
        }
    }

    public Block create(int x, int y) { //allows Render3D to draw blocks
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return new Block();
        }
        return blocks[x + y * width];
    }
}
