// The "MyArithmetic" class.
import java.awt.*;
import hsa.Console;

public class MyArithmetic
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	c.print("12345678901234567890123456789012345678901234567890\n");
	c.print(57.654,10,1);
	c.setCursor(2,20);
	c.print(-17);
	c.print("\n\n");
	c.setCursor(4,10);
	c.print("Integers are a subset of Real numbers\n\n");
	c.print(8754.77-652.876+423.754*0.5,30,3);
	
	// Place your program here.  'c' is the output console
    } // main method
} // MyArithmetic class
