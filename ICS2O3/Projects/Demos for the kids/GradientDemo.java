// The "GradientDemo" class.
import java.awt.*;
import hsa.Console;

public class GradientDemo
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();

	// This makes 100 700x5 rectangles, each a bit lighter than the
	// previous, creating a gradient

	for (int i = 0 ; i <= 100 ; i++)
	{
	    int r, g, b;

	    /*
	    FORMAT READ THIS

	    variable = (int)(a+b*i);

	    a -> The r,g,or b value of your original colour
	    b -> (255-a)/100, you must compute this yourself
	    as you will want a decimal so your gradient looks
	    as nice as possible

	    */
	    // My starting colour is sky blue (135,205,235)
	    r = (int) (135 + 1.2 * i);
	    g = (int) (205 + 0.5 * i);
	    b = (int) (235 + 0.2 * i);
	    // (int)(real number) rounds the real number down
	    Color sky = new Color (r, g, b);
	    c.setColor (sky);
	    c.fillRect (0, i * 5, 700, 5);
	}

	// Place your program here.  'c' is the output console
    } // main method
} // GradientDemo class
