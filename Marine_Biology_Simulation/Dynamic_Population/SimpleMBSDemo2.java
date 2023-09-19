// Class SimpleMBSDemo2
//
// Author: Alyce Brady
//
// This class is based on the College Board's SimpleMBSDemo2 class,
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

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Location;

import mbsgui.SimpleMBSDisplay;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  The <code>SimpleMBSDemo2</code> class provides a main method that creates
 *  a simulation of a number of fish swimming in a bounded environment,
 *  such as a pond, lake, or bay.  It also creates a simple window in which
 *  to view the environment after each timestep in the simulation. This
 *  version of the MBS demo uses an object of the <code>Simulation</code> class.
 *
 *  @author Alyce Brady  (based on the AP CS SimpleMBSDemo2)
 *  @version 23 March 2017
 **/

public class SimpleMBSDemo2
{
    // Specify number of rows and columns in environment.
    private static final int ENV_ROWS = 10;      // rows in environment
    private static final int ENV_COLS = 10;      // columns in environment

    // Specify how many timesteps to run the simulation.
    private static final int NUM_STEPS = 15;     // number of timesteps

    /** Start the Marine Biology Simulation program.
     *  The String arguments (args) are not used in this application.
     **/
    public static void main(String[] args)
    {
        // Construct an empty environment grid and put several fish in it.
        BoundedGrid grid = new BoundedGrid(ENV_ROWS, ENV_COLS);
        Fish f1 = new Fish(grid, new Location(2, 2));
        Fish f2 = new Fish(grid, new Location(2, 3));
        Fish f3 = new Fish(grid, new Location(5, 8));

        // Construct an object that knows how to draw an environment, and
        // tell it which environment (grid) it will draw.  This very simple
        // graphical user interface includes a display area, a speed
        // slider, and minimal File and Help menus.
        SimpleMBSDisplay display = new SimpleMBSDisplay();
        display.setGrid(grid);

        // Construct the simulation object.  It needs to be told about
        // the environment (grid + objects in it) and the display.
        Simulation sim = new Simulation(grid, display);

        // Run the simulation for the specified number of steps.
        sim.run(NUM_STEPS);
    }
}

