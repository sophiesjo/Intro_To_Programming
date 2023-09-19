package mbsgui;

// Class FishDisplay
//
// Author: Alyce Brady
//
// This class is based on the College Board's FishDisplay class,
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

import java.awt.Color;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Method;

import edu.kzoo.grid.GridObject;
import edu.kzoo.grid.Direction;
import edu.kzoo.grid.display.ScaledDisplay;
import edu.kzoo.grid.display.RotatedDecorator;

/**
 *  Grid-Based Marine Biology Simulation Program:<br>
 *
 *  The <code>FishDisplay</code> class provides a way to display
 *  a fish using an image, rotating and tinting it as needed.
 *
 *  @author Julie Zelensk (2002)
 *  @author Chris Nevison (2002)
 *  @author Alyce Brady (2002, 2019)
 *  @version 25 April 2019
 **/

public class FishDisplay extends ScaledDisplay
{
    private static final double BODY_WIDTH = .55, BODY_LENGTH = .75;
    private static final double TAIL_WIDTH = .5, TAIL_LENGTH = .4;
    private static final double EYE_SIZE = .08;

    private static final int GRADIENT_SIZE = 50;
    private static final AffineTransform ATX =
            AffineTransform.getScaleInstance(GRADIENT_SIZE, GRADIENT_SIZE);

    private static Color defaultColor = Color.WHITE;

    private Shape bodyAndTail, eye1, eye2;

    /** Gets default color to use for objects with no color method.  */
    public static Color getDefaultColor()
    {
        return defaultColor;
    }

    /** Sets default color to use for objects with no color method.  */
    public static void setDefaultColor(Color newDefaultColor)
    {
        defaultColor = newDefaultColor;
    }

    /**
     * Constructs an object that knows how to draw a simple fish
     * (which will need to be rotated when displayed).
     */
    public FishDisplay()
    {    
        buildPaths();
        addDecorator(new RotatedDecorator(Direction.NORTH));
    }
    
    /** Sets up the paths used for the fish with default values for the
     * body, tail, and eyes.
     */
    protected void buildPaths()
    {
        buildPaths(BODY_WIDTH, BODY_LENGTH, TAIL_WIDTH, TAIL_LENGTH, EYE_SIZE);
    }

    /** Sets up the paths used for the fish body, tail, and eyes.
     *  Different parameters will change the proportions, and thereby
     *  control the "look" of the fish.  The various parameters should be 
     *  specified assuming the fish will occupy a cell of size (1, 1).
     *  @param   bodyWidth       width of the elliptical body
     *  @param   bodyLength      length of the elliptical body
     *  @param   tailWidth       width of the triangular tail
     *  @param   tailLength      length of the triangular tail
     *  @param   eyeSize         diameter of the eye
     */
    protected void buildPaths(double bodyWidth, double bodyLength, 
                              double tailWidth, double tailLength, double eyeSize)
    {
        // Build a set of paths for a fish facing North in a unit-length cell.
        // We will rotate/scale as needed later.

        float halfFishLength = (float)(bodyLength + tailLength/3)/2;

        // The fish body is an ellipse of the given body width and length.
        // The ellipse is horizontally centered and slightly above vertical
        // center (to leave room for tail).
        Shape body = new Ellipse2D.Double(-bodyWidth/2, -halfFishLength, bodyWidth, bodyLength);

        // The fish tail is a triangle overlapping the end of body.
        GeneralPath tail = new GeneralPath();
        tail.moveTo(-(float)tailWidth/2, halfFishLength);	// lower left
        tail.lineTo(0, halfFishLength-(float)tailLength);	// top of tail
        tail.lineTo((float)tailWidth/2, halfFishLength);	// lower right
        tail.closePath();

        // Join body and tail together in one path.
        tail.append(body, false);
        bodyAndTail = tail;

        // The fish eyes are circles.
        eye1 = new Ellipse2D.Double(-bodyWidth/4, -halfFishLength + bodyLength/4, eyeSize, eyeSize);
        eye2 = new Ellipse2D.Double(+bodyWidth/4 - eyeSize, -halfFishLength + bodyLength/4, eyeSize, eyeSize);
    }

    /** Draws the given object as a fish.
     *  Fills a simple fish with gradient paint using the paths created in
     *  the construtor. The Fish is drawn facing North in a cell of
     *  size (1,1) centered around (0,0) on the drawing surface.
     *  (All scaling/rotating has been done beforehand).
     *  @param   objToDraw  object we want to draw to look like a fish
     *  @param   comp       component on which to draw
     *  @param   g2         drawing surface
     **/
    public void draw(GridObject objToDraw, Component comp, Graphics2D g2)
    {
        Color fishColor = getColor(objToDraw);

       // Stroke outline of fish body and tail in slightly darker color.
        g2.setPaint(fishColor.darker()); 	
        g2.draw(bodyAndTail);

        // Fill fish body and tail with gradient (scale up temporarily to get smooth dither).
        g2.scale(1.0/GRADIENT_SIZE, 1.0/GRADIENT_SIZE);  
        g2.setPaint(new GradientPaint(-GRADIENT_SIZE/4, -GRADIENT_SIZE/2, Color.white, 
                                       GRADIENT_SIZE/4, GRADIENT_SIZE/4, fishColor));
        g2.fill(ATX.createTransformedShape(bodyAndTail));
        g2.scale(GRADIENT_SIZE, GRADIENT_SIZE);

        // Fill black circles for the eyes.
        g2.setPaint(Color.black);
        g2.fill(eye1);
        g2.fill(eye2);
    }

    public Color getColor(GridObject objToDraw)
    {
        Color color;
        Class<? extends GridObject> objClass = objToDraw.getClass();
        try
        {
            Method colorMethod = objClass.getMethod("color", new Class[0]);
            color = (Color)colorMethod.invoke(objToDraw, new Object[0]);
        }
        catch (Exception e)
        {
            color = defaultColor;
        }

        return color;
    }
}

