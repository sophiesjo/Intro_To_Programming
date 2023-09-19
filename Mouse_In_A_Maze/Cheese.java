// Class: Cheese
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

import java.awt.Color;

import edu.kzoo.grid.ColorBlock;
import edu.kzoo.util.NamedColor;

/**
 *  Mouse in a Maze Program:<br>
 *
 *    A Cheese object represents a piece of cheese in the maze.
 *
 *  @author Alyce Brady
 *  @version 5 March 2003
 **/
public class Cheese extends ColorBlock
{
    /** Constructs a piece of cheese at the specified location in
     *  a grid.
     **/
    public Cheese()
    {
        super(new NamedColor(Color.YELLOW));
    }

}
