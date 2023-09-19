import edu.kzoo.grid.Grid;
import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.grid.gui.GridAppFrame;

//
// Author: Alyce Brady
//
// This class is based on the College Board's Simulation class,
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

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  A <code>Simulation</code> object controls a simulation of fish
 *  movement in a <code>Grid</code>-based environment.
 *
 *
 *  @author Alyce Brady (based partly on the AP CS MBS Simulation class)
 *  @version 25 April 2019
 **/

public class Simulation
{
    // Instance Variables: Encapsulated data for each simulation object
    private Grid envGrid;
    private GridAppFrame  display;

    /** Constructs a <code>Simulation</code> object for a particular
     *  grid-based environment.
     *  @param env     the environment on which the simulation will run
     *  @param display an object that knows how to display the environment
     **/
    public Simulation(Grid env, GridAppFrame display)
    {
        this.envGrid = env;
        this.display = display;

        // Display the initial state of the simulation.
        this.display.showGrid();
    }

    /** Runs the simulation for as many steps as specified. **/
    public void run(int numSteps)
    {
        for ( int i = 0; i < numSteps; i++ )
        {
            this.step();
        }
    }

    /** Runs through a single step of this simulation. **/
    public void step()
    {
        // Get all the fish in the environment and ask each
        // one to "act" (perform whatever actions it does in a timestep).
        GridObject[] theFishes = this.envGrid.allObjects();
        for ( GridObject fish : theFishes )
        {
            // Is each item a GridObject?  Is it a Fish?
            // We know that Fish know how to act; do all GridObjects
            //   have an act method?
            fish.act();
        }

        // Display the state of the simulation after this timestep.
        this.display.showGrid();
    }

}
