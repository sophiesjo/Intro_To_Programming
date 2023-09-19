import edu.kzoo.grid.Grid;
import edu.kzoo.grid.BoundedGrid;
import edu.kzoo.grid.Location;


/**
 * The maze class constructs the maze object and has setter and getter methods for the finish and start locations.
 *
 * @author Sophie Sjogren
 * @version 13 March 2021
 */
public class Maze extends BoundedGrid
{
    // Instance variables
    protected Location startingLoc;     //starting location of mouse
    protected Location finishLoc;       //location of cheese
    private int rows = 1;               //number of rows in maze
    private int cols = 1;               //number of columns in maze
    
    /**
     * Constructor for the maze object that uses the specified dimensions
     * 
     *  @param rows  the number of rows in the maze
     *  @param cols  the number of columns in the maze
     */
    public Maze(int rows, int cols)
    {
        super(rows, cols);
    }
    
    /** Sets the starting location of the mouse
     *  
     *  @param start    the starting location of the mouse on the grid
     **/
    protected void setStartLoc(Location start)
    {
        this.startingLoc = start;
    }

    /** Sets the location of the cheese and where the mouse will end up
     *  
     *  @param finish    the location of the cheese on the grid
     **/
    protected void setFinishLoc(Location finish)
    {
        this.finishLoc = finish;
    }
    
    /** Method to get the starting location of the mouse
     *  
     *  @return the location that the mouse starts
     **/
    public Location getStartLoc()
    {
        return this.startingLoc;
    }
    
    /** Method to get the location of the cheese and where the mouse will finish
     *  
     *  @return the location that the cheese is in and where the mouse will end up
     **/
    public Location getFinishLoc()
    {
        return this.finishLoc;
    }
}
