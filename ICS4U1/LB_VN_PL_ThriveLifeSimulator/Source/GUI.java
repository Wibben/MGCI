import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1-04
 */
public class GUI extends Canvas implements Runnable//the main interface
{
    private int width = 800; //the width of the window
    private int height = 600; //the height of the window
    private Thread thread; //thread to be able to do multiple things at once
    private boolean running = false; //the thread is not running yet
    private Screen screen; //this creates a screen
    private BufferedImage img; //buffered image to handle images to be displayed on screen
    private int[] pixels; //to store data from the buffered image
    private static Game game; //this class controls all the user controls
    private InputHandler input; //this controls all the input to the program
    private BufferStrategy b;
    private static boolean autoControl;

    public GUI(int w, int h, int wb, int hb, int d, Run r) {
        width = w;
        height = h;
        autoControl = false;
        Dimension size = new Dimension(width, height); //dimension
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size); //so the window it the appropriate size

        game = new Game(d, d, 0, wb, 0, hb, r); //starts new Game
        screen = new Screen(width, height, game.getLife()); //initializes screen
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //initializes buffered image
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData(); //converst buffered image piel data into an array

        input = new InputHandler();
        addKeyListener(input);
        addFocusListener(input); //add appropriate listeners
    }
    //Getter Methods
    public static Game getGame() {
        return game;
    }

    public Screen getScreen() {
        return screen;
    }

    public void start() //this method allows the program to start as an applet
    {
        if (running)
            return; // if it is already running, don't want to start again
        running = true;
        thread = new Thread(this);
        thread.start(); //starts thread
    }

    public void stop() //allows you to stop the program as an applet
    {
        if (!running) //can't exit if it is already over
            return;
        running = false;
        try {
            thread.join();
        } //thread.join() tries to end the thread
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0); //exits
        }
    }

    public void run() //implements run method from Runnable interface
    {
        //following variables regulate the game's speed regardless of speed of computer
        double unprocessedSecs = 0;
        long prevTime = System.nanoTime();
        double secsPerTick = 1 / 60.0;
        int tickCount = 0;

        while (running) {
            //following code calculates ensures the game runs at a consistent speed
            long currentTime = System.nanoTime();
            long passedTime = currentTime - prevTime;
            prevTime = currentTime;
            unprocessedSecs += passedTime / 1000000000.0;
            while (unprocessedSecs > secsPerTick) {
                requestFocus();
                tick();
                unprocessedSecs -= secsPerTick;
                tickCount++;
                if (tickCount % 60 == 0) {
                    prevTime += 1000;
                }
            }
            render();
        }
    }

    private void tick() {
        game.tick(input.getKey(),autoControl);
    }
    //this will read the key boolean array from input and send it to game, which will use the information to
    //perform the necessary actions

    public void changeSize(int w, int h) {
        width = w;
        height = h - 60;
        Dimension size = new Dimension(width, height); //dimension
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);//so the window it the appropriate size

        screen = new Screen(width, height, game.getLife()); //initiaizes screen
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //initializes buffered image
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData(); //converst buffered image piel data into an array
        repaint();
    }

    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3); //this allows for a 3D Game
            return;
        }

        screen.render(game); //renders the game onto the screen

        for (int i = 0; i < width * height; i++)
            pixels[i] = screen.pixels[i]; //sends information to the screen class

        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(img, 0, 0, width, height, null); //draws the buffered image onto the screen

        //output frames per second
        g.setFont(new Font("Verdana", 0, 30)); //MIDDLE argument is italics/bold/bold italics
        g.setColor(Color.YELLOW);
        //g.drawString("" + fps + " fps", 10, 30);

        g.dispose(); //disposes graphics object to optimize code
        bufferStrategy.show();

    }
    
    //setter for autocontrol
    public static void setAutoControl(boolean b) { autoControl = b; }
}
