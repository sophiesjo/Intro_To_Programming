import edu.kzoo.grid.ColorBlock;
import edu.kzoo.util.NamedColor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.util.Debug;
import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.util.RandNumGenerator;

/**
 * This class creates a mouse object which is able to find next locations on the grid and move to those locations.
 * This is the abstract class to the other mouse classes.
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class Mouse extends ColorBlock
{
    //Instance variables 
    protected int numOfSteps = 0;       //Counts the number of steps it takes a mouse to reach the cheese

    /**
     * Constructor for the mouse object and determines the color to be light gray
     */
    public Mouse()
    {
        super(new NamedColor(Color.LIGHT_GRAY));
    }

    /** This method returns a maze as an object to be used in the class
     * 
     * @return  the maze object 
     **/
    public Maze getMaze() 
    {
        return (Maze) grid();
    }
    
    /**
     * This method moves the mouse by finding the next location and determines the mouses' movements according to where the cheese is and where the mouse currently is.
     *
     */
    protected void move()
    {
        //Initialize variables
        Location nextLoc = nextLocation();              //Find the next location
        Maze maze = getMaze();                          //Initialize maze
        Location finishLoc = maze.getFinishLoc();       //Find location of cheese

        numOfSteps++;          //Keeps track of the number of steps taken before reaching the cheese
        
        // If the next location is equal to the current location, then no movement. If there is a different location, then check to see if the cheese is there. If there is cheese there, then remove the cheese. Move the mouse no matter if there is cheese there.
        if ( nextLoc.equals(location()) )
        {
            Debug.println("  Does not move.");
            return;
        }
        else
        {
            if ( nextLoc.equals(finishLoc) )
            {
                maze.remove(nextLoc);       //Remove cheese from grid
                Debug.println("The mouse took " + numOfSteps + " to find the cheese.");         //Print the number of steps taken to get the cheese
            }
            // Move mouse to the new location
            Location oldLoc = location();
            changeLocation(nextLoc);
            Debug.println("  The mouse moves to " + nextLoc);
        }

    }

    /** Finds this mouse's next location.
     *  
     *  @return    the next location for the mouse
     **/
    protected Location nextLocation()
    {
        return location();
    }

     /** Returns a string representing key information about the mouse.
      * 
     *  @return  a string indicating the class and location
     **/
     public String toString()
    {
        return this.getClass().getName().toString() + " " + location().toString();
    }
}
