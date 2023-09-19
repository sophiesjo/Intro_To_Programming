// Class MazeDataFileHandler
//
// Author: Alyce Brady
//
// This class is based on the College Board's MBSDataFileHandler class,
// as allowed by the GNU General Public License.  MBSDataFileHandler
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

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

import edu.kzoo.grid.ColorBlock;
import edu.kzoo.grid.Grid;
import edu.kzoo.grid.Location;
import edu.kzoo.grid.gui.GridDataFileHandler;

/**
 *  Mouse in a Maze Program:<br>
 *
 *  A <code>MazeDataFileHandler</code> object reads and writes
 *  information about a Maze to and from a data file, including
 *  the maze's dimensions, the starting and finish locations,
 *  and the locations of the walls.
 *
 *  <p>
 *  The first line of the input file should contain the dimensions of
 *  the maze.  Subsequent lines should specify the locations of the
 *  starting location, the finishing location, and the locations of
 *  the walls.  The file format is:
 *  <pre>
 *     rows columns
 *     row-pos col-pos          (starting location)
 *     row-pos col-pos          (finishing location)
 *     row-pos col-pos          (walls ... )
 *     ...
 *     row-pos col-pos
 *  </pre>
 *  where <code>rows</code> and <code>columns</code> are integers
 *  indicating the number of rows and columns in the maze,
 *  the first <code>row-pos</code> and <code>col-pos</code> are integers
 *  indicating the row and column position of the starting location,
 *  the second <code>row-pos</code> and <code>col-pos</code> are integers
 *  indicating the row and column position of the finishing location,
 *  and the rest of the <code>row-pos</code> and <code>col-pos</code> lines
 *  indicate the row and column positions of walls in the maze.
 *
 *  @author Alyce Brady (based on MBSDataFileHandler)
 *  @version 29 February 2004
 **/
public class MazeDataFileHandler implements GridDataFileHandler
{
    // Encapsulated data used to read/write maze info from a file
    private LineNumberReader inputReader;  	// buffered input w/ line number
    private StringTokenizer tokenizer;   	// parses tokens from a line


    /** Reads information about a maze from an initial configuration
     *  data file.
     *  @param  file       java.io.File object from which to read
     *  @throws java.io.FileNotFoundException if file cannot be opened
     *  @throws RuntimeException   if invalid information is read from file
     **/
    public Grid readGrid(File file)
        throws java.io.FileNotFoundException
    {
        // Open the file for reading.
        inputReader = new LineNumberReader(new FileReader(file));

        // Read maze dimensions and construct maze.
        Maze maze = buildMaze();

        // Read starting and ending locations.
        Location startLoc = readLocation(maze);
        if ( startLoc == null )
            throw new RuntimeException("Maze has no starting location (line "
                                       + inputReader.getLineNumber() + ")");
        else
            maze.setStartLoc(startLoc);

        Location endLoc = readLocation(maze);
        if ( endLoc == null )
            throw new RuntimeException("Maze has no finishing location (line "
                                       + inputReader.getLineNumber() + ")");
        else
            maze.setFinishLoc(endLoc);

        // Read wall locations.
        readWalls(maze);

        return maze;
    }

    /** Reads maze specifications and constructs maze.
     *  @throws RuntimeException  if invalid maze information is read
     **/
    private Maze buildMaze()
    {
        int mazeRows, mazeCols;

        try
        {
            // Read the maze dimensions.
            mazeRows = readInt();
            mazeCols = readInt();
        }
        catch (Exception e)
        { 
            throw new RuntimeException("Error reading maze dimensions "
                                       + "(line " 
                                       + inputReader.getLineNumber() + ")");
        }

        // Validate maze dimensions.
        if ( mazeRows <= 0 || mazeCols <= 0 )
            throw new RuntimeException("Maze dimensions must be positive (line "
                                       + inputReader.getLineNumber() + ")");

        // Construct the appropriate bounded maze.
        Maze newMaze = new Maze(mazeRows, mazeCols);
        return newMaze;
    }

    /** Reads in a Location object (must be a valid location in
     *  <code>maze</code>).
     *  @param maze      maze in which location must be valid
     *  @return  the newly created Location object
     *  @throws RuntimeException   if invalid location information is read
     **/
    private Location readLocation(Maze maze)
    {
        int row, col;
        try
        {
            // Read the location.
            row = readInt();
            col = readInt();
        }
        catch (java.io.EOFException e)
        {
            // Reached end of file
            return null;
        }
        catch (Exception e)
        {
            // Convert reading exceptions to MBSException.
            throw new RuntimeException("Error reading location (line " 
                                       + inputReader.getLineNumber() + ")"); 
        }
        Location loc = new Location(row, col);
        
        // Verify that location is valid in this maze.
        if ( maze.isValid(loc) )
            return loc;
        else
            throw new RuntimeException("Location " +  loc +
                                       " is not valid in this maze (line " 
                                        + inputReader.getLineNumber() + ")"); 
    }

    /** Returns the next token in the file as an integer.
     *  @return    an int containing the next number in the file
     *  @throws    java.io.EOFException if EOF
     *  @throws    java.lang.NumberFormatException if next token is not an int
     *  @throws    java.io.IOException if another type of input error occurs
     **/
    private int readInt()
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
    private String readString()
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

    /** Reads location information for the walls in the maze and
     *  constructs the walls.
     *  @param maze   the maze in which walls should be created
     *  @throws RuntimeException   if invalid location information is read
     **/
    private void readWalls(Maze maze)
    {
        // Read wall locations until there aren't any more, and construct
        // the walls as color blocks.  The color blocks add themselves to
        // the maze as they are constructed.
        Location loc;
        while ( (loc = readLocation(maze)) != null )
        {
            new ColorBlock(Color.black, maze, loc);
        }
    }

    /** Reads information about a maze from an initial configuration
     *  data file.
     *  @param  maze       the maze to save
     *  @param  file       java.io.File object from which to read
     *  @throws UnsupportedOperationException
     **/
    public void writeGrid(Grid maze, File file)
        throws java.io.FileNotFoundException
    {
        throw new UnsupportedOperationException();
    }

}
