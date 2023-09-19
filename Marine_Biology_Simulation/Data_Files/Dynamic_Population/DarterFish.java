
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import edu.kzoo.grid.Grid;
import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.util.Debug;
import edu.kzoo.util.NamedColor;
import edu.kzoo.util.RandNumGenerator;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  This class creates a darter fish that quickly moves in the water because the fish has a method that moves it two blocks if there are two blocks empty in front of it.
 *  If the fish is unable to move two blocks ahead then it moves only one space, and if it can't move one space then it changes reverse direction. 
 *  The darter fish are hard coded to be yellow.
 *  This class also has a method that will create the child of a fish specific to this class.
 *
 *  @author Sophie Sjogren
 *  @version 24 February 2021
 **/

public class DarterFish extends Fish
{
    // Class Variables: Shared among ALL objects of this class

    // Instance Variables: Encapsulated data for EACH object of this class

    // Constructors
    // constructors and related helper methods
    /** Constructs a fish at the specified location in a given environment.
     *  The Fish is assigned a random direction and random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     **/
    public DarterFish(Grid env, Location loc)
    {
        this(env, loc, env.randomDirection(), NamedColor.YELLOW);
    }

    /** Constructs a fish at the specified location and direction in a
     *  given environment.  The Fish is assigned a random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     *  @param dir    direction the new fish is facing
     **/
    public DarterFish(Grid env, Location loc, Direction dir)
    {
        this(env, loc, dir, NamedColor.YELLOW);
    }

    /** Constructs a fish of the specified color at the specified location
     *  and direction.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     *  @param dir    direction the new fish is facing
     *  @param col    color of the new fish
     **/
    public DarterFish(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc, dir, col);      //pass parameters to fish constructor
    }

    // Accessor (or Observer)  methods
    
    // Modifier methods

    /** Moves this fish in its environment.
     **/
    protected void move()
    {
        // Find a location to move to.
        Debug.print("Fish " + toString() + " attempting to move.  ");
        Location nextLoc = nextLocation();

        // If the next location is different, move there.
        if ( ! nextLoc.equals(location()) )
        {
            // Move to new location.
            Location oldLoc = location();
            changeLocation(nextLoc);

            // Update direction in case fish had to turn to move.
            Direction newDir = grid().getDirection(oldLoc, nextLoc);
            changeDirection(newDir);
            Debug.println("  Moves to " + location() + direction());
        }
        else
        {
            this.changeDirection(direction().reverse());
            Debug.println("Change direction.");
        }
    }
    
    /** Finds this fish's next location.
     *  A fish may move to any empty adjacent locations except the one
     *  behind it (fish do not move backwards).  If this fish cannot
     *  move, <code>nextLocation</code> returns its current location.
     *  @return    the next location for this fish
     **/
    protected Location nextLocation()
    {
        // Dart two ahead if possible, if can't dart one ahead, and if can't stay in same position
        
        // Find location of one cell in front
        Direction frontDir = direction();
        Location locationAhead = grid().getNeighbor(location(), frontDir);
        
        if ( grid().isEmpty(locationAhead) )
        {
            Location locationTwoAhead = grid().getNeighbor(locationAhead, frontDir);
            if ( grid().isEmpty(locationTwoAhead) )
            {
                return locationTwoAhead;
            }
            else
            {
                return locationAhead;
            }
        }
        else
        {
            return location();
        }
        
    }
    
    /** Creates the child of a fish.
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        DarterFish child = new DarterFish(grid(), loc, grid().randomDirection());
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }
}