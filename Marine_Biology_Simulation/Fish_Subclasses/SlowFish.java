
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
 *  This class creates a slow fish which will move 20% of the time.
 *  Thic class uses a random number to determine when the fish will move.
 *  The fish will be red
 *  This class also has a method that will create the child of a fish specific to this class.
 *  This class also has a method that allows the fish to turn even if it doesn't move locations and it can
 *  turn either right or left. There is a 2 out of 3 chance it will turn if it does not move.
 *
 *  @author Sophie Sjogren
 *  @version 25 February 2021
 **/

public class SlowFish extends Fish
{
    // Class Variables: Shared among ALL objects of this class

    // Instance Variables: Encapsulated data for EACH object of this class
    protected double probOfTurning;

    // Constructors
    // constructors and related helper methods
    /** Constructs a fish at the specified location in a given environment.
     *  The Fish is assigned a random direction and random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     **/
    public SlowFish(Grid env, Location loc)
    {
        this(env, loc, env.randomDirection(), NamedColor.RED);
    }

    /** Constructs a fish at the specified location and direction in a
     *  given environment.  The Fish is assigned a random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     *  @param dir    direction the new fish is facing
     **/
    public SlowFish(Grid env, Location loc, Direction dir)
    {
        this(env, loc, dir, NamedColor.RED);
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
    public SlowFish(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc, dir, col);      //pass parameters to fish constructor
        this.probOfTurning = 1.0 / 5.0;   //a 1 in 5 chance of turning
    }

    // Accessor (or Observer)  methods

    // Modifier methods


    /** Finds this fish's next location.
     *  A fish may move to any empty adjacent locations except the one
     *  behind it (fish do not move backwards).  If this fish cannot
     *  move, <code>nextLocation</code> returns its current location.
     *  @return    the next location for this fish
     **/
    protected Location nextLocation()
    {
        // Create a random number from 0 to 4 and have the fish move locations every time the random number equals 0
        int randNum = RandNumGenerator.getInstance().nextInt(5);

        //test if the random number is equal to zero then check to make sure the location is available and return the new location if it is
        if ( randNum == 0 )
        {
            //return if fish will be moving
            return super.nextLocation();
        }
        else
        {
            //Get another random number to possibly change the direction of the fish because the location did not change
            int newRandNum = RandNumGenerator.getInstance().nextInt(3);
            Direction newDir;
            if ( newRandNum == 0)
            {
                newDir = direction().toLeft();  //get the direction to the left
                changeDirection(newDir);
            }
            else
            {
                newDir = direction().toRight(); //get the direction to the right
                changeDirection(newDir);
            }
            return location();
        }
    }
    
    /** Creates the child of a fish.
     * 
     * @param  loc    location where child fish will go
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        SlowFish child = new SlowFish(grid(), loc, grid().randomDirection());
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }
}