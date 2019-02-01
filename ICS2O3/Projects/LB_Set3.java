// The "LB_Set3" class.
// Bing Li Sept 12, 2014
import java.awt.*;
import hsa.Console;

public class LB_Set3
{
    //*******************************BlueRect*********************************
  
    public static void BlueRect() { 
	c.setColor(Color.blue);
	c.fillRect(635,0,5,5);
	c.setColor(Color.green);
	c.fillRect(0,0,5,5);
	c.setColor(Color.yellow);
	c.fillRect(635,495,5,5);
	c.setColor(Color.pink);
	c.fillRect(317,248,5,5);
    }
    
    //*******************************Rectangles*********************************
  
    public static void Rectangles() { 
	c.setColor(Color.blue);
	c.fillRect(635,0,5,5);
	c.setColor(Color.green);
	c.fillRect(0,0,5,5);
	c.setColor(Color.yellow);
	c.fillRect(635,495,5,5);
	Color col = new Color(255,0,255);
	c.setColor(col);
	c.fillRect(317,248,5,5);
    }
    
    //*******************************MyColors*********************************
  
    public static void MyColors() { 
	Color col = new Color(0,200,200);
	c.setColor(col);
	c.fillRect(0,0,100,100);
    }
    
    //*******************************Circles*********************************
  
    public static void Circles() { 
	c.setColor(Color.magenta);
	c.drawOval(0,0,250,250);
    }
    
    //*******************************House*********************************
  
    public static void House() { 
	c.setColor(Color.red);
	c.fillRect(50,0,50,50);
	c.fillRect(0,50,200,50);
	c.setColor(Color.blue);
	c.fillRect(25,100,150,150);
	c.setColor(Color.yellow);
	c.fillRect(50,125,25,25);
	c.fillRect(125,125,25,25);
	c.setColor(Color.green);
	c.fillRect(75,175,50,75);
    }
    
    //*******************************Moon*********************************
  
    public static void Moon() { 
	c.setColor(Color.black);
	c.fillRect(0,0,635,495);
	c.setColor(Color.yellow);
	c.fillOval(123,123,100,100);
	c.setColor(Color.black);
	c.fillOval(150,100,100,100);
    }
    
    //*******************************ColSquares*********************************
  
    public static void ColSquares() { 
	c.setColor(Color.blue);
	c.fillRect(0,0,400,400);
	c.setColor(Color.yellow);
	c.fillRect(50,50,300,300);
	c.setColor(Color.green);
	c.fillRect(100,100,200,200);
	c.setColor(Color.red);
	c.fillRect(150,150,100,100);
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	int choice;
    
	do {
	c.print("\nPlease choose from the following menu\n\n");
	c.print("Enter a number from 1 to 7:\n");
	c.print("1 - BlueRect\n");
	c.print("2 - Rectangles\n");
	c.print("3 - MyColors\n");
	c.print("4 - Circles\n");
	c.print("5 - House\n");
	c.print("6 - Moon\n");
	c.print("7 - ColSquares\n");
	c.print("\nEnter 0 to exit\n");
    
	choice = c.readInt();
    
	c.clear();
	if(choice==1) BlueRect();
	else if(choice==2) Rectangles();
	else if(choice==3) MyColors();
	else if(choice==4) Circles();
	else if(choice==5) House();
	else if(choice==6) Moon();
	else if(choice==7) ColSquares();
	
	c.setCursor(25,1);
	c.print("Press Enter to continue");
	c.getChar();
	c.clear();
    
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
	// Place your program here.  'c' is the output console
    } // main method
} // BlueRect class
