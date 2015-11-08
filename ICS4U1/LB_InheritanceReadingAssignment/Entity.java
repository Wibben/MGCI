/* Bing Li
 * ICS4U1
 * Inheritance Reading Assignment
 */
import java.awt.*;
import javax.imageio.*;
import java.io.*;

/** 
 * Entity is an abstract class
 * this means that there will not be Entity objects, it is purely meant to aid in creating subclasses
 * a variable typed Entity can only refer to one of the subclasses of Entity
 */
abstract class Entity
{
    /**
     * Public vs. private vs. protected
     * If I want to be able to reference a method or variable anywhere, I will set it as public
     * If I only want the class itself (but not its subclasses) to reference it, I will set it prvate
     * If I want the class and its subclasses to be able to reference it, I will set it protected
     */
    protected int hp,atk,def;
    protected boolean alive;
    protected String name;
    
    Entity(int hp, int atk, int def, String name)
    {
        // "this" refers to the current instance of the object
        // this way I can have same name variables and differentiate between them
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.name = name;
        alive = true;
    }
    
    // To be customized with name of entity
    String showStats()
    {
        return "Hp: " + hp + " Atk: " + atk + " Def: " + def;
    }
    
	
	/**
     * The attack method can be considered to be polymorphic as the actions it performs differs
     * in accordance to the object using it, so the statement object.atrack(target) will have different
     * results depending on the type of the object using the method
	 * e.g. Players can critical hit, skeletons have countdowns to come back to life, goblins can hit twice
	 */
    void attack(Entity target)
    {
        // Basically calls defense, but here to be overwritten
        // with effects that happen when you attack
        // e.g. goblins attacking twice
        
        // Attack only if alive
        if(alive) target.defend(this);
    }
    
    void defend(Entity attacker)
    {
        // Can be overwritten to get effects that happen when you defend
        // e.g. death, temporary death for skeletons
        // Hp lost is difference between attacker's atk and defender's def
        // Minimum health lost is 1
        int lostHp = attacker.atk-def;
        
        if(lostHp<1) lostHp = 1;
        System.out.printf("%s attacked %s for %d damage\n", attacker.name, name, lostHp);
        
        hp -= lostHp;
        
        // Make them dead if they're dead
        if(hp<=0) {
            System.out.printf("%s is dead\n", name);
            hp = 0;
            alive = false;
        }
    }
    
    /** 
     * This is an abstract method becasue every one of its derived methods will be unique
     * and tailored to the subclass
     * This is here to tell the computer all subclasses of Entity have a redraw(params) method
     * thus allowing me to use the line "object.redraw(params)" where object is type Entity
     * 
     * Using an abstract method also prevents me from creating a subclass of Entity without
     * a redraw(params) method
     */
    abstract void redraw(Graphics g, int x, int y);
    
    Image loadImage (String name)  //Loads image from file
    {
        Image img = null;
        try
        {
            img = ImageIO.read (new File (name));
        }
        catch (IOException e)
        {
            System.out.println("Error: " + name + " does not exist");
        }
        return img;
    }
	
    boolean isDead()
    {
        return !alive;
    }
}

/**
 * extends Entity allows the subclass to inherit the methods
 * of the Entity abstract class
 * 
 * I can access the methods and variables of the superclass by using the keyword super
 * accessing a constructor: super()
 * accessing a method: super.method()
 * accessing a variable: super.variable
 * 
 * Note: If there isn't another variable with the same name as a protected variable in
 * the superclass, I can simply use variable, the super.variable is only required to
 * differentiate between variables of the same name bewteen the sub and superclass.
 * Similarly, if I do not have a method with the same name as a method in the superclass,
 * the superclass's method with that name will be called
 * 
 * I can also override/extend the methods of the superclass
 * by declaring a method of the same return type, name, and parameters
 * if I still want to use the processes of the superclass, I can use super.methodName(params)
 * to access and use the original method
 */

// Players have a 50% critical chance, critical hits do 1.5x damage
class Player extends Entity
{
    public Player()
    {
        super(50,20,6,"Player");
    }
    
    // Add in critical hit chance
    public void attack(Entity target)
    {
        // If critical hit modify atk to do 1.5x damage against target, then modify back after hit
        // Round up modified attack
        // If atk originally did 1 damage, it now does 2 damage
        if(0.5>Math.random()) {
            System.out.printf("CRITICAL HIT!\n");
            int lostHp = atk-target.def;
        
            if(lostHp<1) atk = target.def+2;
            else atk = (int)Math.ceil(target.def*2.5);
        }
        
        super.attack(target);
        
        // Set atk back to the original 20
        atk = 20;
    }
    
    // This method isn't really used, it is just here to demonstrate the fact
    // that you must create a method if there is an abstract method in the superclass
    public void redraw(Graphics g, int x, int y)
    {
        // Placeholder
        // this is just empty and won't draw anything
    }
}

// Skeletons can respawn 1 times (2 lives in total)
// Respawns 2 turns after death
class Skeleton extends Entity
{
    public static final String NAME = "Skeleton";
    private int lives,countdown;
    
    public Skeleton()
    {
        super(15,10,10,"Skeleton");
        lives = 1; // 1,0 completely dead when die on life 0
    }
    
    public void attack(Entity target)
    {
        // If still on countdown, do countdown
        // If countdown is 0, skeleton is alive
        if(countdown>0) countdown--;
        else if(lives>0 && !alive) {
            System.out.printf("%s came back to life!\n", name);
            alive = true;
            hp = 15;
            lives--;
        }
        
        super.attack(target);
    }
    
    public void defend(Entity attacker)
    {
        super.defend(attacker);
        
        // If skeleton still has lives, start countdown
        // Else, set countdown to -1 so it will stay dead during attack phase
        if(!alive && lives>0) countdown = 2;
        else countdown = -1;
    }
    
    public void redraw(Graphics g, int x, int y)
    {
        // Source: http://36.media.tumblr.com/tumblr_m42w389Abc1r413h3o1_250.png
        if(alive) g.drawImage(super.loadImage("Sprites\\skeleton.jpg"),x,y,null);
        else g.drawImage(super.loadImage("Sprites\\skeletonDead.jpg"),x,y,null);
        
        g.drawString(super.showStats(),x,y);
    }
}

// Goblins can potentially attack twice
// Second attack is based on a random number generator
// Each goblin has a different second attack chance
class Goblin extends Entity
{
    // secondChance is a double in the interval [0,1)
    private double secondChance;
    
    public Goblin()
    {
        super(20,8,10,"Goblin");
        secondChance = Math.random();
    }
    
    // Add in chance to attack a second time
    public void attack(Entity target)
    {
        // Checking if attacking a second time
        if(secondChance>=Math.random() && alive) {
            System.out.printf("DOUBLE HIT!\n");
            super.attack(target);
        }
        
        super.attack(target);
    }
    
    public void redraw(Graphics g, int x, int y)
    {
        // Source: http://piq.codeus.net/static/media/userpics/piq_79469_400x400.png
        if(alive) g.drawImage(super.loadImage("Sprites\\goblin.jpg"),x,y,null);
        else g.drawImage(super.loadImage("Sprites\\goblinDead.jpg"),x,y,null);
        
        g.drawString(super.showStats(),x,y);
    }
}
