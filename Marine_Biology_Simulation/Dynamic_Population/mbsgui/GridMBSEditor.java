package mbsgui;

// Class GridMBSEditor
//
// Author: Alyce Brady
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

import edu.kzoo.grid.gui.GridAppFrame;
import edu.kzoo.grid.gui.GridEditor;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  The <code>GridMBSEditor</code> class provides a window in which
 *  to add MBS objects to the environment.
 *
 *  @author Alyce Brady
 *  @version 23 March 2017
 **/
public class GridMBSEditor extends GridEditor
{
  // constructor

    /** Constructs an empty ObstCourseEditor window.
     *    @param frame  the frame that invoked this grid editor
     **/
    public GridMBSEditor(GridAppFrame frame)
    {
        super(frame);

        // Tool tips should show object info, not just location.
        constructWindowContents();
        getDisplay().makeToolTipsReportObject();
    }

}


