// The "ImprovedHouseMoon" class.
// Bing Li Sept 16,2014
// ICS2O3
// Improved House and Moon
import java.awt.*;
import hsa.Console;
import java.security.*;
import java.lang.*;
import java.awt.image.BufferedImage;

public class LB_ImprovedHouseMoon {
    //Console Resolution: 640 x 500

    static Console c; // The output console
    
    public static BufferedImage img = new BufferedImage(640 ,500, BufferedImage.TYPE_INT_ARGB);
    public static Graphics2D g2d;

    //*******************************Cloud for House*********************************

    public static void Cloud(int x) {
	g2d.setColor(Color.white);
	g2d.fillOval(x,90,50,50);
	g2d.fillOval(x+25,60,50,60);
	g2d.fillOval(x+55,50,60,60);
	g2d.fillOval(x+90,70,65,65);
    }

    //*******************************House*********************************

    public static void House() throws InterruptedException
    {
	//Set all values in relation to chimney...

	int chimx=75,chimy=50;
	
	//Main Background
	Color grass = new Color(22,159,0);
	Color smoke = new Color(145,145,145);
	for(int i=0; i<=100; i++) {
	    if(i<10 || i>28) {
		int r,g,b;
		r = (int)(135+1.2*i);
		g = (int)(205+0.5*i);
		b = (int)(235+0.2*i);
		Color sky = new Color(r,g,b);
		g2d.setColor(sky);
		g2d.fillRect(0,i*5,700,5);
	    }
	}
	g2d.setColor(grass);
	g2d.fillRect(0,400,640,100);
	g2d.setColor(smoke);
	for(int i=2; i<=10; i++)
	    g2d.fillOval(chimx+25+30*i,chimy-50-5*i,60,60);
	//End Main Background

	Color line = Color.black;

	//Wall
	Color brick = new Color(146,47,0);
	g2d.setColor(brick);
	g2d.fillRect(chimx+10,chimy+150,480,250);
	g2d.setColor(line);
	for(int i=chimy+175; i<=chimy+375; i += 25)
	    g2d.fillRect(chimx+10,i,480,2);
	for(int i=chimy+150; i<=chimy+375; i += 25) {
	    if(i%50==0)
		for(int j=chimx+30; j<chimx+480; j += 40)
		    g2d.fillRect(j,i,2,25);
	    else
		for(int j=chimx+50; j<chimx+480; j += 40)
		    g2d.fillRect(j,i,2,25);
	}
	g2d.drawRect(chimx+10,chimy+150,480,250);
	//End Wall

	//Door
	Color door = new Color(130,82,1);
	Color stone = new Color(120,120,120);
	Color knob = new Color(181,166,66);
	Color path = new Color(50,50,50);
	g2d.setColor(door);
	g2d.fillRect(chimx+70,chimy+215,85,165);
	g2d.setColor(stone);
	g2d.fillRect(chimx+65,chimy+380,95,10);
	g2d.fillRect(chimx+55,chimy+390,115,10);
	g2d.setColor(knob);
	g2d.fillOval(chimx+138,chimy+300,10,10);
	g2d.setColor(line);
	g2d.drawLine(chimx+55,chimy+390,chimx+55,chimy+400);
	g2d.drawLine(chimx+55,chimy+390,chimx+170,chimy+390);
	g2d.drawLine(chimx+170,chimy+390,chimx+170,chimy+400);
	g2d.drawRect(chimx+65,chimy+380,95,10);
	g2d.drawRect(chimx+70,chimy+215,85,165);
	g2d.drawRect(chimx+75,chimy+220,75,160);
	g2d.fillRect(chimx+142,chimy+303,2,4);
	g2d.setColor(path);
	int[] pathx = {chimx+55,chimx+30,chimx+195,chimx+170};
	int[] pathy = {chimy+400,chimy+450,chimy+450,chimy+400};
	g2d.fillPolygon(pathx,pathy,4);
	g2d.setColor(Color.black);
	g2d.fillRect(chimx+95,chimy+258,30,15);
	g2d.setColor(Color.white);
	g2d.drawLine(chimx+103,chimy+262,chimx+103,chimy+268);
	g2d.drawLine(chimx+108,chimy+262,chimx+108,chimy+268);
	g2d.drawOval(chimx+113,chimy+262,3,6);
	//End Door

	//Door Design
	Color light = new Color(161,125,65);
	Color dark = new Color(99,39,0);
	g2d.setColor(light);
	int[] designx = {chimx+85,chimx+90,chimx+90,chimx+85};
	for(int k=0; k<2; k++) {
	    for(int i=0; i<2; i++) {
		int[] designy = {chimy+230,chimy+235,chimy+245,chimy+250};
		g2d.fillPolygon(designx,designy,4);
		designy[0]=chimy+280; designy[1]=chimy+285; designy[2]=chimy+315; designy[3]=chimy+320;
		g2d.fillPolygon(designx,designy,4);
		for(int j=0; j<4; j++)
		    designy[j] += 50;
		g2d.fillPolygon(designx,designy,4);
		for(int j=0; j<4; j++)
		    designx[j] += 30;
	    }
	    designx[0]=designx[3]=chimx+105; designx[1]=designx[2]=chimx+100;
	}
	g2d.setColor(dark);
	designx[0]=chimx+85; designx[1]=chimx+90; designx[2]=chimx+100; designx[3]=chimx+105;
	for(int k=0; k<2; k++) {
	    int[] designy = {chimy+230,chimy+235,chimy+235,chimy+230};
	    for(int i=0; i<3; i++) {
		g2d.fillPolygon(designx,designy,4);
		for(int j=0; j<4; j++)
		    designy[j] += 50;
	    }
	    designy[0]=designy[3]=chimy+250; designy[1]=designy[2]=chimy+245;
	    g2d.fillPolygon(designx,designy,4);
	    for(int i=0; i<4; i++)
		designy[i] += 70;
	    for(int i=0; i<2; i++) {
		g2d.fillPolygon(designx,designy,4);
		for(int j=0; j<4; j++)
		    designy[j] += 50;
	    }
	    for(int i=0; i<4; i++)
		designx[i] += 30;
	}
	//End Door Design

	//Window
	Color window = new Color(135,205,235);
	g2d.setColor(Color.white);
	g2d.fillRect(chimx+220,chimy+170,210,160);
	g2d.setColor(window);
	g2d.fillRect(chimx+225,chimy+175,45,150);
	g2d.fillRect(chimx+275,chimy+175,100,150);
	g2d.fillRect(chimx+380,chimy+175,45,150);
	g2d.setColor(line);
	g2d.drawRect(chimx+220,chimy+170,210,160);
	g2d.drawRect(chimx+225,chimy+175,45,150);
	g2d.drawRect(chimx+275,chimy+175,100,150);
	g2d.drawRect(chimx+380,chimy+175,45,150);
	//End Window

	//Curtain
	Color curtainOut = new Color(9,54,158);
	Color curtainIn = new Color(5,27,79);
	g2d.setColor(curtainOut);
	g2d.fillRect(chimx+226,chimy+176,10,147);
	g2d.fillRect(chimx+241,chimy+176,10,145);
	g2d.fillRect(chimx+256,chimy+176,10,146);
	g2d.fillRect(chimx+385,chimy+176,10,146);
	g2d.fillRect(chimx+400,chimy+176,10,145);
	g2d.fillRect(chimx+415,chimy+176,10,147);
	g2d.setColor(curtainIn);
	g2d.fillRect(chimx+236,chimy+176,5,143);
	g2d.fillRect(chimx+251,chimy+176,5,144);
	g2d.fillRect(chimx+395,chimy+176,5,144);
	g2d.fillRect(chimx+410,chimy+176,5,143);
	//End Curtain

	//Flowerbed
	Color[] flowers = {Color.pink,Color.red,Color.magenta,new Color(135,205,235)};
	int counter=0;
	for(int i=0; i<8; i++) {
	    int neg=1;
	    if(i%2 != 0) neg=-1;
	    g2d.setColor(Color.green);
	    g2d.fillRect(chimx+220+30*i,chimy+360+2*neg,2,25);
	    g2d.setColor(flowers[counter]);
	    g2d.fillOval(chimx+210+30*i,chimy+348+2*neg,10,10);
	    g2d.fillOval(chimx+210+30*i,chimy+360+2*neg,10,10);
	    g2d.fillOval(chimx+222+30*i,chimy+348+2*neg,10,10);
	    g2d.fillOval(chimx+222+30*i,chimy+360+2*neg,10,10);
	    g2d.setColor(Color.yellow);
	    g2d.fillOval(chimx+215+30*i,chimy+353+2*neg,12,12);
	    counter++;
	    if(counter==4) counter=0;
	}
	g2d.setColor(stone);
	g2d.fillRect(chimx+210,chimy+385,230,15);
	for(int i=0; i<23; i++)
	    g2d.fillArc(chimx+210+10*i,chimy+380,10,10,0,180);
	//End Flowerbed

	//Animation
	int cloudx=0;
	while(!c.isCharAvail()) {
	    for(int i=10; i<=28; i++) {
		int r,g,b;
		r = (int)(135+1.2*i);
		g = (int)(205+0.5*i);
		b = (int)(235+0.2*i);
		Color sky = new Color(r,g,b);
		g2d.setColor(sky);
		g2d.fillRect(0,i*5,700,5);
		if(i==20) {
		for(int j=0; j<=24; j++) {
			int g2 = (int)(115+5.6*j);
			Color sun = new Color(255,g2,0);
			g2d.setColor(sun);
			g2d.fillOval(535+2*j,5+2*j,100-4*j,100-4*j);
		    }
		    Cloud(cloudx);
		}
	    }
		    
	    //Chimney
	    g2d.setColor(smoke);
	    g2d.fillOval(chimx+25,chimy-30,60,60);
	    g2d.fillOval(chimx+25,chimy-50,60,60);
	    g2d.fillOval(chimx+55,chimy-55,60,60);
	    g2d.setColor(brick);
	    g2d.fillRect(chimx+25,chimy,60,150);
	    g2d.setColor(line);
	    for(int i=chimy+25; i<=chimy+125; i += 25)
		g2d.fillRect(chimx+25,i,60,2);
	    for(int i=chimy; i<=chimy+125; i += 25) {
		if(i%50==0) g2d.fillRect(chimx+45,i,2,25);
		else g2d.fillRect(chimx+65,i,2,25);
	    }
	    g2d.drawRect(chimx+25,chimy,60,150);
	    //End Chimney

	    //Roof
	    Color roof = new Color(100,200,150);
	    Color frame = new Color(86,52,23);
	    int[] roofx = {chimx-25,chimx+250,chimx+525};
	    int[] roofy = {chimy+150,chimy+50,chimy+150};
	    g2d.setColor(roof);
	    g2d.fillPolygon(roofx,roofy,3);
	    g2d.setColor(line);
	    int[] innerRidgex = {chimx+22,chimx+27,chimx+252,chimx+478,chimx+473,chimx+247};
	    int[] innerRidgey = {chimy+150,chimy+150,chimy+70,chimy+150,chimy+150,chimy+70};
	    g2d.fillPolygon(innerRidgex,innerRidgey,6);
	    int[] outerRidgex = {chimx-27,chimx-22,chimx+252,chimx+528,chimx+523,chimx+247};
	    int[] outerRidgey = {chimy+150,chimy+150,chimy+50,chimy+150,chimy+150,chimy+50};
	    g2d.fillPolygon(outerRidgex,outerRidgey,6);
	    for(int i=1; i<4; i++)
		g2d.fillRect(chimx+(56*i)+27,chimy+148-(20*i),446-(112*i),2);
	    g2d.fillRect(chimx-22,chimy+148,544,2);
	    g2d.fillRect(chimx+250,chimy+50,2,22);
	    g2d.setColor(frame);
	    g2d.fillOval(chimx+215,chimy+76,70,70);
	    g2d.setColor(window);
	    g2d.fillOval(chimx+220,chimy+81,60,60);
	    g2d.setColor(frame);
	    g2d.fillRect(chimx+247,chimy+81,5,60);
	    g2d.fillRect(chimx+220,chimy+108,60,5);
	    //End Roof

	    cloudx += 10; // Moving the cloud
	    if(cloudx==640) cloudx=0;
	    Thread.sleep(350); // Delay
	    // Note about Thread.sleep(long l):
	    // function and main must have "throws InterruptedException" for proper syntax, l is milliseconds delayed
	    c.drawImage(img,0,0,null);
	    c.setCursor(25,1);
	    c.print("Press <Enter> to exit...");
	}
	//End Animation
	c.clear();
    }
    
