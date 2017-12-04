import ecs100.*;
import java.awt.*;

/**
 * This class manages and operates the simulation
 *
 * @author Siôn Grabham-Madden
 * @version 1
 */
public class SimulationController {
    public ActiveArea area;
    public boolean firstSim;
    public boolean simRunning;

    /**
     * Constructor
     */
    public SimulationController() {
        area = new ActiveArea();
        firstSim = true;
        simRunning = false;
    }
    
    /**
     * This method sets up the UI and other important details.
     */
    public static void main(String[] arg) {
        SimulationController controller = new SimulationController();
        controller.initialise();
    }
    
    /**
     * This method initialises the UI of the simulation.
     */
    public void initialise() {
        UI.initialise();
        UI.setImmediateRepaint(false);
        UI.addButton("Initialise Predator-Prey simulation", this::predatorPrey);
        
        UI.println("Welcome to a cellular automata simulation program.");
        UI.println("This program simulates a Predator-Prey environment.");
        UI.println("Please initialise the simulation.");
        UI.println("You may then begin.");
    }
    
    /**
     * This method prepares the Predator-Prey simulation model.
     */
    public void predatorPrey() {
        if (firstSim) {
            UI.addButton("Start simulation",this::simulate);
            UI.addButton("Stop simulation",this::end);
            firstSim = false;
        }
        area.instantiate();
        UI.clearPanes();
        this.draw();
        UI.println("Simulation initialised.");
    }
    
    /**
     * This method loops through the simulation.
     */
    public void simulate() {
        UI.clearText();
        UI.println("Simulation is now running.");
        simRunning = true;
        
        while(simRunning) {
            area.step();
            draw();
            //UI.sleep(10);
        }
    }
    
    /**
     * This method ends the loop, stopping the simulation.
     */
    public void end() {
        simRunning = false;
        UI.clearText();
        UI.println("Simulation has stopped.");
        UI.println("To continue current simulation, press start again.");
        UI.println("To run a new simulation, please initialise.");
    }

    /**
     * This method will render the simulation.
     */
    public void draw() {
        area.draw();
        UI.repaintGraphics();
    }
}
