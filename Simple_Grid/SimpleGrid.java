// Class: SimpleGrid
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

import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Location;

/**
 *  Simple Grid Object Programming Project:<br>
 *
 *  A SimpleGrid object represents a simple bounded grid.  In addition
 *  to the methods it inherits from Grid and BoundedGrid, it provides
 *  methods that take row and column parameters rather than Location
 *  object parameters.
 *
 *  @author Alyce Brady
 *  @author Sophie Sjogren
 *  @version 10 January 2021
 */
public class SimpleGrid extends BoundedGrid
{

    /** Constructs a new SimpleGrid object with the given number of rows
     *  and the given number of columns.
     *  @param numRows    the number of rows to give this simple grid
     *  @param numCols    the number of columns to give this simple grid
     */
    public SimpleGrid(int numRows, int numCols)
    {
        super(numRows, numCols);
    }

    /** Returns the object at a specific (row, col) location in this grid.
     *  @param row    the row in which to look (where the first row is 0)
     *  @param col    the column in which to look (where the first column is 0)
     *  @return       the object at location <code>(row, col)</code>;
     *                <code>null</code> if <code>(row, col)</code> is not in
     *                the grid or is empty.  For example, the call
     *                <code>objectAt(0, 0)</code> returns the object in the
     *                upper-left corner (first row, first column), if there
     *                is one, otherwise it returns <code>null</code>.
     */
    public synchronized GridObject objectAt(int row, int col)
    {
        return objectAt(new Location(row, col));
    }

    /** Adds a new object to this grid at the specified (row, col) location.
     *  (Precondition: <code>obj.grid()</code> and
     *   <code>obj.location()</code> are both <code>null</code>; location
     *   <code>(row, col)</code> is a valid empty location in this grid.)
     *  @param obj the new object to be added
     *  @param row    the row in which to place <code>obj</code>
     *                  (row numbering starts at 0)
     *  @param col    the column in which to place <code>obj</code>
     *                  (column numbering starts at 0)
     *  @throws    IllegalArgumentException if the precondition is not met
     */
    public void add(GridObject obj, int row, int col)
    {
        add(obj, new Location(row, col));
    }

    /** Removes whatever object is at the specified location in this grid.
     *  If there is no object at the specified location, this method does
     *  nothing.
     *  @param row     the row location from which to remove an object
     *                  (row numbering starts at 0)
     *  @param col     the column location from which to remove an object
     *                  (column numbering starts at 0)
     */
    public void remove(int row, int col)
    {
        remove(new Location(row, col));
    }

}
