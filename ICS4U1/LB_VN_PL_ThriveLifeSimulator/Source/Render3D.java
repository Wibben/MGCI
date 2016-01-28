import java.util.ArrayList;

/**
 * Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * 13 January 2016
 * ICS4U1-04
 */
public class Render3D extends Render {
    //This class interprets the 3D input
    private double[] zBuffer, zBufferWall; //to judge distance and appropriately add gof
    private final double renderDistance = 10000;
    private double forward, right, cos, sin, up; //players movement
    private int shift = Texture.width - 1; //shifting the byte values for colour
    public static double floorPosition = 8.0; //starting position of the floor AKA player height
    public static double ceilingPosition = 32.0; // position of the ceiling
    public ArrayList<Life> life = new ArrayList<Life>();
    private int spriteSize;

    //The followiing Arraylists are used to display gravestones for dead dinos
    public static ArrayList<Double> deadX = new ArrayList<Double>();
    public static ArrayList<Double> deadZ = new ArrayList<Double>();
    public static ArrayList<Integer> deadCount = new ArrayList<Integer>();


    //constructor
    public Render3D(int w, int h, ArrayList<Life> l) {
        super(w, h);
        zBuffer = new double[width * height];
        zBufferWall = new double[width];
        life = l;
        spriteSize = (int)(256 * h/600.0);
    }

    //renders everything
    public void floor(Game game) {
        double correct = 0.625; //this compensates for the players movement
        life = game.getLife();
        for (int i = 0; i < width; i++) {
            zBufferWall[i] = 0; //resets the buffer
        }
        //set values for variables
        double rotation = game.getController().getRotation(); //game.getTime()/200.0; //the /xxx.0 number getController() the speed of rotation
        cos = Math.cos(rotation);
        sin = Math.sin(rotation); //these two are used to rotate the player
        forward = game.getController().getZ();
        right = game.getController().getX();
        up = game.getController().getY();

        for (int y = 0; y < height; y++) {
            double ceiling = (y - height / 2.0) / height; //calculate ceiling position
            double z = (floorPosition + up) / ceiling; //depth
            if (ceiling < 0) {
                z = (ceilingPosition - up) / -ceiling; //makes the ceiling and floor go in the same direction
            }

            for (int x = 0; x < width; x++) {
                double depth = (x - width / 2.0) / height * correct * z;//calculates how far the obejct is
                double xx = depth * cos + z * sin + right * correct;
                double yy = z * cos - depth * sin + forward * correct; //used to compensate for rotation
                int xPix = (int) (xx * Texture.width / 8);
                int yPix = (int) (yy * Texture.width / 8); //values for the x and y pixel colours
                zBuffer[x + y * width] = z; //records the distance of the pixel in 3D space

                if (y > height / 2) //renders sky and ground
                    pixels[x + y * width] = Texture.ground.pixels[(xPix & shift) + (yPix & shift) * Texture.width];
                else
                    pixels[x + y * width] = Texture.sky.pixels[(xPix & shift) + (yPix & shift) * Texture.width]; //the 8 indicates width

                renderDistanceLimiter(x + y * width); //creates fog for farther objects
            }
        }

        Level level = game.getLevel(); //gets the generated level
        int size = 500; //draws all the required trees
        for (int xBlock = -1; xBlock <= size; xBlock++) {
            for (int zBlock = -1; zBlock <= size; zBlock++) {
                Block block = level.create(xBlock, zBlock);
                Block east = level.create(xBlock + 1, zBlock);
                Block south = level.create(xBlock, zBlock + 1); //south and east are made to ensure the tree is solid from all sides
                renderTree(block, east, south, xBlock, zBlock);
            }
        }

        //Renders all the dinosaur sprites
        for (int i = 0; i < life.size(); i++) {
            if (life.get(i).code != Name.TREE) { //trees are static and already rendered
                renderSprite(life.get(i).x, 0, life.get(i).z, i);
            }
        }
        //below code displays required gravestones
        for (int i = 0; i < deadCount.size(); i++) {
            if (deadCount.get(i) < 60) {
                renderSprite(deadX.get(i), 0, deadZ.get(i), Texture.grave, -1);
                deadCount.set(i, deadCount.get(i) + 1);
            } else { //after 1 second, the gravestones are removed
                deadX.remove(i);
                deadZ.remove(i);
                deadCount.remove(i);
                i--; //since one was removed, need to decrement
            }
        }
    }

