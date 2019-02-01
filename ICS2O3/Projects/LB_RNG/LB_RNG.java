// The "LB_RNG" class.
import java.awt.*;
import hsa.Console;
// RNG by SecureRandom
import java.security.*;
// Sound
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class LB_RNG
{
    static Console c;           // The output console

    // Console Dimensions:
    // Characters:  25 x 80
    // Pixels:      500 x 640
    
    // Randomizing colour and making the numbers "pop" out
    public static void display(String output)
    {
	Font font;
	Color color;
	SecureRandom rand = new SecureRandom();
	for(int i=0; i<100; i++) {
	    font = new Font(null, Font.PLAIN, (int)(5.12*i));
	    c.setFont(font);
	    color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
	    c.setColor(color);
	    // 2 digit numbers require different spacing, I don't know why you need 3 digit numbers so I didn't make any
	    if(output.length()==1) c.drawString(output,275-i,250+2*i);
	    else c.drawString(output,(int)(275-2.5*i),250+2*i);
	}
    }
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	AudioPlayer drum = new AudioPlayer("Drumroll.mp3");
	boolean visited[] = new boolean[1000];
	int upper,lower,rand,cnt=0,generated;
	SecureRandom RNG = new SecureRandom();
	char choice;
	
	
	for(int i=0; i<1000; i++)
	    visited[i] = false;
	
	// Buttons, I'll come back to this later
	/*
	JPanel p = new JPanel();

	JButton b1 = new JButton("Exit");
	
	p.setLayout(null);
	b1.setBounds(40,100,100,60);
	p.add(b1);
	*/
	
	// Header
	c.print("\t\t\t\tRandom Number Generator\n");
	for(int i=0; i<80; i++,c.print("-"));
	c.print("\n\t\tThis Random Number Generator generates an unique \n\t\t  number each time as long as it's not closed.\n\n");
	
	// Input of upper and lower bounds
	c.print("Please enter the lower and upper bounds(order does not matter): ");
	lower = c.readInt();
	upper = c.readInt();
    
	// Check if upper is actually higher than lower
	if(upper<lower) {
	    int temp = upper;
	    upper = lower;
	    lower = temp;
	}
	upper++;
	
	// Marking pre-determined found numbers
	c.print("Please enter the numbers that you do not want to generate\nEnter -1 to finish entry:\n");
	do {
	    generated = c.readInt();
	    if(generated<-1 && generated<1000)visited[generated] = true; // Preventing out-of-bounds error
	    if(generated<=upper && generated>=lower) cnt++;
	} while(generated!=-1);
	
	// Actual number generation
	do {
	    // Check if there are still numbers to generate
	    if(cnt++<(upper-lower)) {
		// Play drumroll
		drum.play();
		
		do { // Generating a random number until a new number has been found
		    rand = RNG.nextInt(upper-lower) + lower;
		} while(visited[rand]==true);
		visited[rand] = true; // Mark the new number as found

		// 5 seconds into the clip then stop
		try {
		    Thread.sleep(4500);
		} catch(InterruptedException ex) {}
		drum.stop();
		
		// Display results
		c.clear();
		c.print("\nThe next number is:");
		String output = "";
		output += rand;
		display(output);
		c.setCursor(25,1);
		c.print("\nPress x to exit or any other key to continue...");
		choice = c.getChar();
	    } else {
		c.clear();
		c.print("\nAll numbers in range have been generated.\nPress any key to exit...");
		c.getChar();
		choice = 'x';
	    }
	} while(choice!='x');
	
	c.clear();
	c.print("Program terminated");
	
	// Place your program here.  'c' is the output console
    } // main method
} // LB_RNG class

// Creds to Michael Kim
class AudioPlayer implements Runnable{
    private Player player;
    private Thread thread;
    private boolean loop = false;
    private String fileName;
    public AudioPlayer(String s){
	load(s);
    }
    public void load(String s){
	fileName = s;
	try{
	    player = new Player(new FileInputStream(s));
	    thread = new Thread(this);
	}
	catch(Exception e){
	    e.printStackTrace();
	}
    }
    public void play(){
	if(thread != null) thread = null;
	if(thread == null) load(fileName);
	thread.start();
    }
    public void loop(){
	loop = true;
	play();
    }
    public void stop(){
	loop = false;
	thread = null;
	player.close();
    }
    public void run(){
	do{
	    try{
		player.play();
	    }
	    catch(Exception e){
		e.printStackTrace();
	    }
	    if(loop) load(fileName);
	}while(loop);
    }
}
