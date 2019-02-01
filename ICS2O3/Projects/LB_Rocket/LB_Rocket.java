// The "Rocket" class.
import java.awt.*;
import hsa.Console;
//Image
import javax.imageio.*;
import java.io.*;
//Sound
import javazoom.jl.player.Player;
import java.io.FileInputStream;
//Animation
import java.awt.image.BufferedImage;
//RNG
import java.security.SecureRandom;
//Rotation
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;

public class LB_Rocket
{
    static Console c;           // The output console
    
    public static BufferedImage img = new BufferedImage(640 ,500, BufferedImage.TYPE_INT_ARGB);
    public static BufferedImage rot;
    public static Graphics2D g2d,ro;
    public static SecureRandom RNG = new SecureRandom();
    public static int star[][] = new int[25][3]; 
    
    public static Image rocket = loadImage ("Rocket.png");
    public static Image flight = loadImage ("Flight.png");
    public static Image mars = loadImage("Mars.png");
    
    //-----------------------------LoadImage----------------------------------------------
    
    public static Image loadImage (String name)  //Loads image from file
    {
	Image img = null;
	try
	{
	    img = ImageIO.read (new File (name));
	}
	catch (IOException e)
	{
	    c.println("Error: file does not exist");
	}
	return img;
    }
    
    //-----------------------------launch----------------------------------------------
    
    public static void launch(int move)
    {
	for(double j=0; j<move; j+=2.1) { // This moves slow enough to not need a delay, the interval is to make it exit just as the sound ends
	    Color grass = new Color(22,159,0);
	    
	    for(int i=0; i<=100; i++) { // Gradient the sky
		int r,g,b;
		r = (int)(135+1.2*i); // Gradually decreasing rgb values
		g = (int)(205+0.5*i);
		b = (int)(235+0.2*i);
		Color sky = new Color(r,g,b); 
		g2d.setColor(sky);
		g2d.fillRect(0,i*5,700,5);
	    }
	    
	    g2d.setColor(grass); // Draw main grass
	    g2d.fillRect(0,400,640,100);
	    
	    g2d.drawImage(rocket,300,(int)(350-j),null); // Draw rocket now moving
	    
	    for(int i=0; i<50; i++) { // Making it seem like there's a bump there
		int r,g,b;
		r = (int)(22+0.26*i);
		g = (int)(159+0.84*i);
		b = (int)(0.18*i);
		Color bump = new Color(r,g,b);
		g2d.setColor(bump); // Color of bump
		g2d.fillOval(220+i,400,200-2*i,75-i);
	    }
	    
	    // Launchpad
	    g2d.setColor(new Color(125,125,125));
	    g2d.fillRect(285,390,70,15);
	    g2d.fillRect(340,320,10,70);
	    g2d.fillRect(317,340,23,5);
	    g2d.fillRect(320,353,20,5);
	    g2d.fillRect(323,366,17,5);
	    g2d.fillRect(323,375,17,5);
	    g2d.setColor(Color.black);
	    g2d.drawRect(285,390,70,15);
	    g2d.drawRect(340,320,10,70);
	    g2d.drawRect(317,340,23,5);
	    g2d.drawRect(320,353,20,5);
	    g2d.drawRect(323,366,17,5);
	    g2d.drawRect(323,375,17,5);
	    for(int i=0; i<7; i++) {
		g2d.drawLine(340,320+10*i,350,330+10*i);
		g2d.drawLine(350,320+10*i,340,330+10*i);
	    }
	    // End Launchpad
	    
	    c.drawImage(img,0,0,null); // Displaying 
	}
    }
    
    //-----------------------------countdown----------------------------------------------
    
    public static void countdown()
    {
	Font font = new Font(null, Font.PLAIN, 50);
	c.setFont(font);
	Color color;
	AudioPlayer count = new AudioPlayer("countdown.mp3");
	
	count.play(); // Start playing the count down sound  
	for(int i=10; i>0; i--) { // Counting down form 10 to 1
	    c.setColor(Color.black); // Set the color to black for numbers
	    c.drawString(""+i,0,40); // Display the countdown in big numbers
	    
	    try {Thread.sleep(1000);} // Delay
	    catch(Exception e) {}
	    
	    // Coverup - covering up previously displayed numbers
	    for(int j=0; j<=10; j++) { // Blend in with background gradient
		int r,g,b;
		r = (int)(135+1.2*j);
		g = (int)(205+0.5*j);
		b = (int)(235+0.2*j);
		Color sky = new Color(r,g,b);
		c.setColor(sky);
		c.fillRect(0,j*5,60,5);
	    }
	    // End coverup
	}
    }
    
    //-----------------------------stars----------------------------------------------
    
