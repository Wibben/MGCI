import java.util.ArrayList;

/**
 * Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * 13 January 2016
 * ICS4U1-04
 */
public class Screen extends Render {
    private Render3D render; //this class converts the 2d pixel info from render onto the screen

    public Screen(int w, int h, ArrayList<Life> life) {
        super(w, h);
        render = new Render3D(width, height, life); //creates rneder3d
    }

    public void render(Game game) {
        for (int i = 0; i < width * height; i++) //resets all the pixels
            pixels[i] = 0;
        render.floor(game); //draws the floor onto render3d
        draw(render, 0, 0);// draws onto screen

    }

    //returns the render 3d
    public Render3D get3d() {
        return render;
    }
}
