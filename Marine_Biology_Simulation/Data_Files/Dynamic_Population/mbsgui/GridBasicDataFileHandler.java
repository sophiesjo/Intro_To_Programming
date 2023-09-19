package mbsgui;

// Class GridBasicDataFileHandler
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

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import edu.kzoo.grid.Grid;
import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.Direction;
import edu.kzoo.grid.gui.GridDataFileHandler;
import edu.kzoo.grid.gui.GridPkgFactory;

/**
 *  Grid GUI Support Package:<br>
 *
 *  A <code>GridBasicDataFileHandler</code> object reads and writes information
 *  about a grid and the objects in it to and from a data file.
 *
 *  <p>
 *  The <code>GridBasicDataFileHandler</code> object assumes that the objcts in
 *  the grid have constructors that expect two parameters: the
 *  grid in which the object resides and acts, and its initial location in
 *  the grid.
 *
 *  <p>
 *  The first line of the input file should be either
 *  <pre>
 *     bounded <i>rows columns</i>
 *  </pre>
 *  or
 *  <pre>
 *     unbounded
 *  </pre>
 *  where <i>rows</i> and <i>columns</i> are integers indicating the
 *  number of rows and columns in a bounded grid. (The internal
 *  representation used for the grid is whatever has been set as the
 *  default bounded or unbounded grid in <code>GridPkgFactory</code>.)
 *
 *  Each line after the first represents an object in the grid
 *  and should be in the following format:
 *  <pre>
 *     class row-pos col-pos
 *  </pre>
 *  where <code>class</code> is a string indicating the class name of
 *  the object ("Fish" for example), and <code>row-pos</code> and
 *  <code>col-pos</code> are integers indicating its row and column
 *  position.
 *
 *  @author Alyce Brady (based on the AP CS MBSDataFileHandler)
 *  @version 4 May 2019
 *  @see GridPkgFactory
 *
 **/
public class GridBasicDataFileHandler implements GridDataFileHandler
{
    // Encapsulated data used to read/write info from a file
    protected LineNumberReader inputReader;    // buffered input w/ line number
    protected StringTokenizer tokenizer;       // parses tokens from a line

    /** Reads information about a grid (grid + objects) from an
     * initial configuration data file.
     *  @param  file       java.io.File object from which to read
     *  @throws java.io.FileNotFoundException if file cannot be opened
     *  @throws RuntimeException   if invalid information is read from file
     **/
    public Grid readGrid(File file)
        throws java.io.FileNotFoundException
    {
        // Open the file for reading.
        inputReader = new LineNumberReader(new FileReader(file));

        // Read grid dimensions and construct it.
        Grid grid = buildGrid();

        // Read objects until there are no more.
        while ( readObject(grid) )
            ;

        return grid;
    }

    /** Read grid specifications and construct it.
     *  @throws RuntimeException  if invalid grid information is read
     **/
    protected Grid buildGrid()
    {
        // Read in grid type.
        String gridType;
        try
        {
            gridType = readString();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error reading grid type (line "
                                   + inputReader.getLineNumber() + ")");
        }

        if ( gridType.equals("bounded") )
        {
            int numRows, numCols;

            try
            {
                // Read the grid dimensions.
                numRows = readInt();
                numCols = readInt();
            }
            catch (Exception e)
            { 
                throw new RuntimeException("Error reading grid dimensions "
                                       + "(line " 
                                       + inputReader.getLineNumber() + ")");
            }

            // Validate grid dimensions.
            if ( numRows <= 0 || numCols <= 0 )
                throw new RuntimeException("Grid dimensions must be positive (line "
                                       + inputReader.getLineNumber() + ")");

            // Construct the appropriate bounded grid.
            Class gridClass = GridPkgFactory.getDefaultBoundedGridClass();
            return GridPkgFactory.constructGrid(gridClass, numRows, numCols);
        }
        else if ( gridType.equals("unbounded") )
        {
            // Construct the appropriate unbounded grid.
            Class gridClass = GridPkgFactory.getDefaultUnboundedGridClass();
            return GridPkgFactory.constructGrid(gridClass);
        }
        else
            throw new RuntimeException("File must begin with bounded or unbounded (line "
                                   + inputReader.getLineNumber() + ")");
    }


