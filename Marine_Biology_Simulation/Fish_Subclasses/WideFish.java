
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
 *  This class creates a wide fish which will appear as a round fish and can be any given color.
 *  This fish functions similarly to the fish in the Fish class.
 *  This class also has a method that will create the child of a fish specific to this class.
 *
 *  @author Sophie Sjogren
 *  @version 24 February 2021
 **/

public class WideFish extends Fish
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
    public WideFish(Grid env, Location loc)
    {
        this(env, loc, env.randomDirection(), NamedColor.getRandomColor());
    }

    /** Constructs a fish at the specified location and direction in a
     *  given environment.  The Fish is assigned a random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     *  @param dir    direction the new fish is facing
     **/
    public WideFish(Grid env, Location loc, Direction dir)
    {
        this(env, loc, dir, NamedColor.getRandomColor());
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
    public WideFish(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc, dir, col);      //pass parameters to fish constructor
    }

    // Accessor (or Observer)  methods

    // Modifier methods
    /** Creates the child of a WideFish.
     * 
     * @param  loc    location where child fish will go
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        WideFish child = new WideFish(grid(), loc, grid().randomDirection());
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }
}