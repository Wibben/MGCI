/*Bing Li
Oct 24, 2014
ICS2O3
Blast off program with audio
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class LB_BlastOff
{
    //*******************************For*********************************
  
    public static void For() throws InterruptedException{ 
	//Count down from 10 to 1 with 1 second delays
	for(int i=10; i>0; i--,Thread.sleep(1000)) // Yes Mr. Brossard, you can do this, but I believe so far it's only in for loops
	    c.println(i);
	//Termination message
	c.println("Blast Off!");
    }
    
    //*******************************DoWhile*********************************
  
    public static void DoWhile() throws InterruptedException { 
	int i = 10;
	//Count down from 10 to 1 with 1 second delays
	do {
	    c.println(i--); // displays i, then i--
	    Thread.sleep(1000);
	} while(i > 0);
	//Termination message
	c.println("Blast Off!");
    }
	
    //*******************************While*********************************
  
    public static void While() throws InterruptedException { 
	int i = 10;
	//Count down from 10 to 1 with 1 second delays
	while(i > 0) { 
	    c.println(i--);
	    Thread.sleep(1000);
	}
	//Termination message
	c.println("Blast Off!");
    }
    
    //*******************************Main*********************************
    
    static Console c;           // The output console
    
    public static void main (String[] args) throws InterruptedException
    {
	c = new Console ();
    
	int choice;
	
	//Regular menu stuffs
	do {
	    c.print("\nPlease choose from the following menu\n\n");
	    c.print("Enter a number from 1 to 3:\n");
	    c.print("1 - For Loop\n");
	    c.print("2 - Do While Loop\n");
	    c.print("3 - While Loop\n");
	    c.print("\nEnter 0 to exit\n");
    
	    choice = c.readInt();
    
	    c.clear();
	    AudioPlayer countDown = new AudioPlayer("Rocket.mp3");
	    countDown.play();
	    if(choice==1) For();
	    else if(choice==2) DoWhile();
	    else if(choice==3) While();
	    Thread.sleep(2000);
	    countDown.stop();
	    
	    c.print("Press Enter to continue");
	    c.getChar();
	    c.clear();
	} while(choice!=0);
    
	c.print("Program terminated\n");
	
    } // main method
} // BlastOff class

//Credits to Michael Kim
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
