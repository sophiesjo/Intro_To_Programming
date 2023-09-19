// Class Fish
//
// Author: Alyce Brady
//
// This class is based on the College Board's Fish class,
// as allowed by the GNU General Public License.
//
// License Information:
//   This class is free software; you can redistribute it and/or modify
//   it under the terms of the GNU General Public License as published by
//   the Free Software Foundation.
//
//   This class is distributed in the hope that it will be useful,
//   but WITHOUT ANY WARRANTY; without even the implied warranty of
//   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//   GNU General Public License for more details.

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
import edu.kzoo.util.Debug;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  A <code>Fish</code> object represents a fish in the Marine Biology
 *  Simulation. Each fish has a unique ID, which remains constant
 *  throughout its life.  A fish also maintains information about its
 *  location and direction in the environment.
 *  This class also calls this fish to act and includes method which will allow the fish
 *  to both die and breed based upon given probabilites.
 *
 *  @author Alyce Brady (based partly on the AP CS MBS Fish class)
 *  @author Sophie Sjogren
 *  @version 24 February 2021
 **/

public class Fish extends GridObject
{
    // Class Variable: Shared among ALL fish
    private static int nextAvailableID = 1;   // next avail unique identifier

    // Instance Variables: Encapsulated data for EACH fish
    private int myId;                  // unique ID for this fish
    private Direction myDir;           // fish's direction
    private Color myColor;             // fish's color
    protected double probOfBreeding;   //likelihood in each timestep
    protected double probOfDying;      //likelihood in each timestep

    // constructors and related helper methods
    /** Constructs a fish at the specified location in a given environment.
     *  The Fish is assigned a random direction and random color.
     *  (Precondition: parameters are non-null; <code>loc</code> is valid
     *  for <code>env</code>.)
     *  @param env    environment (grid) in which fish will live
     *  @param loc    location of the new fish in <code>env</code>
     **/
    public Fish(Grid env, Location loc)
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
    public Fish(Grid env, Location loc, Direction dir)
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
    public Fish(Grid env, Location loc, Direction dir, Color col)
    {
        super(env, loc);    // puts object at location myLoc in environment
        this.myId = nextAvailableID;
        this.nextAvailableID++;
        this.myDir = dir;
        this.myColor = col; 
        this.probOfBreeding = 1.0 / 7.0;    //The fish have a 1 in 7 chance of breeding
        this.probOfDying = 1.0 / 5.0;       //The fish have a 1 in 5 chance of dying
    }

    // accessor methods

    /** Returns this fish's ID.
     *  @return        the unique ID for this fish
     **/
    public int id()
    {
        return this.myId;
    }

    /** Returns this fish's color.
     *  @return        the color of this fish
     **/
    public Color color()
    {
        return this.myColor;
    }

    /** Returns this fish's direction.
     *  @return        the direction in which this fish is facing
     **/
    public Direction direction()
    {
        return this.myDir;
    }

    /** Returns a string representing key information about this fish.
     *  @return  a string indicating the fish's ID, location, and direction
     **/
    public String toString()
    {
        return id() + location().toString() + direction().toString();
    }

    // modifier method
    
    /** Acts for one step in the simulation. The fish will first be tested that they are in the grid
     * then the fish will be tested for breeding which will call upon the breed() method
     * and then the fish will randomly die based upon the probOfDying.
     **/
    public void act()
    {
        // Make sure fish is alive and well in the environment -- fish
        // that have been removed from the environment shouldn't act.
        if ( ! isInAGrid() )
        {
            return;
        }

        
        //test if the fish breed and move if the fish did not
        boolean success = breed();
        if ( success == false )
        {
            move();
        }

        // Create a random decimal which will determine if the fish dies
        double randDecimal = RandNumGenerator.getInstance().nextDouble();

        //test if the random decimal is within the fish's probabilites of dying
        if ( randDecimal < probOfDying )
        {
            die();
        }

        
    }
    

    // internal helper methods
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
            Debug.println("  Does not move.");
    }

    /** Finds this fish's next location.
     *  A fish may move to any empty adjacent locations except the one
     *  behind it (fish do not move backwards).  If this fish cannot
     *  move, <code>nextLocation</code> returns its current location.
     *  @return    the next location for this fish
     **/
    protected Location nextLocation()
    {
        // Get list of neighboring empty locations.
        ArrayList<Location> emptyNbrs = emptyNeighbors();

        // Remove the location behind, since fish do not move backwards.
        Direction oppositeDir = direction().reverse();
        Location locationBehind = grid().getNeighbor(location(),
                oppositeDir);
        emptyNbrs.remove(locationBehind);
        Debug.print("Possible new locations are: " + emptyNbrs.toString());

        // If there are no valid empty neighboring locations, then we're done.
        if ( emptyNbrs.size() == 0 )
            return location();

        // Return a randomly chosen neighboring empty location.
        Random randNumGen = RandNumGenerator.getInstance();
        int randNum = randNumGen.nextInt(emptyNbrs.size());
        return (Location) emptyNbrs.get(randNum);
    }

    /** Finds empty locations adjacent to this fish.
     *  @return    an ArrayList containing neighboring empty locations
     **/
    protected ArrayList<Location> emptyNeighbors()
    {
        // Get all the neighbors of this fish, empty or not.
        ArrayList<Location> nbrs = grid().neighborsOf(location());

        // Figure out which neighbors are empty and add those to a new list.
        ArrayList<Location> emptyNbrs = new ArrayList<Location>();
        for ( Location loc : nbrs )
        {
            if ( grid().isEmpty(loc) )
                emptyNbrs.add(loc);
        }

        return emptyNbrs;
    }

    /** Modifies this fish's direction.
     *  @param  newDir    new direction value
     **/
    protected void changeDirection(Direction newDir)
    {
        // Change direction.
        myDir = newDir;
    }

    /** Removes the fish from the environment.
     **/
    protected void die()
    {
        //Call the remove method
        removeFromGrid();
    }

    /** Adds new born fish to the environment. Test to make sure the fish can breed and then
     * test for empty neighbors, if both the loops do not execute then the method will loop through
     * the empty locations and add fish.
     **/
    protected boolean breed()
    {
        //generate random decimal to determine if fish will breed
        double randDecimal = RandNumGenerator.getInstance().nextDouble();

        //test if the random decimal is greater than the probability of breeding and return false if it is
        if ( randDecimal > probOfBreeding )
        {
            return false;
        }
        
        //Get all the empty neighbors of the fish
        ArrayList<Location> emptySpots = emptyNeighbors();
        
        //If there are no empty neighbors then return false
        if ( emptySpots.size() == 0 )
        {
            return false;
        }

        //Loop through the locations of empty neighbors and add fish to those locations
        for ( Location loc : emptySpots )
        {
            generateChild(loc);
        }
        //Return true because the fish was able to breed
        return true;

    }
    
    /** Creates the child of a fish.
     **/
    protected void generateChild(Location loc)
    {
        //Create fish
        Fish child = new Fish(grid(), loc, grid().randomDirection(), myColor);
        //Enter debugging notes
        Debug.println( "New Fish created: " + child.toString());
    }

}
