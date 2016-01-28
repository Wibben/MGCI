import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Nicholas Vadivelu, Bingran Li, Lawrence Pang
 * Thrive Life Simulator
 * ICS4U1-04
 */
public class InputHandler implements KeyListener, FocusListener { //Handles all the input from the user
    private boolean[] key = new boolean[68836];

    //getter for key
    public boolean[] getKey() {
        return key;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode > 0 && keyCode < key.length) {
            key[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode > 0 && keyCode < key.length)
            key[keyCode] = false;
    }


    @Override
    public void focusLost(FocusEvent e) { //makes sure that the controls stop once the screen loses focus
        for (int i = 0; i < key.length; i++)
            key[i] = false;

    }

    //Unused methods (from interfaces)
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
