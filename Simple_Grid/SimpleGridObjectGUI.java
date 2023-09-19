// Class: SimpleGridObjectGUI
//
// Author:  Alyce Brady
//
// Created on 8 January 2009
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

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.gui.GridAppFrame;
import edu.kzoo.grid.gui.nuggets.BasicHelpMenu;
import edu.kzoo.grid.gui.nuggets.MinimalFileMenu;

/**
 *  Simple Grid Object Programming Project:<br>
 *
 *  A SimpleGridObjectGUI object represents the graphical user interface for
 *  the Simple Grid Object application.
 *
 *  @author Alyce Brady
 *  @author Sophie Sjogren
 *  @version 10 January 2021
 */
public class SimpleGridObjectGUI extends GridAppFrame
{
    // Specify dimensions of grid display and individual cell size.
    private static final int DISPLAY_WIDTH = 600;
    private static final int DISPLAY_HEIGHT = 600;
    private static final int MIN_CELL_SIZE = 10;

    // Specify the background color to use for the grid.
    private static final Color BACKGROUND_COLOR = new Color(0, 155, 255);

    /** Constructs a new SimpleGridObjectGUI object (a graphical user interface
     *  for a Simple Grid Object application)
     *  @param grid    the grid to be displayed by this GUI
     *  @param studentName    the name of the student creating this program
     *  @param assistants     the name of anyone who helped with this program
     *  @param date           the date when the program was finished
     */
    public SimpleGridObjectGUI(BoundedGrid grid, String studentName,
                               String assistants, String date)
    {
        // Create graphical user interface capable of displaying
        // a grid containing simple grid objects.
        includeMenu(new MinimalFileMenu());
        includeMenu(new BasicHelpMenu("SimpleGridObject",
                studentName, assistants, date));
        includeSpeedSlider();
        constructWindowContents("SimpleGridObject", BACKGROUND_COLOR, 
                DISPLAY_WIDTH, DISPLAY_HEIGHT, MIN_CELL_SIZE);
        setGrid(grid);
        showGrid();
      }
}
