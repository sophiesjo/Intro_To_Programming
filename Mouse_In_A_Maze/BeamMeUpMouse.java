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
 * This class extends from the mouse class and creates a mouse the moves to the location of the cheese in one step.
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class BeamMeUpMouse extends Mouse
{ 
    //Instance variables
    
    /**
     * Constructor for beamMeUp mouse object
     */
    public BeamMeUpMouse()
    {
        super();
    }

    /** This method returns the next location for the mouse to be the same location as the cheese
     * 
     * @return  the location of the cheese
     **/
    protected Location nextLocation()
    {
        //Use maze object to get cheese location
        Maze maze =  (Maze) grid();
        Location finishLoc = maze.getFinishLoc();
        
        return finishLoc;
    }
}
