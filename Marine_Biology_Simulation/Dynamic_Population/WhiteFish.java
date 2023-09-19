
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
 *  This class creates a fish in the simulation that appears white and can give information about the fish's age.
 *  The age of the white fish increase once every time it steps. 
 *  This class also has a method that will create the child of a fish specific to this class.
 *
 *  @author Sophie Sjogren
 *  @version 24 February 2021
 **/

public class WhiteFish extends Fish
{
    // Class Variables: Shared among ALL objects of this class

    // Instance Variables: Encapsulated data for EACH object of this class
    int fishAge;
    
    // Constructors
    // constructors and related helper methods
    /** Constructs a fish at the specified location in a given environment.
     *  The Fish is assigned a random direction and random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     **/
    public WhiteFish(Grid env, Location loc)
    {
        this(env, loc, env.randomDirection(), NamedColor.WHITE);
    }

    /** Constructs a fish at the specified location and direction in a
     *  given environment.  The Fish is assigned a random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     *  @param dir    direction the new fish is facing
     **/
    public WhiteFish(Grid env, Location loc, Direction dir)
    {
        this(env, loc, dir, NamedColor.WHITE);
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
    public WhiteFish(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc, dir, col);      //pass parameters to fish constructor
        this.fishAge = RandNumGenerator.getInstance().nextInt(10);
    }

    // Accessor (or Observer)  methods
    /** Returns a string representing key information about this fish.
     *  @return  a string indicating the fish's ID, location, and direction
     **/
    public String toString()
    {
        return super.toString() + " (age: " + this.fishAge + ")";
    }
    
    // Modifier methods
    /** Acts for one step in the simulation.
     **/
    public void act()
    {
        // Make sure fish is alive and well in the environment -- fish
        // that have been removed from the environment shouldn't act.
        super.act();
        this.fishAge++;
    }
    

    /** Creates the child of a fish.
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        WhiteFish child = new WhiteFish(grid(), loc, grid().randomDirection());
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }
}