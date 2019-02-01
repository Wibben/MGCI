// The "MP3" class.
import java.awt.*;
import hsa.Console;
import java.io.*;

public class OpeningExeDemo
{
    static Console c;

    public static void main (String[] args)
    {
	//c = new Console ();
	
	try
	{
	    Process p = Runtime.getRuntime ().exec (new String[] {"C:\\Program Files\\Skype\\Phone\\Skype"});
	}
	catch (IOException e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace ();
	}
    } // main method
} // MP3 class