    /** Reads information about the next object and constructs it using
     *  the <code>GridPkgFactory</code> class. (The factory must know how to
     *  construct objects with the class name read in from the file.)
     *  The object adds itself to the grid as it is constructed.
     *  @param grid   the grid in which object should be created
     *  @return <code>true</code> if object was successfully read,
     *          <code>false</code> at EOF
     *  @throws RuntimeException   if invalid location information is read
     **/
    protected boolean readObject(Grid grid)
    {
        String className = null;    // class name of object read
        Class cls;                  // class for object read

        try
        {
            // Read the class name first (a null string means end of file).
            className = readString();
            if ( className == null )
            {
                // If end-of-file encountered, close file.
                inputReader.close();
                return false;
            }
            cls = Class.forName(className);

            // Now read information about the object and construct it.
            constructObject(cls, grid);
        }
        catch (ClassNotFoundException e)
        {
              throw new RuntimeException("Cannot find class named "
                                     + className + " (line " 
                                     + inputReader.getLineNumber() + ")");
        }
        catch (java.io.IOException e)
        {
            // Convert i/o exceptions to RuntimeException?
            throw new RuntimeException("Error reading object (line " 
                                   + inputReader.getLineNumber() + ")"); 
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error constructing object (line " 
                                   + inputReader.getLineNumber() + ")"); 
        }

        return true;
    }

    /** Reads in information about an object in the grid (e.g., location)
     *  and constructs the object.  Can be redefined in subclasses to read
     *  additional information, such as direction or color.
     *  @param cls       class of new grid object
     *  @param grid      grid in which location must be valid
     *  @throws RuntimeException   if invalid location information is read
     */
    protected void constructObject(Class cls, Grid grid)
    {
        // Now read the object's location.
        Location loc = readLocation(grid);

        // Construct the object at the specified location.
        GridPkgFactory.constructGridObject(cls, grid, loc);
    }

    /** Reads in a Location object (must be a valid location in
     *  <code>grid</code>).
     *  @param grid      grid in which location must be valid
     *  @return  the newly created Location object
     *  @throws RuntimeException   if invalid location information is read
     **/
    protected Location readLocation(Grid grid)
    {
        int row, col;
        try
        {
            // Read the location.
            row = readInt();
            col = readInt();
        }
        catch (Exception e)
        {
            // Convert reading exceptions to RuntimeException.
            throw new RuntimeException("Error reading location (line " 
                                   + inputReader.getLineNumber() + ")"); 
        }
        Location loc = new Location(row, col);

        // Verify that location is valid in this grid.
        if ( grid.isValid(loc) )
            return loc;
        else
            throw new RuntimeException("Location " +  loc +
                                   " is not valid in this grid (line " 
                                   + inputReader.getLineNumber() + ")"); 
    }

    /** Returns the next token in the file as an integer.
     *  @return    an int containing the next number in the file
     *  @throws    java.io.EOFException if EOF
     *  @throws    java.lang.NumberFormatException if next token is not an int
     *  @throws    java.io.IOException if another type of input error occurs
     **/
    protected int readInt()
        throws java.io.IOException
    {
        // Read in number as string, then convert to integer.
        String token = readString();
        if ( token == null )
            throw new java.io.EOFException();
        return Integer.parseInt(token);
    }

    /** Returns the next token in the file as a string.
     *  @return     a String containing the next token in the file; or null
     *              if end of file is encountered
     *  @throws     java.io.IOException if an input error occurs
     **/
    protected String readString()
        throws java.io.IOException
    {
        // Read in a new line if there are no more tokens in current line.
        while ( tokenizer == null || ! tokenizer.hasMoreTokens() )
        {
            String line = inputReader.readLine();

            // Did we encounter end of file?
            if ( line == null )
                return null;
            tokenizer = new StringTokenizer(line);
        }

        // Return next token.
        return tokenizer.nextToken();
    }

    /** Writes information about a grid into a data file,
     *  including the grid dimensions and the class names and
     *  locations of the objects in the grid.
     *  @param  grid   grid to write to file
     *  @param  file   java.io.File object to which to write
     *  @throws java.io.FileNotFoundException if file cannot be opened
     *  @throws java.io.IOException if error writing to file
     **/
    public void writeGrid(Grid grid, File file)
        throws java.io.IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(file));

        // Save grid type (and dimensions if necessary).
        if (grid.numRows() == Grid.UNBOUNDED ||
                grid.numCols() == Grid.UNBOUNDED)
            out.println("unbounded");
        else
            out.println("bounded " + grid.numRows() + " " + grid.numCols());

        // Save the objects in the grid.
        GridObject[] objList = grid.allObjects();
        for ( GridObject obj : objList )
        {
            String className = obj.getClass().getName();
            out.print(className);
            writeGridObject(out, obj);
            out.println();
        }
        out.close();
    }

    /** Writes information about an object in the grid (e.g., location).
     *  Can be redefined in subclasses to write additional information,
     *  such as direction or color.
     *  @param out      where to write information
     *  @param obj      object whose information should be written
     */
    protected void writeGridObject(PrintWriter out, GridObject obj)
    {
        int row = obj.location().row();
        int col = obj.location().col();
        out.print(" " + row + " " + col);
    }

}
