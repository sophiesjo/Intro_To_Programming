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
 * This class extends from the mouse class and creates a short sighted mouse who is able to see and move immediatley to the cheese if the cheese is within a short, 2 block distance from the mouse.
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class ShortSightedMouse extends Mouse
{
    //Instance variables

    /**
     * Constructor for the short sighted mouse object
     */
    public ShortSightedMouse()
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
        // Get the list of all possible spots
        ArrayList<Location> possibleSpots = emptyNeighbors();
        Location nextSpot;  //will be the location of the next spot

        if(possibleSpots.size() == 0)
            return location();

        else if(cheeseInSight())
        {
            //Move the mouse towards the cheese
            nextSpot = grid().getNeighbor(location(),grid().getDirection(location(),getMaze().getFinishLoc()));
            return nextSpot;
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

    /** This method will test to see if the cheese is within sight of the mouse and will only find this true when the mouse is two or less blocks away from the cheese
     * 
     * @return      boolean that will be true if the mouse is within 2 blocks of the cheese
     **/
    protected boolean cheeseInSight()
    {
        //Get the locations that are one and two blocks away from the cheese
        Location firstLocNearCheese =  grid().getNeighbor(getMaze().getFinishLoc(), grid().getDirection(getMaze().getFinishLoc(), location()));
        Location secondLocNearCheese =  grid().getNeighbor(firstLocNearCheese, grid().getDirection(getMaze().getFinishLoc(), location()));

        //Test if current location is equal to the two locations near the cheese
        if(location().equals(firstLocNearCheese) || location().equals(secondLocNearCheese))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}

