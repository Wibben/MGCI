// The "SoundDemo" class.
import java.awt.*;
import hsa.Console;

// Import these file - VERY IMPORTANT
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class LB_VN_SoundDemo
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	/*
	
	There's really nothing to show, this is just going to be a section explaining
	how to use Michael Kim's godlike program
	
	*Declaration 
	AudioPlayer var = new AudioPlayer("filename");
	
	*To play a mp3
	var.play();
	
	*To stop a mp3
	var.stop();
	
	*To load a different mp3 with the same variable name
	var.load("new_filename");
	
	*To loop a mp3
	var.loop();
	
	var is the name you want for your variable
	
	filename and new_file name are the filenames of the mp3 files
	IMPORTANT:
	- file names must be exact, including the extension .mp3
	- the .mp3 files must be inside the same folder as your .java file
	- you need to have JLayer up and running
	
	*/
	
	// Place your program here.  'c' is the output console
    } // main method
} // SoundDemo class

// COPY PASTE the following AT THE VERY VERY END
// DO NOT PUT THIS INTO public class (w/e you named your program) {}

// Created by: Michael Kim the GOD
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
