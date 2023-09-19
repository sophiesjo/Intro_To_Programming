package mbsgui;

// Class GridDirObjDataFileHandler
//
// Author: Alyce Brady
//
// This class is based on the College Board's MBSDataFileHandler class,
// as allowed by the GNU General Public License.  MBSDataFileHandler
// was a black-box class within the AP(r) CS Marine Biology Simulation
// case study, used for four years in the early 2000's.
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

import java.io.PrintWriter;

import edu.kzoo.grid.Grid;
import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.grid.gui.GridPkgFactory;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 *  Grid GUI Support Package:<br>
 *
 *  A <code>GridDirObjDataFileHandler</code> object reads and writes
 *  information about a grid and the objects in it to and from a data file.
 *  It assumes that all objcts in the grid have constructors that expect
 *  three parameters: the grid in which the object resides and acts, its
 *  initial location in the grid, and its direction.
 *
 *  Each line representing an object in the grid should be in the following
 *  format:
 *  <pre>
 *     class row-pos col-pos dir
 *  </pre>
 *  where <code>class</code> is a string indicating the class name of
 *  the object ("Fish" for example), <code>row-pos</code> and
 *  <code>col-pos</code> are integers indicating its row and column
 *  position, and <code>dir</code> is a compass direction (either
 *  a word like North or Northeast or a number of degrees).
 *
 *  @author Alyce Brady
 *  @version 4 May 2019
 *  @see GridPkgFactory
 *
 **/
public class GridDirObjDataFileHandler extends GridBasicDataFileHandler
{

    /** Reads in information about an object in the grid (e.g., location
     *  and direction) and constructs the object.
     *  @param cls       class of new grid object
     *  @param grid      grid in which location must be valid
     *  @throws RuntimeException   if invalid location information is read
     */
    protected void constructObject(Class cls, Grid grid)
    {
        // Read the object's location.
        Location loc = readLocation(grid);

        // Read the object's direction.
        Direction dir = readDirection();

        // Construct the object at the specified location.
        GridPkgFactory.constructGridObject(cls, grid, loc, dir);
    }

    /** Reads in a new Direction object.  The direction could be either an
     *  integer or a named compass direction recognized by the Direction
     *  constructor.
     *  @return the newly created direction object
     *  @throws RuntimeException   if invalid direction information is read
     **/
    protected Direction readDirection()
    {
        // Direction could be either an integer or a named compass
        // direction.
        String dirName = null;
        Direction dir = null;

        try
        {	// Attempt to interpret token as direction in degrees.
            dirName = readString();
            int degrees = Integer.parseInt(dirName);
            dir = new Direction(degrees);
        } 
        catch (Exception e)
        {
            try 
            {
            	// If that didn't work, try to interpret as a named direction.
                dir = new Direction(dirName);
            }
            catch (Exception e2)
            {
                // Convert reading exceptions to RuntimeException.
                throw new RuntimeException("Error reading direction (line " 
                                       + inputReader.getLineNumber() + ")");
            }
        }

        return dir;
    }

    /** Writes information about an object in the grid (e.g., location).
     *  Can be redefined in subclasses to write additional information,
     *  such as direction or color.
     *  @param out      where to write information
     *  @param obj      object whose information should be written
     */
    protected void writeGridObject(PrintWriter out, GridObject obj)
    {
        super.writeGridObject(out, obj);

	Class<? extends GridObject> objClass = obj.getClass();
        String err = "Trying to write direction info for " + objClass +
                     "class; cannot access or invoke direction method.";
        try
        {
            Method dirMethod = objClass.getMethod("direction", new Class[0]);
            Direction dir = (Direction)dirMethod.invoke(obj, new Object[0]);
            out.print(" " + dir);
        }
        catch (NoSuchMethodException e)
	{   throw new IllegalArgumentException(err); }
        catch (InvocationTargetException e)
	{   throw new IllegalArgumentException(err); }
        catch (IllegalAccessException e)
	{   throw new IllegalArgumentException(err); }
    }

}
