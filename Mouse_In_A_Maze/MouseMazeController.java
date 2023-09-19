// Class: MouseMazeController
//
// Author: Alyce Brady
//
// Created on Feb 29, 2004
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

import edu.kzoo.grid.gui.SteppedGridAppController;

/**
 *  A <code>MouseMazeController</code> object handles the starting, stepping, and
 *  stopping operations initiated by the user using the GUI fir the
 *  mouse-in-a-maze application.
 *
 *  @author Alyce Brady
 *  @version Feb 29, 2004
 **/
public class MouseMazeController extends SteppedGridAppController
{
    private MouseMazeGUI gui;
    private Mouse mouse;

    /** Constructs a MouseMazeController object. */
    public MouseMazeController()
    {
    }

    /** Sets the graphical user interface that the controller will
     *  communicate with as the program progresses.
     **/
    public void setGUI(MouseMazeGUI gui)
    {
        this.gui = gui;
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.SteppedGridAppController#step()
     */
    public void step()
    {
        if ( mouse != null )
            mouse.move();
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.SteppedGridAppController#hasReachedStoppingState()
     */
    public boolean hasReachedStoppingState()
    { 
        // Return true if mouse has found the finish location.
        if ( mouse == null )
            return true;
        return mouse.location().equals(gui.getMaze().getFinishLoc());
    }

    /* (non-Javadoc)
     * @see edu.kzoo.grid.gui.SteppedGridAppController#init()
     */
    public void init()
    {
        Maze maze = gui.getMaze();
        if ( maze == null )
            return;

        // Remove the previous mouse if necessary.
        removeMouse();
            
        // Put the cheese in place if necessary;
        if ( maze.isEmpty(maze.getFinishLoc()) )
            maze.add(new Cheese(), maze.getFinishLoc());
    }

    /** Adds a mouse into the maze.
     *  (Precondition: the maze must already exist.)
     */
    protected void addMouse()
    {
        Maze maze = gui.getMaze();

        // Remove the previous mouse if necessary.
        removeMouse();

        // Put the new mouse in place.
        Class cls = gui.getMouseClass();
        try { mouse = (Mouse) cls.newInstance(); }
        catch (Exception e)
            { throw new IllegalArgumentException(cls.toString() +
                " is not a Mouse type with a no-parameter constructor"); };
        maze.add(mouse, maze.getStartLoc());
    }

    /** Removes a mouse from the maze (if there is one there).
     *  (Precondition: the maze must already exist.)
     */
    protected void removeMouse()
    {
        Maze maze = gui.getMaze();
        if ( mouse != null  && mouse.grid() == maze )
        {
            maze.remove(mouse);
            mouse = null;
        }
    }

}
