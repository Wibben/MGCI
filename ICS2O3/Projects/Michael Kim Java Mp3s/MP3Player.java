/*Created by Michaerl Kim
File -> Preferences -> Java ->
Additional Class Path Directories: (add .jar file to path)
(H:\ICS203-02-03\Demos ICS20 Sep18_13\Michael Kim\JLayer.jar)
*/

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class MP3Player{

    public static void main(String[] args){
	AudioPlayer bgm = new AudioPlayer("bgm.mp3");
	bgm.play();
	try{
	    Thread.sleep(2000);
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	bgm.stop();
	//bgm.load("test.mp3");
	//bgm.loop();
	
	//bgm.play();
	//bgm.stop();
	//bgm.loop();
    }
    
}

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