    public void renderTree(Block block, Block east, Block south, int x, int z) {
        if (block.solid) { //this code renders all sides of the tree depending on the player's angle
            if (!east.solid) {
                renderWall(x + 1, x + 1, z, z + 1, 0, Texture.trunk);
                renderWall(x + 1, x + 1, z, z + 1, 0.5, Texture.trunk);
                renderWall(x + 1, x + 1, z, z + 1, 1, Texture.leaves);
            }
            if (!south.solid) {
                renderWall(x + 1, x, z + 1, z + 1, 0, Texture.trunk);
                renderWall(x + 1, x, z + 1, z + 1, 0.5, Texture.trunk);
                renderWall(x + 1, x, z + 1, z + 1, 1, Texture.leaves);
            }
        } else {
            if (east.solid) {
                renderWall(x + 1, x + 1, z + 1, z, 0, Texture.trunk);
                renderWall(x + 1, x + 1, z + 1, z, 0.5, Texture.trunk);
                renderWall(x + 1, x + 1, z + 1, z, 1, Texture.leaves);
            }
            if (south.solid) {
                renderWall(x, x + 1, z + 1, z + 1, 0, Texture.trunk);
                renderWall(x, x + 1, z + 1, z + 1, 0.5, Texture.trunk);
                renderWall(x, x + 1, z + 1, z + 1, 1, Texture.leaves);
            }
        }
    }

    public void renderSprite(double x, double y, double z, Render texture, int i) { //renders the sprites
        double upCorrect = -0.08; //vertical movement correction
        double floorCorrect = 0.04; //corrects movement across floor

        //Calculate the sides based on rotation
        double xc = ((x / 2) - (right * floorCorrect)) * 2;
        double yc = ((y / 2) - (up * upCorrect)) + 0.25; //use this to change position of stuff
        double zc = ((z / 2) - (forward * floorCorrect)) * 2;

        //calculate player's view
        double rotX = xc * cos - zc * sin;
        double rotY = yc;
        double rotZ = zc * cos + xc * sin;

        //Calculate the position of each corner based on rotation
        double xPixel = rotX / rotZ * height + width / 2.0;
        double yPixel = rotY / rotZ * height + height / 2.0;

        int size = spriteSize; //this is for the size of the sprites

        //calculates x coordinates for corners
        double xPixelL = xPixel - size / rotZ;
        double xPixelR = xPixel + size / rotZ;

        //calculates y values for coordinates
        double yPixelL = yPixel - size / rotZ;
        double yPixelR = yPixel + size / rotZ;

        //integer versions of the x and y values for convenient use
        int xpl = (int) xPixelL;
        int xpr = (int) xPixelR;
        int ypl = (int) yPixelL;
        int ypr = (int) yPixelR;

        //ensures that the pixels are within the bounds
        if (xpl < 0) xpl = 0;
        if (xpr > width) xpr = width;
        if (ypl < 0) ypl = 0;
        if (ypr > height) ypr = height;

        rotZ *= 8; //this number controls some of the rendering, too high and you have glitches, too low and you lose detail

        //stores all pixel information into pixels
        for (int yp = ypl; yp < ypr; yp++) {
            double pixelRotationY = (yp - yPixelL) / (yPixelR - yPixelL); //calculates the displacement from centre (y)
            int yTexture = (int) (pixelRotationY * Texture.width);
            for (int xp = xpl; xp < xpr; xp++) {
                double pixelRotationX = (xp - xPixelL) / (xPixelR - xPixelL); //calculates x displacement from the center
                int xTexture = (int) (pixelRotationX * Texture.width); //adjusts colour appropriately
                if (zBuffer[xp + yp * width] > rotZ) { //ensures that the distance is correct
                    int colour = texture.pixels[(xTexture & shift) + (yTexture & shift) * Texture.width]; //stores colour
                    if (colour != 0xff00ff00){ //this shade is used to generate an alpha channel
                        if (i >= 0 && ((Dino) life.get(i)).isSick()) {
                            colour = darkenColour(colour); //if the dinosaur is sick, it will darken the shade
                        }
                        pixels[xp + yp * width] = colour; //stores the colour
                        zBuffer[xp + yp * width] = rotZ; //stores distance
                        renderDistanceLimiter(xp + yp * width); //creates fog for far objects
                    }
                }
            }
        }

    }

