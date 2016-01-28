/**
 * Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * 13 January 2016
 * ICS4U1-04
 */

public class Render {
    //This class stores 2D pixel information about the 3d objects
    protected int width, height;
    public int[] pixels; //this will store the RGB values of all the pixels

    public Render(int w, int h) { //constructor that creates the render
        width = w;
        height = h;
        pixels = new int[width * height];
    }

    public void draw(Render render, int xOffset, int yOffset) //the x and z offset represent how far from the origin object is
    {
        for (int y = 0; y < render.height; y++) //this loop draws the render onto your screen at the requested spot
        {
            int yPix = y + yOffset;
            if (yPix < 0 || yPix >= height) continue; //checks bounds

            for (int x = 0; x < render.width; x++) {
                int xPix = x + xOffset;
                if (xPix < 0 || xPix >= width) continue; //checks bounds for each line

                int alpha = render.pixels[x + y * render.width]; //checks for transparency
                if (alpha > 0) {
                    pixels[xPix + yPix * width] = alpha;
                }
            }
        }
    }
}
