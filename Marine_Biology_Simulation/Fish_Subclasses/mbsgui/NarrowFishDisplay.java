package mbsgui;

// Class NarrowFishDisplay
//
// Author: Alyce Brady
//
// This class is a very slight variation on the College Board's
// NarrowFishDisplay class, as allowed by the GNU General Public License.
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

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  A <code>NarrowFishDisplay</code> draws a long, narrow fish (an object
 *  of the <code>Fish</code> class or of a subclass of <code>Fish</code>).
 *  It inherits the <code>draw</code> method from
 *  <code>FishDisplay</code>, but the different paths for the
 *  fish body and eyes created when the display is constructed give fish drawn
 *  with a <code>NarrowFishDisplay</code> their own specialized look.
 *
 *  @author Julie Zelensk (2002)
 *  @author Chris Nevison (2002)
 *  @author Alyce Brady (2019: moved call to 5-parameter buildPaths from
 *                       constructor to zero-parameter buildPaths)
 *  @version 25 April 2019
 **/
public class NarrowFishDisplay extends FishDisplay
{
    private static final double BODY_WIDTH = .3, BODY_LENGTH = .7;
    private static final double TAIL_WIDTH = .3, TAIL_LENGTH = .3;
    private static final double EYE_SIZE = .05;
    
    /** Constructs an object that knows how to display long, narrow fish.
     **/
    public void buildPaths()
    {    
        buildPaths(BODY_WIDTH, BODY_LENGTH, TAIL_WIDTH, TAIL_LENGTH, EYE_SIZE);
    }

}
