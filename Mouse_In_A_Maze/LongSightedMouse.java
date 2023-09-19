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
 * This class extends the mouse class and creates a long sighted mouse that is able to see if the cheese is within its row and column, and if it is then the mouse will move towards the cheese.
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class LongSightedMouse extends Mouse
{
    //Instance variables 
    
    /**
     * Constructor for the long sighted mouse object
     */
    public LongSightedMouse()
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
                emptyNbrs.add(loc);
        }

        return emptyNbrs;
    }

    /** This method selects the next location out of the list of empty neighbors and tests to see if the mouse is within a 2 grid distance of the cheese and will move towards the final location if it is
     *  If the cheese is not within sight then the mouse will move randomly
     * 
     * @return      the location that is selected for the mouse
     **/
    protected Location nextLocation()
    {
        //Get a list of the possible spots the mouse could move into
        ArrayList<Location> possibleSpots = emptyNeighbors();
        Location nextSpot;  //will be the location of the next spot
        
        //Test if the mouse is in the same row or column as the cheese or if the mouse is at the cheese. If not, the mouse will continue to move randomly
        if(possibleSpots.size() == 0)
        {
            return location();
        }
        else if(location().row() == getMaze().getFinishLoc().row()  && anythingInTheWay() || location().col() == getMaze().getFinishLoc().col() && anythingInTheWay())
        {
            return  grid().getNeighbor(location(),grid().getDirection(location(),getMaze().getFinishLoc()));
        }
        else if(location().equals(grid().getNeighbor(getMaze().getFinishLoc(), grid().getDirection(getMaze().getFinishLoc(), location()))))
        {
            return getMaze().getFinishLoc();
        }
        else
        {
            // Return a location from the list by using a random number generator
            Random randNumGen = RandNumGenerator.getInstance();
            int randNum = randNumGen.nextInt(possibleSpots.size());
            nextSpot = (Location) possibleSpots.get(randNum);
            return nextSpot;
        }
    }

    /** This method will check if the cheese is in sight by seeing if there are empty spaces between the mouse and the cheese
     * 
     *  @return    boolean and this will be true only when there are no obstacles between the cheese and the mouse
     **/
    protected boolean anythingInTheWay()
    {
        //Test for any obstacles between the mouse and cheese
        if(grid().isEmpty(grid().getNeighbor(location(), grid().getDirection(location(),getMaze().getFinishLoc()))))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
