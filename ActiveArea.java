import ecs100.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class creates the active area of the simulation
 *
 * @author Siôn Grabham-Madden
 * @version 1
 */
public class ActiveArea {
    private int sideLength;
    public Cell[][] cellArray;
    public int predCount;
    public int preyCount;

    /**
     * Constructor
     */
    public ActiveArea() {
        sideLength = 500;
        cellArray = new Cell[250][250];
        predCount = 0;
        preyCount = 0;
    }

    /**
     * This method will run through the cells, creating the start of the simulation.
     */
    public void instantiate() {
        for (int y = 0; y < 250; y++) {
            for (int x = 0; x < 250; x++) {
                String ident = "";
                int randomNum = ThreadLocalRandom.current().nextInt(0, 10000 + 1);
                if (randomNum <= 9990) {
                    ident = "empty";
                }
                else if (randomNum <=9998) {
                    ident = "prey";
                }
                else if (randomNum <= 10000) {
                    ident = "predator";
                }
                cellArray[y][x] = new Cell(ident, (x*2), (y*2));
            }
        }
    }
    
    /**
     * This method runs through one step of the simulation.
     */
    public void step() {
        //Predators move first
        for (int y = 0; y < 250; y++) {
            for (int x = 0; x < 250; x++) {
                if (cellArray[y][x].identity.equals("predator")) {
                    if(cellArray[y][x].health > 100) {
                        cellArray[y][x].health = 100;
                    }
                    
                    //hunger
                    cellArray[y][x].health -= 15;
                    //starvation
                    if (cellArray[y][x].health <= 0) {
                        cellArray[y][x].identity = "empty";
                    }
                    //movement
                    int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
                    if ((y-1 >= 0)&&(x-1 >=0) && randomNum == 1) {
                        if (cellArray[y-1][x-1].identity.equals("empty")) {
                            cellArray[y-1][x-1].identity = "predator";
                            cellArray[y-1][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y-1][x-1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y-1][x-1].health;
                            cellArray[y-1][x-1].identity = "predator";
                            cellArray[y-1][x-1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((y-1 >= 0) && randomNum == 2) {
                        if (cellArray[y-1][x].identity.equals("empty")) {
                            cellArray[y-1][x].identity = "predator";
                            cellArray[y-1][x].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y-1][x].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y-1][x].health;
                            cellArray[y-1][x].identity = "predator";
                            cellArray[y-1][x].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((y-1 >= 0)&&(x+1 <=249) && randomNum == 3) {
                        if (cellArray[y-1][x+1].identity.equals("empty")) {
                            cellArray[y-1][x+1].identity = "predator";
                            cellArray[y-1][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y-1][x+1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y-1][x+1].health;
                            cellArray[y-1][x+1].identity = "predator";
                            cellArray[y-1][x+1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((x+1 <=249) && randomNum == 4) {
                        if (cellArray[y][x+1].identity.equals("empty")) {
                            cellArray[y][x+1].identity = "predator";
                            cellArray[y][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y][x+1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y][x+1].health;
                            cellArray[y][x+1].identity = "predator";
                            cellArray[y][x+1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((x+1 <=249)&&(y+1 <=249) && randomNum == 5) {
                        if (cellArray[y+1][x+1].identity.equals("empty")) {
                            cellArray[y+1][x+1].identity = "predator";
                            cellArray[y+1][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y+1][x+1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y+1][x+1].health;
                            cellArray[y+1][x+1].identity = "predator";
                            cellArray[y+1][x+1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((y+1 <=249) && randomNum == 6) {
                        if (cellArray[y+1][x].identity.equals("empty")) {
                            cellArray[y+1][x].identity = "predator";
                            cellArray[y+1][x].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y+1][x].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y+1][x].health;
                            cellArray[y+1][x].identity = "predator";
                            cellArray[y+1][x].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((y+1 <=249)&&(x-1 >=0) && randomNum == 7) {
                        if (cellArray[y+1][x-1].identity.equals("empty")) {
                            cellArray[y+1][x-1].identity = "predator";
                            cellArray[y+1][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y+1][x-1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y+1][x-1].health;
                            cellArray[y+1][x-1].identity = "predator";
                            cellArray[y+1][x-1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                    else if ((x-1 >=0) && randomNum == 8) {
                        if (cellArray[y][x-1].identity.equals("empty")) {
                            cellArray[y][x-1].identity = "predator";
                            cellArray[y][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                        else if (cellArray[y][x-1].identity.equals("prey")) {
                            cellArray[y][x].health += cellArray[y][x-1].health;
                            cellArray[y][x-1].identity = "predator";
                            cellArray[y][x-1].health = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        }
                    }
                }
            }
        }
        
        
        // Then prey move
        for (int y = 0; y < 250; y++) {
            for (int x = 0; x < 250; x++) {
                if (cellArray[y][x].identity.equals("prey")) {
                    if(cellArray[y][x].health > 100) {
                        cellArray[y][x].health = 100;
                    }
                    
                    int randomNum = 0;
                    //growth
                    cellArray[y][x].health += 5;
                    //reproduction
                    if (cellArray[y][x].health > 100) {
                        ArrayList<Integer> available = new ArrayList<Integer>();
                        if ((y-1 >= 0)&&(x-1 >=0) && cellArray[y-1][x-1].identity.equals("empty")) {
                            available.add(1);
                        }
                        if ((y-1 >= 0) && cellArray[y-1][x].identity.equals("empty")) {
                            available.add(2);
                        }
                        if ((y-1 >= 0)&&(x+1 <=249) && cellArray[y-1][x+1].identity.equals("empty")) {
                            available.add(3);
                        }
                        if ((x+1 <=249) && cellArray[y][x+1].identity.equals("empty")) {
                            available.add(4);
                        }
                        if ((x+1 <=249)&&(y+1 <=249) && cellArray[y+1][x+1].identity.equals("empty")) {
                            available.add(5);
                        }
                        if ((y+1 <=249) && cellArray[y+1][x].identity.equals("empty")) {
                            available.add(6);
                        }
                        if ((y+1 <=249)&&(x-1 >=0) && cellArray[y+1][x-1].identity.equals("empty")) {
                            available.add(7);
                        }
                        if ((x-1 >=0) && cellArray[y][x-1].identity.equals("empty")) {
                            available.add(8);
                        }
                        
                        if(available.size() > 0) {
                            int pick = ThreadLocalRandom.current().nextInt(1, available.size() + 1);
                            randomNum = available.get(pick-1);
                            
                            if (randomNum == 1) {
                                cellArray[y-1][x-1].identity = "prey";
                                cellArray[y-1][x-1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 2) {
                                cellArray[y-1][x].identity = "prey";
                                cellArray[y-1][x].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 3) {
                                cellArray[y-1][x+1].identity = "prey";
                                cellArray[y-1][x+1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 4) {
                                cellArray[y][x+1].identity = "prey";
                                cellArray[y][x+1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 5) {
                                cellArray[y+1][x+1].identity = "prey";
                                cellArray[y+1][x+1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 6) {
                                cellArray[y+1][x].identity = "prey";
                                cellArray[y+1][x].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 7) {
                                cellArray[y+1][x-1].identity = "prey";
                                cellArray[y+1][x-1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            else if (randomNum == 8) {
                                cellArray[y][x-1].identity = "prey";
                                cellArray[y][x-1].health = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                            }
                            
                            cellArray[y][x].health = 1;
                        }
                    }
                    
                    //movement
                    randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
                    if ((y-1 >= 0)&&(x-1 >=0) && randomNum == 1) {
                        if (cellArray[y-1][x-1].identity.equals("empty")) {
                            cellArray[y-1][x-1].identity = "prey";
                            cellArray[y-1][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((y-1 >= 0) && randomNum == 2) {
                        if (cellArray[y-1][x].identity.equals("empty")) {
                            cellArray[y-1][x].identity = "prey";
                            cellArray[y-1][x].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((y-1 >= 0)&&(x+1 <=249) && randomNum == 3) {
                        if (cellArray[y-1][x+1].identity.equals("empty")) {
                            cellArray[y-1][x+1].identity = "prey";
                            cellArray[y-1][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((x+1 <=249) && randomNum == 4) {
                        if (cellArray[y][x+1].identity.equals("empty")) {
                            cellArray[y][x+1].identity = "prey";
                            cellArray[y][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((x+1 <=249)&&(y+1 <=249) && randomNum == 5) {
                        if (cellArray[y+1][x+1].identity.equals("empty")) {
                            cellArray[y+1][x+1].identity = "prey";
                            cellArray[y+1][x+1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((y+1 <=249) && randomNum == 6) {
                        if (cellArray[y+1][x].identity.equals("empty")) {
                            cellArray[y+1][x].identity = "prey";
                            cellArray[y+1][x].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((y+1 <=249)&&(x-1 >=0) && randomNum == 7) {
                        if (cellArray[y+1][x-1].identity.equals("empty")) {
                            cellArray[y+1][x-1].identity = "prey";
                            cellArray[y+1][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    else if ((x-1 >=0) && randomNum == 8) {
                        if (cellArray[y][x-1].identity.equals("empty")) {
                            cellArray[y][x-1].identity = "prey";
                            cellArray[y][x-1].health = cellArray[y][x].health;
                            cellArray[y][x].identity = "empty";
                            cellArray[y][x].health = 0;
                        }
                    }
                    
                    
                }
            }
        }
    }

    /**
     * This method will draw the active area.
     */
    public void draw() {
        UI.clearGraphics();
        UI.setColor(Color.black);
        UI.fillRect(0,0,sideLength,sideLength);

        predCount = 0;
        preyCount = 0;

        for (int y = 0; y < 250; y++) {
            for (int x = 0; x < 250; x++) {
                if (cellArray[y][x].identity.equals("prey")) {
                    preyCount += 1;
                }
                else if (cellArray[y][x].identity.equals("predator")) {
                    predCount += 1;
                }
                cellArray[y][x].draw();
            }
        }

        UI.setColor(Color.black);
        UI.setFontSize(18);
        UI.drawString(("Current Prey: " + preyCount), 20, 520);
        UI.drawString(("Current Predators: " + predCount), 20, 540);

    }
}
