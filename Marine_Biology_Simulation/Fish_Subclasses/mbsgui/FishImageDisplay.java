package mbsgui;

// Class FishImageDisplay
//
// Author: Alyce Brady
//
// This class is loosely based on the College Board's FishImageDisplay class,
// as allowed by the GNU General Public License.
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

import edu.kzoo.grid.Direction;
import edu.kzoo.grid.display.ScaledImageDisplay;
import edu.kzoo.grid.display.RotatedDecorator;
import edu.kzoo.grid.display.ScaledImageTintDecorator;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  The <code>FishImageDisplay</code> class provides a way to display
 *  a fish using an image, rotating and tinting it as needed.
 *
 *  @author Alyce Brady
 *  @version 25 April 2019
 **/

public class FishImageDisplay extends ScaledImageDisplay
{
    /**
     * Constructs an object that knows how to display a fish as
     * an image (which will need to be rotated when displayed).
     */
    public FishImageDisplay(String imageFilename, Direction dir)
    {
        super(imageFilename);
        addDecorator(new RotatedDecorator(dir));
        addDecorator(new ScaledImageTintDecorator());
    }
}

