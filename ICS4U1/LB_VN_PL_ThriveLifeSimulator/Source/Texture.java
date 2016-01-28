import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;

/* Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * Thrive
 * ICS4U1
 */
public class Texture { //holds all the textures for the game
    public static int width = 256;
    public static Render ground = loadBitmap("images/textures/grass.png");
    public static Render sky = loadBitmap("images/textures/sky.png");
    public static Render trunk = loadBitmap("images/textures/trunk.png");
    public static Render leaves = loadBitmap("images/textures/leaves.png");

    //Dinosaur textures:
    public static Render anky = loadBitmap("images/textures/anky.png");
    public static Render apato = loadBitmap("images/textures/apato.png");
    public static Render galli = loadBitmap("images/textures/galli.png");
    public static Render irex = loadBitmap("images/textures/spino.png");
    public static Render stego = loadBitmap("images/textures/stego.png");
    public static Render trex = loadBitmap("images/textures/trex.png");
    public static Render trice = loadBitmap("images/textures/trice.png");
    public static Render velo = loadBitmap("images/textures/velo.png");

    public static Render ankyb = loadBitmap("images/textures/ankyb.png");
    public static Render apatob = loadBitmap("images/textures/apatob.png");
    public static Render gallib = loadBitmap("images/textures/gallib.png");
    public static Render irexb = loadBitmap("images/textures/spinob.png");
    public static Render stegob = loadBitmap("images/textures/stegob.png");
    public static Render trexb = loadBitmap("images/textures/trexb.png");
    public static Render triceb = loadBitmap("images/textures/triceb.png");
    public static Render velob = loadBitmap("images/textures/velob.png");

    public static Render egg = loadBitmap("images/textures/egg.png");
    public static Render grave = loadBitmap("images/textures/grave.png");

    public static Render[][] dinos = {
            {egg, egg, egg, egg, egg, egg, egg, egg}, //egg stage
            {ankyb, apatob, gallib, stegob, triceb, velob, trexb, irexb}, //juvenile stage
            {anky, apato, galli, stego, trice, velo, trex, irex}, //adult stage
            {anky, apato, galli, stego, trice, velo, trex, irex} //old stage
    };


    public static Render loadBitmap(String fileName) { //loads the file from class resources
        Render result = new Render(1, 1);
        try {
            BufferedImage image = ImageIO.read(Texture.class.getResource(fileName)); //loads
            result = new Render(image.getWidth(), image.getHeight()); //creates render out of images
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), result.pixels, 0, image.getWidth());
            //stores colour values of the image in the pixels array
        } catch (Exception e) { //catches exception
            JOptionPane.showMessageDialog(null, "Crashed at loadBitmap!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }
}