    public static void stars(int x, int y, int star)
    {
	if(star==1) {
	    g2d.setColor(Color.yellow);
	    g2d.fillOval(x,y,5,5);
	    g2d.setColor(Color.blue);
	    g2d.fillOval(x+2,y+2,1,1);
	} else if(star==2) {
	    g2d.setColor(Color.green);
	    g2d.fillOval(x,y,4,4);
	    g2d.setColor(Color.red);
	    g2d.fillOval(x+1,y+1,2,2);
	} else if(star==3) {
	    g2d.setColor(Color.blue);
	    g2d.fillOval(x,y,3,3);
	    g2d.setColor(Color.pink);
	    g2d.fillOval(x+1,y+1,1,1);
	} else if(star==0) {
	    g2d.setColor(Color.pink);
	    g2d.fillOval(x,y,5,5);
	    g2d.setColor(Color.white);
	    g2d.fillOval(x+2,y+2,1,1);
	}
    }
    
    //-----------------------------journey----------------------------------------------
    
    public static void journey()
    {
	for(int i=0; i<220; i++) {
	    // Black BG for space
	    g2d.setColor(Color.black);
	    g2d.fillRect(0,0,640,500);
	
	    // Animate rocket flying
	    for(int j=0; j<25; j++) // Generate Stars every time rocket moves
		stars(RNG.nextInt(630),RNG.nextInt(490),RNG.nextInt(4));
	    
	    g2d.drawImage(flight,(int)(3*i),(int)(475-2.5*i),null);
	    
	    c.drawImage(img,0,0,null);
	}   
    }
    
     //-----------------------------journey----------------------------------------------
    
    public static void landing()
    {
	// Used in rotations
	AffineTransform transform;
	AffineTransformOp op;
	
	AudioPlayer yay = new AudioPlayer("yay.mp3");
	
	for(int i=0; i<100; i++) { // Horizontal movement
	    rot = new BufferedImage(200 ,200, BufferedImage.TYPE_INT_ARGB);
	    ro = rot.createGraphics();
	    ro.drawImage(rocket,100,100,null);
	    // 90 degree rotation
	    transform = new AffineTransform();
	    transform.rotate(Math.toRadians(90), 100, 100);
	    op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    rot = op.filter(rot, null);
	
	    // Black BG for space
	    g2d.setColor(Color.black);
	    g2d.fillRect(0,0,640,500);
	
	    for(int j=0; j<25; j++) // Generate stars
		stars(star[j][0],star[j][1],star[j][2]);
	    
	    g2d.drawImage(rot,-200+3*i,220,null);
	    g2d.drawImage(mars,300,320,null);
	    
	    c.drawImage(img,0,0,null);
	}
	
	for(int i=1; i<=90; i++) { // Rotation
	    // Must re-declare each time to make sure image is clear
	    rot = new BufferedImage(200 ,200, BufferedImage.TYPE_INT_ARGB);
	    ro = rot.createGraphics();
	    ro.drawImage(rocket,100,100,null);
	    transform = new AffineTransform();
	    transform.rotate(Math.toRadians(90-i), 100, 100);
	    op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    rot = op.filter(rot, null);
	    
	    // Black BG for space
	    g2d.setColor(Color.black);
	    g2d.fillRect(0,0,640,500);
	
	    for(int j=0; j<25; j++) // Generate stars
		stars(star[j][0],star[j][1],star[j][2]);
	    
	    g2d.drawImage(rot,100+3*i,220-3*i,null);
	    g2d.drawImage(mars,300,320,null);
	    
	    c.drawImage(img,0,0,null);
	}
	
	for(int i=0; i<230; i++) { // Vertical movement
	    // Don't need to rotate, but need to declare because I was working with rot, so my coords reference rot
	    rot = new BufferedImage(200 ,200, BufferedImage.TYPE_INT_ARGB);
	    ro = rot.createGraphics();
	    ro.drawImage(rocket,100,100,null);
	
	    // Black BG for space
	    g2d.setColor(Color.black);
	    g2d.fillRect(0,0,640,500);
	
	    for(int j=0; j<25; j++) // Generate stars
		stars(star[j][0],star[j][1],star[j][2]);
	    
	    g2d.drawImage(rot,370,-50+i,null);
	    g2d.drawImage(mars,300,320,null);
	    
	    c.drawImage(img,0,0,null);
	}
	
	yay.play(); // Playing sound
    }

    //-----------------------------Main----------------------------------------------
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	g2d = img.createGraphics(); // Making a canvas to draw onto
	
	// Resizing
	rocket = rocket.getScaledInstance(25, 75, Image.SCALE_SMOOTH);
	flight = flight.getScaledInstance(100,80, Image.SCALE_SMOOTH);
	mars = mars.getScaledInstance(400,400, Image.SCALE_SMOOTH);
	
	for(int i=0; i<25; i++) { // Initializing star attributes for final scene
	    star[i][0] = RNG.nextInt(630);
	    star[i][1] = RNG.nextInt(490);
	    star[i][2] = RNG.nextInt(4);
	}
	
	launch(1); // only 1 iteration, AKA not moving
	
	countdown();
	
	launch(425); // 425 iterations -> Animation
	
	journey();
	
	landing();
	
	// Place your program here.  'c' is the output console
    } // main method
} // Rocket class

// Scaling images
// Image scaledImage = img.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);

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
