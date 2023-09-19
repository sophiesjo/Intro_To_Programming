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
 * This class extends from the mouse class and creates a mouse that will cluelessly look around in random locations. 
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class CluelessMouse extends Mouse
{
    //Instance variables
    
    /**
     * Constructor for the clueless mouse object
     */
    public CluelessMouse()
    {
        super();
    }

    /** This method finds empty locations around the mouse
     * 
     *  @return    an ArrayList containing the locations of the empty neighbors
     **/
    protected ArrayList<Location> emptyNeighbors()
    {
        // Get a list of all the areas around the location
        ArrayList<Location> neighbors = grid().neighborsOf(location());

        // Sort through the areas and add any empty locations or the finish location to the list
        ArrayList<Location> emptyNbrs = new ArrayList<Location>();
        for ( Location loc : neighbors )
        {
            if ( grid().isEmpty(loc) || loc.equals(getMaze().getFinishLoc()) )
            {
                emptyNbrs.add(loc);
            }
        }

        return emptyNbrs;
    }

    /** This method randomly selects the next location out of the list of empty neighbors and creates the random movement affect of the clueless mouse
     * 
     * @return      the location that is randomly selected for the mouse
     **/
    protected Location nextLocation()
    {
        // Get a list of all empty neighbor locations
        ArrayList<Location> possibleLocs = emptyNeighbors();
        if(possibleLocs.size() == 0)
        {
            return location();
        }
            
        // Return a location from the list by using a random number generator
        Random randNumGen = RandNumGenerator.getInstance();
        int randNum = randNumGen.nextInt(possibleLocs.size());
        Location nextSpot = (Location) possibleLocs.get(randNum);
        return nextSpot;
    }

}
