package mbsgui;

// Class MBSGUI
//
// Author: Alyce Brady
//
// This class is based on the College Board's MBSGUIFrame class,
// as allowed by the GNU General Public License.  MBSGUIFrame
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
import edu.kzoo.grid.gui.ActiveGridAppController;
import edu.kzoo.grid.gui.ControlButton;
import edu.kzoo.grid.gui.EnabledDisabledStates;
import edu.kzoo.grid.gui.GridDataFileHandler;
import edu.kzoo.grid.gui.SteppedGridAppController;
import edu.kzoo.grid.gui.SteppedGridAppFrame;
import edu.kzoo.grid.gui.nuggets.BasicGridFileMenu;
import edu.kzoo.grid.gui.nuggets.BasicHelpMenu;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  The <code>MBSGUI</code> is a graphical user interface for the
 *  Marine Biology Simulation.  The user interface consists of a window
 *  containing the environment display (objects in a grid), and a menu and
 *  set of buttons that allows the user to create a new grid, modify the
 *  contents of the grid, or step through the simulation.  Users can also
 *  read in an initial configuration for the environment from a file, or
 *  write the current contents and configuration of the environment to a
 *  file.
 *
 *  @author Alyce Brady
 *  @version 25 April 2019
 **/
public class MBSGUI extends SteppedGridAppFrame
{
    private static final boolean REDISPLAY = true;

    private GridMBSFileMenuActionHandler menuActionHandler;

  // constructors and initialization methods

    /** Creates a window in which to run a marine biology simulation.
     **/
    public MBSGUI()
    {
        // This is an active grid frame with Step, N Steps,
        // Run and Stop buttons, but not a Restart button.
        super(new ActiveGridAppController(), REDISPLAY);
        SteppedGridAppController controller = getController();

        GridDataFileHandler fileHandler = new GridDirObjDataFileHandler();
        menuActionHandler = new GridMBSFileMenuActionHandler(this,
                                                                fileHandler);
        includeMenu(new BasicGridFileMenu(this, menuActionHandler,
                                          fileHandler));
        includeMenu(new BasicHelpMenu("Marine Biology Simulation",
                "Your Name Here",
                "with assistance from ???some people???;" +
                " based on a program originally developed by Alyce Brady",
                "25 April 2019"));
        includeControlComponent(
              new ControlButton(this, "New Grid", REDISPLAY)
                  { public void act() 
                    {   menuActionHandler.createNewGrid();
                    }},
              EnabledDisabledStates.NEEDS_APP_WAITING);
        includeControlComponent(
              new ControlButton(this, "Manually Populate Environment", REDISPLAY)
                  { public void act() { invokeEditor(); }},
              EnabledDisabledStates.NEEDS_GRID_AND_APP_WAITING);
        includeStepOnceButton();
        includeStepNTimesButton();
        includeRunButton();
        includeStopButton(REDISPLAY);
        includeSpeedSlider();

        constructWindowContents("Marine Biology Simulation", null, 0, 0, 0);

        // Tool tips should show object info, not just location.
        getDisplay().makeToolTipsReportObject();
    }

  // redefined methods from SteppedGridAppFrame

    /** Sets the grid being displayed (and displays it).
     *    @param grid the Grid to display
     **/
    public void setGrid(Grid grid)
    {
        super.setGrid(grid);
        showGrid();
    }

    /** Invokes the MBS grid editor for placing marine biology objects.
     *    
     */
    protected void invokeEditor()
    {
        new GridMBSEditor(this);
    }

}


