import ecs100.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class handles an individual cell of the simulation
 *
 * @author Siôn Grabham-Madden
 * @version 1
 */
public class Cell {
    
    public String identity;
    public int sideLength;
    public int top;
    public int left;
    
    public int health;
    public int reproductionVal;

    /**
     * Constructor
     */
    public Cell(String iden, int x, int y) {
        identity = iden;
        sideLength = 2;
        left = x;
        top = y;
        
        int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
        health = randomNum;
    }
    
    public void draw() {
        if (identity.equals("empty")) {
            UI.setColor(Color.black);
            UI.fillRect(left,top,sideLength,sideLength);
        } 
        else if (identity.equals("prey")) {
            UI.setColor(Color.green);
            UI.fillRect(left,top,sideLength,sideLength);
        }
        else if (identity.equals("predator")) {
            UI.setColor(Color.red);
            UI.fillRect(left,top,sideLength,sideLength);
        }
    }
}