    //*******************************Preset-Stars*********************************
    
    public static void Stars(int x, int y, int star)
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
	} else if(star==4) {
	    g2d.setColor(Color.pink);
	    g2d.fillOval(x,y,5,5);
	    g2d.setColor(Color.white);
	    g2d.fillOval(x+2,y+2,1,1);
	}
    }
    
    //*******************************Moon*********************************

    public static void Moon() throws InterruptedException
    {
	//so many gradients because I can
	//Ramdomizing the generation of stars

	//Background
	g2d.setColor(Color.black);
	g2d.fillRect(0,0,640,500);
	for(int i=0; i<=100; i++) {
	    int r,b;
	    r = (int)(1.46*i);
	    b = (int)(0.18*i);
	    Color sky=new Color(r,0,b);
	    g2d.setColor(sky);
	    g2d.fillOval(460+i,i-25,200-i*2,200-i*2);
	}
	for(int i=0; i<=100; i++) {
	    int r,g,b;
	    r = (int)(0.24*i);
	    g = (int)(0.16*i);
	    b = (int)(1.07*i);
	    Color sky = new Color(r,g,b);
	    g2d.setColor(sky);
	    g2d.fillOval(35+i,70+i*2,200-i*2,400-i*4);
	}
	for(int i=0; i<=100; i++) {
	    int r,g,b;
	    r = (int)(0.82*i);
	    g = (int)(0.02*i);
	    b = (int)(0.74*i);
	    Color sky = new Color(r,g,b);
	    g2d.setColor(sky);
	    g2d.fillRect((int)(275+i*1.5),(int)(375+i*0.5),300-i*3,100-i);
	}
	//End Background

	
	//Stars
	for(int i=0; i<750; i++) {
	    SecureRandom rand = new SecureRandom();
	    int randStar = rand.nextInt(4)+1;
	    SecureRandom randx = new SecureRandom();
	    int starx = randx.nextInt(630);
	    SecureRandom randy = new SecureRandom();
	    int stary = randy.nextInt(490);
	    Stars(starx,stary,randStar);
	    if(i%5==0) c.drawImage(img,0,0,null);
	}
	//End Stars
	//SecureRandom more random than Random, used in cryptography
	
	//Moon
	for(int j=0; j<=180; j++) {
	    for(int i=0; i<=100; i++) {
		int r,g,b;
		r = (int)((255-0.00708*i*j)%127.5);
		g = (int)((127.5+0.00708*i*j*2)%180);
		b = (int)((0.708*j*2)%127.5);
		Color moon = new Color(r,g,b);
		g2d.setColor(moon);
		g2d.fillArc(25+i,250+i,200-2*i,200-2*i,(30+j*2)%360,1);
	    }
	    c.drawImage(img,0,0,null);
	}
	//End Moon
	
	//Planet
	for(int j=0; j<2; j++) {
	    for(int i=0; i<=25; i++) {
		int r,g,b;
		r = (int)(34+3.56*i);
		g = (int)(4+4.28*i);
		b = (int)(177+0.96*i);
		Color planet = new Color(r,g,b);
		g2d.setColor(planet);
		g2d.fillOval((int)(225+0.75*i),(int)(25+2.95*i),(int)(400-1.5*i),400-3*i);
	    }
	    for(int i=0; i<=25; i++) {
		int r,g,b;
		r = (int)(123-1.78*i);
		g = (int)(111-2.14*i);
		b = (int)(201-0.48*i);
		Color planet = new Color(r,g,b);
		g2d.setColor(planet);
		g2d.fillOval((int)(287.5+i),(int)(246.25+2.95*i),(int)(275-2*i),(int)(177-2.92*i));
	    }
	    for(int i=0; i<50; i++) {
		int r,g,b;
		r = (int)(123-0.445*i);
		g = (int)(111-0.535*i);
		b = (int)(201-0.12*i);
		Color planet = new Color(r,g,b);
		g2d.setColor(planet);
		g2d.fillOval((int)(360+0.75*i),(int)(185+0.5*i),(int)(180-1.5*i),60-i);
	    }
	    c.drawImage(img,0,0,null);
	    if(j==0) {
		c.setCursor(12,32);
		c.print("This was supposed to be neptune...");
		Thread.sleep(2000);
	    }
	}
	
	//End Planet
	
	c.setCursor(25,1);
	c.print("Press Enter to continue");
	c.getChar();
	c.clear();
    }

    //*******************************Main*********************************

    public static void main(String[] args) throws InterruptedException
    {
	c = new Console();       
	
	g2d = img.createGraphics();
	
	int choice;
    
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 2:\n");
	    c.print("1-Improved House\n");
	    c.print("2-Improved Moon\n(you must minimize the window and open it again to view moon properly)\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    if(choice==1) House();
	    else if(choice==2) Moon();
	} while(choice!=0);
    
	c.print("Program terminated\n");

	// Place your program here.  'c' is the output console
    } // main method
} // Improved_HM class
