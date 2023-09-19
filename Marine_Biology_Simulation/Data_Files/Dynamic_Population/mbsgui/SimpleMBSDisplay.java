package mbsgui;

// Class SimpleMBSDisplay
//
// Author: Alyce Brady
//
// This class is based on the College Board's SimpleMBSDisplay class,
// as allowed by the GNU General Public License.  SimpleMBSDisplay
// is a black-box class within the AP(r) CS Marine Biology Simulation
// case study (see www.collegeboard.com/ap/students/compsci).
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

import edu.kzoo.grid.Grid;
import edu.kzoo.grid.gui.GridAppFrame;
import edu.kzoo.grid.gui.nuggets.MinimalFileMenu;
import edu.kzoo.grid.gui.nuggets.BasicHelpMenu;
import edu.kzoo.grid.display.DisplayMap;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  A <code>SimpleMBSDisplay</code> is a simple graphical user interface for
 *  the Marine Biology Simulation.  The user interface consists of a window
 *  containing the environment display (objects in a grid), with a slider for
 *  speeding up or slowing down the simulation.
 *
 *  @author Alyce Brady
 *  @version 25 April 2019
 **/
public class SimpleMBSDisplay extends GridAppFrame
{
    private static final boolean REDISPLAY = true;
    private GridMBSFileMenuActionHandler menuActionHandler;

  // constructors and initialization methods

    /** Creates a window in which to run a marine biology simulation.
     **/
    public SimpleMBSDisplay()
    {
        super();

        // Create a minimal File menu (with Quit Option) and a minimal Help menu.
        boolean includeQuitOption = true;
        includeMenu(new MinimalFileMenu(includeQuitOption));
        includeMenu(new BasicHelpMenu("Marine Biology Simulation",
                "Jamie",
                "with assistance from Alyce Brady",
                "25 April 2019"));

        // Add a display that knows how to draw a fish, with a speed slider. 
        DisplayMap.associate("Fish", new FishDisplay());        
        includeSpeedSlider();

        // Construct the window and its contents.
        constructWindowContents("Marine Biology Simulation", null, 0, 0, 0);

        // Tool tips should show object info, not just location.
        getDisplay().makeToolTipsReportObject();
    }


  // redefined methods from GridAppFrame

    /** Sets the Grid being displayed.
     *  (Precondition: grid is not null.)
     *    @param grid the Grid to display
     **/
    public void setGrid(Grid grid)
    {
        super.setGrid(grid);
        showGrid();
    }

}


