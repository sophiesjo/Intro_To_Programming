package mbsgui;

// Class: GridMBSFileMenuActionHandler
//
// Author: Alyce Brady 
//
// Created on March 23, 2017
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
import edu.kzoo.grid.gui.FileMenuActionHandler;
import edu.kzoo.grid.gui.GridAppFrame;
import edu.kzoo.grid.gui.GridCreationDialog;
import edu.kzoo.grid.gui.GridDataFileHandler;
import edu.kzoo.grid.gui.SteppedGridAppFrame;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  A <code>GridMBSFileMenuActionHandler</code> object 
 *  provides the actions associated with choosing various items
 *  from the File Menu in the MBS program.
 *
 *  @author Alyce Brady
 *  @version March 23, 2017
 **/
public class GridMBSFileMenuActionHandler extends FileMenuActionHandler
{

    /** Constructs a new GridMBSFileMenuActionHandler object.
     *  @param frame  the frame that uses this action handler
     *  @param fileHandler object that can read and write a grid;
     *                     null if this handler should not support file i/o
     */
    public GridMBSFileMenuActionHandler(GridAppFrame frame,
                                           GridDataFileHandler fileHandler)
    {
        super(frame, fileHandler);
    }

    /** Indicates whether this controller supports grid editing.
     *  @return <code>true</code>
     **/
    public boolean supportsGridEditing()
    {
        return true; 
    }

    /** Creates a new empty grid and invokes the grid editor.
     *  (Precondition: <code>supportsGridEditing() == true</code>
     **/
    public void createNewGrid()
    {
        GridCreationDialog gridCreationDialog =
                  GridCreationDialog.makeDimensionsDialog(getParentFrame());
        Grid newGrid = gridCreationDialog.showDialog();
        if (newGrid != null)
            getParentFrame().setGrid(newGrid);
    }

    /** Invokes the appropriate editor.
     **/
    protected void invokeEditor()
    {
        GridMBSEditor editor = new GridMBSEditor(getParentFrame());
    }
    
    /** Enables editing of an existing grid.
     **/
    public void editGrid(Grid grid)
    {
        ((SteppedGridAppFrame)getParentFrame()).stop();
        super.editGrid();
    }

}
