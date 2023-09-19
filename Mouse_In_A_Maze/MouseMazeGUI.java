// Class: MouseMazeGUI
//
// Author: Alyce Brady
//      with assistance from:  everyone in CS 110 
//
// Created on Feb 25, 2004
// Modifications:
//      Date    Name    Reason
//      ----    ----    ------
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
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;

import edu.kzoo.grid.gui.ControlButton;
import edu.kzoo.grid.gui.EnabledDisabledStates;
import edu.kzoo.grid.gui.SteppedGridAppFrame;

/**
 *  Mouse Maze GUI:<br>
 *
 *  A MouseMazeGUI object represents a window with control
 *  buttons and a display for a Mouse-in-a-Maze project.
 *  Most of the functionality is provided in the ActiveGridFrame
 *  superclass.
 *  The GUI is just a prototype at this time.
 *
 *  @author Alyce Brady
 *  @version Feb 25, 2004
 **/
public class MouseMazeGUI extends SteppedGridAppFrame
{
    // Specify the dimensions of the grid display.
    private static final int DISPLAY_WIDTH = 400;
    private static final int DISPLAY_HEIGHT = 400;

    // Specify the grid's background color.
    private static final Color BG_COLOR = Color.WHITE;

    // Specify the minimum height or width for any cell.
    private static final int MIN_CELL_SIZE = 10;

    public static final boolean REDISPLAY = true;

    private JComboBox<MouseChoice> mouseClassChooser = null;

    /** Constructs a new MouseMazeGUI object.
     *    @param mouseTypes a collection of <code>Class</code> objects
     *                      representing the mouse classes supported by
     *                      the program
     */
    public MouseMazeGUI(Collection<Class> mouseTypes)
    {
        super(new MouseMazeController(), REDISPLAY);
        includeMenu(new MazeFileMenu(this, new MazeDataFileHandler()));
        includeControlComponent(makeMouseClassChooser(mouseTypes), 
                    EnabledDisabledStates.NEEDS_GRID_AND_APP_WAITING);
        includeControlComponent(
                    new ControlButton(this, "Add mouse", REDISPLAY)
                    { public void act() { addMouseToGrid(); } },
                    EnabledDisabledStates.NEEDS_GRID_AND_APP_WAITING,
                    REDISPLAY);
        includeStepOnceButton();
        includeRunButton();
        includeStopButton(REDISPLAY);
        includeSetResetButton("Restart",
                    EnabledDisabledStates.NEEDS_GRID_AND_APP_WAITING,
                    REDISPLAY);
        constructWindowContents("Mouse in a Maze", BG_COLOR,
                                DISPLAY_WIDTH, DISPLAY_HEIGHT, MIN_CELL_SIZE);
        ((MouseMazeController)getController()).setGUI(this);
        getController().init();
        showGrid();
    }

    /** Returns the maze controlled by this graphical user interface. **/
    public Maze getMaze()
    {
        return (Maze) getGrid();
    }

    /** Creates the drop-down menu for choosing a type of mouse.
     *    @param mouseTypes a collection of <code>Class</code> objects
     *                      representing the mouse classes supported by
     *                      the program
     **/
    protected JComboBox<MouseChoice> makeMouseClassChooser(Collection<Class> mouseTypes)
    {
        mouseClassChooser = new JComboBox<MouseChoice>();
        Iterator it = mouseTypes.iterator();
        while ( it.hasNext() )
             mouseClassChooser.addItem(new MouseChoice((Class)it.next()));
        return mouseClassChooser;
    }

    /** Returns the type of mouse indicated by the drop-down menu.
     *    @return the class of the type of mouse currently selected in
     *            the drop-down menu for choosing mouse types
     **/
    protected Class getMouseClass()
    {
        return ((MouseChoice)mouseClassChooser.getSelectedItem()).mouseClass();
    }

    protected void addMouseToGrid()
    {
        ((MouseMazeController)getController()).addMouse();
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.SteppedGridAppFrame#step()
     */
    public void step()
    {
        super.step();
        showGrid();
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.SteppedGridAppFrame#initialize()
     */
    public void initialize()
    {
        super.initialize();
        showGrid();
    }

    /** Nested class  used to hold the per-item information 
     *  for the entries in a combo box of mouse type
     *  choices. Each item represents a mouse class.
     */
    static protected class MouseChoice 
    {
        private Class cls;
        
        public MouseChoice(Class cl) { cls = cl; }
        public Class mouseClass() { return cls; }
        public String toString() { return cls.getName(); }
    }

}
