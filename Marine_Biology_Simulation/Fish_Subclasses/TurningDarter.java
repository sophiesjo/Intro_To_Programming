
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
 * This class creates fish similar to the darter fish, but the methods in this class allow the fish
 * to move either right or left. They have a 1 in 10 chance of moving.
 *
 * @author Sophie Sjogren
 * @version 25 February 2021
 */
public class TurningDarter extends DarterFish
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
    public TurningDarter(Grid env, Location loc)
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
    public TurningDarter(Grid env, Location loc, Direction dir)
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
    public TurningDarter(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc, dir, col);      //pass parameters to fish constructor
        this.probOfTurning = 1.0 / 10.0;    //a 1 in 10 chance of turning
    }
    
    /** Acts for one step in the simulation. The fish will randomly change directions to left or right .
     **/
    public void move()
    {
        // Create a random decimal which will determine if the fish turns
        double randDecimal = RandNumGenerator.getInstance().nextDouble();

        //test if the random decimal is within the fish's probabilites of turning
        if ( randDecimal < probOfTurning )
        {
            //Create a random int that will randomly decide which direction to turn
            int randInt = RandNumGenerator.getInstance().nextInt(2);
            Direction newDir;
            if ( randInt == 0)
            {
                newDir = direction().toLeft();  //get the direction to the left
                Debug.println( "Fish changed direction to the left.");
            }
            else
            {
                newDir = direction().toRight(); //get the direction to the right
                Debug.println( "Fish changed direction to the right.");
            }
            changeDirection(newDir);
        }

        
    }
    
    /** Creates the child of a fish.
     * 
     * @param  loc    location where child fish will go
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        TurningDarter child = new TurningDarter(grid(), loc, grid().randomDirection());
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }
}
