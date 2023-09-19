import java.awt.Color;

import edu.kzoo.grid.Location;
import edu.kzoo.grid.ColorBlock;
import edu.kzoo.grid.PictureBlock;
import edu.kzoo.grid.TextCell;

/**
 *  Simple Grid Object Programming Project:<br>
 *
 *  A SimpleGridObjectApp object represents an application with a simple
 *  grid in one may put basic grid objects, such as TextCell, ColorBlock,
 *  or PictureBlock objects.
 *  This grid displays "BINGO" at the top and includes pictures and colors in the other grid squares.
 *
 *  @author Kelly Schultz and Alyce Brady
 *  @version 8 January 2009
 * @author Kelly Schultz and Alyce Brady (created 8 January 2009)
 * @author Sophie Sjogren
 * @version 10 January 2021 
 */
public class SimpleGridObjectApp
{

    /** The main method represents the starting point of the application.
     *    @param args   (unused)
     */
    public static void main(String[] args)
    {
        // Create and initialize the grid.
        SimpleGrid grid = new SimpleGrid(6, 5);

        // Create graphical user interface capable of displaying
        // the grid.  The 2nd, 3rd, and 4th parameters are used to
        // create an appropriate Help menu.
        SimpleGridObjectGUI display = new SimpleGridObjectGUI(grid,
                                            "Sophie Sjogren", "Alyce Brady",
                                            "10 January 2021");


        // Place several PictureBlock objects in the grid.
        PictureBlock firstPictureBlock = new PictureBlock("images/Cactus.jpg",
                                                          "the image has a spiky cactus");
        grid.add(firstPictureBlock, 1, 2);
        display.showGrid();

        PictureBlock secondPictureBlock = new PictureBlock("images/Skier.jpg",
                                                           "the image has a skier shredding the slopes");
        grid.add(secondPictureBlock, 5, 4);
        display.showGrid();
        
        PictureBlock thirdPictureBlock = new PictureBlock("images/MonsterFace.jpg",
                                                           "the image has a scary monster face");
        grid.add(thirdPictureBlock, 3, 4);
        display.showGrid();
        
        PictureBlock fourthPictureBlock = new PictureBlock("images/Pyramid.jpg",
                                                           "the image has a giant pyramid");
        grid.add(fourthPictureBlock, 5, 0);
        display.showGrid();
        
        
        //Place several TextBlock objects that create the word BINGO at the top of the grid.
        TextCell firstTextBlock = new TextCell("B", Color.BLUE);
        TextCell secondTextBlock = new TextCell("I", Color.BLUE);
        TextCell thirdTextBlock = new TextCell("N", Color.BLUE);
        TextCell fourthTextBlock = new TextCell("G", Color.BLUE);
        TextCell fifthTextBlock = new TextCell("O", Color.BLUE);
        
        grid.add(firstTextBlock, 0, 0);
        grid.add(secondTextBlock, 0, 1);
        grid.add(thirdTextBlock, 0, 2);
        grid.add(fourthTextBlock, 0, 3);
        grid.add(fifthTextBlock, 0, 4);
        
        display.showGrid();
        
        //Place several ColorBlock objects in the grid.
        ColorBlock firstColorBlock = new ColorBlock(Color.GREEN);
        ColorBlock secondColorBlock = new ColorBlock(Color.RED);
        ColorBlock thirdColorBlock = new ColorBlock(Color.BLACK);
        ColorBlock fourthColorBlock = new ColorBlock(Color.WHITE);
        ColorBlock fifthColorBlock = new ColorBlock(Color.CYAN);
        ColorBlock sixthColorBlock = new ColorBlock(Color.YELLOW);
        ColorBlock seventhColorBlock = new ColorBlock(Color.GRAY);
        
        
        grid.add(firstColorBlock, 2, 1);
        grid.add(secondColorBlock, 1, 0);
        grid.add(thirdColorBlock, 2, 3);
        grid.add(fourthColorBlock, 2, 4);
        grid.add(fifthColorBlock, 3, 0);
        grid.add(sixthColorBlock, 4, 1);
        grid.add(seventhColorBlock, 4, 3);
        
        display.showGrid();

        System.out.println("Done.");
    }

}