    public void renderSprite(double x, double y, double z, int i) { //overloaded for convenience
        renderSprite(x, y, z, Texture.dinos[((Dino) life.get(i)).getAge().getValue()][life.get(i).code.getValue()], i);
    }

    public void renderWall(double xLeft, double xRight, double zDistanceLeft, double zDistanceRight, double yHeight, Render texture) {
        //correction for movement
        double correct = 0.065;

        double xcLeft = ((xLeft / 2) - right * correct) * 2;//calculate the xLeft position as the player moves
        double zcLeft = ((zDistanceLeft / 2) - (forward * correct)) * 2; //calculate the Left z position

        //Calculates rotation and y corners
        double rotateLeftSideX = xcLeft * cos - zcLeft * sin;
        double yCornerTL = ((-yHeight) - (-up * correct)) * 2;
        double yCornerBL = ((+0.5 - yHeight) - (-up * correct)) * 2;
        double rotateLeftSideZ = zcLeft * cos + xcLeft * sin;

        //calculates right side of wall
        double xcRight = ((xRight / 2) - right * correct) * 2;
        double zcRight = ((zDistanceRight / 2) - (forward * correct)) * 2;

        //calcualtes rotation and x corners
        double rotateRightSideX = xcRight * cos - zcRight * sin;
        double yCornerTR = ((-yHeight) - (-up * correct)) * 2;
        double yCornerBR = ((+0.5 - yHeight) - (-up * correct)) * 2;
        double rotateRightSideZ = zcRight * cos + xcRight * sin;

        //this is to adjust the angle of the texture appropriately
        double texture30 = 0;
        double texture40 = 8;

        //clip is used to ensure things are cropped correctly
        double clip = 1.0;
        if (rotateLeftSideZ < clip && rotateRightSideZ < clip)
            return;

        //COHEN SUTHERLAND ALGORITHM FOR CLIPPING IS THE TWO IF STATEMENTS BELOW
        if (rotateLeftSideZ < clip)
        {
            double clip0 = (clip - rotateLeftSideZ) / (rotateRightSideZ - rotateLeftSideZ);
            rotateLeftSideZ = rotateLeftSideZ + (rotateRightSideZ - rotateLeftSideZ) * clip0;
            rotateLeftSideX = rotateLeftSideX + (rotateRightSideX - rotateLeftSideX) * clip0;
            texture30 = texture30 + (texture40 - texture30) * (clip0);
        }
        if (rotateRightSideZ < clip) {
            double clip0 = (clip - rotateLeftSideZ) / (rotateRightSideZ - rotateLeftSideZ);
            rotateLeftSideZ = rotateLeftSideZ + (rotateRightSideZ - rotateLeftSideZ) * clip0;
            rotateLeftSideX = rotateLeftSideX + (rotateRightSideX - rotateLeftSideX) * clip0;
            texture40 = texture30 + (texture40 - texture30) * (clip0);
        }

        //calculating left and right bounds after clipping
        double xPixelLeft = (rotateLeftSideX / rotateLeftSideZ * height + width / 2);
        double xPixelRight = (rotateRightSideX / rotateRightSideZ * height + width / 2);

        if (xPixelLeft > xPixelRight) return; //checking for error

        //integer versions of these variables for convenience
        int xPixelLeftInt = (int) xPixelLeft;
        int xPixelRightInt = (int) xPixelRight;

        if (xPixelLeftInt < 0) xPixelLeftInt = 0;
        if (xPixelRightInt > width) xPixelRightInt = width; //correcting any out of bounds

        //calculating yPixel properties
        double yPixelLeftTop = (yCornerTL / rotateLeftSideZ * height + height / 2.0); //calculate all four corners
        double yPixelLeftBottom = (yCornerBL / rotateLeftSideZ * height + height / 2.0);
        double yPixelRightTop = (yCornerTR / rotateRightSideZ * height + height / 2.0);
        double yPixelRightBottom = (yCornerBR / rotateRightSideZ * height + height / 2.0);

        //calculating orientation and angle of textures
        double texture1 = 1 / rotateLeftSideZ;
        double texture2 = 1 / rotateRightSideZ;
        double texture3 = texture30 / rotateLeftSideX;
        double texture4 = texture40 / rotateRightSideZ - texture3;

        //displaying wall
        for (int x = xPixelLeftInt; x < xPixelRightInt; x++) {
            double pixelRotation = (x - xPixelLeft) / (xPixelRight - xPixelLeft);
            double zWall = (texture1 + (texture2 - texture1) * pixelRotation);
            if (zBufferWall[x] > zWall) {
                continue;
            } //ensures that the wall is not beyond the buffer
            zBufferWall[x] = zWall;

            //combines above calculations to appropriately display texture
            int xTexture = (int) ((texture3 + texture4 * pixelRotation) / zWall * Texture.width / 8);

            //Finalize calculation after factoring in pixelRotation
            double yPixelTop = yPixelLeftTop + (yPixelRightTop - yPixelLeftTop) * pixelRotation;
            double yPixelBottom = yPixelLeftBottom + (yPixelRightBottom - yPixelLeftBottom) * pixelRotation;
            int yPixelTopInt = (int) (yPixelTop);
            int yPixelBottomInt = (int) (yPixelBottom);

            if (yPixelTopInt < 0) yPixelTopInt = 0;
            else if (yPixelBottomInt > height) yPixelBottomInt = height; //checks bounds

            //renders it by putting colours in appropriate spot on pixels buffer
            for (int y = yPixelTopInt; y < yPixelBottomInt; y++) {
                double pixelRotationY = (y - yPixelTop) / (yPixelBottom - yPixelTop); //y rotation for trexture
                int yTexture = (int) (pixelRotationY * Texture.width);
                int colour = texture.pixels[(xTexture & shift) + (yTexture & shift) * Texture.width];
                if ((xTexture & shift) + (yTexture & shift) * Texture.width < Texture.ground.pixels.length && x + y * width < pixels.length && colour != 0xffff00ff) {
                    pixels[x + y * width] = texture.pixels[(xTexture & shift) + (yTexture & shift) * Texture.width];//textures.trunk.pixels[(xTexture & 7) + (yTexture & 7) * 8];
                    zBuffer[x + y * width] = 1 / (texture1 + (texture2 - texture1) * pixelRotation) * 5; //render distance limiting
                    renderDistanceLimiter(x + y * width);
                }
            }
        }
    }


    public void renderDistanceLimiter(int i) { //ensures that objects that are far away get lighter
        int colour = pixels[i];
        double brightness = (10 * zBuffer[i] / renderDistance);

        //seperate colours into r, g and b
        int r = (colour >> 16) & 0xff;
        int g = (colour >> 8) & 0xff;
        int b = (colour) & 0xff;

        if (brightness > 1) { //white
            r = 255;
            b = 255;
            g = 255;
        } else { //tint of previous colour
            r += (brightness * (255 - r));
            b += (brightness * (255 - b));
            g += (brightness * (255 - g)); //makes the colour lighter
        }
        pixels[i] = (r << 16) | g << 8 | b; //store the lightened colour back into pixels
    }

    public int darkenColour(int colour) { //makes pixels darker when dinosaurs are sick
        double brightness = 0.3;

        //Seperate rgb
        int r = (colour >> 16) & 0xff;
        int g = (colour >> 8) & 0xff;
        int b = (colour) & 0xff;

        r = (int) (r * brightness);
        g = (int) (g * brightness);
        b = (int) (b * brightness); //this code makes the colour darker

        return (r << 16) | g << 8 | b; //store the value back into pixels array
    }
}