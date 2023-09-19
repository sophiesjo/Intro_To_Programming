import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/** Aquarium Lab Series: <br>    
 *      The AquaSimApplication class contains the main function that will
 *      run the Aquarium Simulation. The program asks the user to input a number of fish
 *      and a number of steps and when start is pressed there will be an aquarium simulation
 *      that displays the number of fish inputted by the user. Each fish will be randomly 
 *      assigned a color and will take the number of steps inputted by the user. The fish 
 *      will change direction when reaching a wall and will randomly change direction while moving.<br>
 *
 * Modifications: <br>
 *   23 March 2008,  Alyce Brady,  Created skeleton main that constructs and
 *                                 displays an empty aquarium.<br>
 *   (date), (your name), Modified to .... <br>
 *
 *  @author  Sophie Sjogren
 *  @version 18 January 2021
 *  @see AquariumController
 **/
public class AquaSimApplication
{
    /**
     *  This is the main function.  It executes the program.
     *  @param    String args[] is never used
     **/
    public static void main(String args[])
    {
        //Welcome user to the interface
        System.out.println("Hello and welcome to the aquarium simulation!");

        // Construct the aquarium.  
        Aquarium aqua;                
        aqua = new Aquarium(600, 480);

        //Construct random integer generator
        Random generator = new Random();
        int randomInt;

        //The following code was initially used to construct three fish, but the code isn't needed anymore and therefore commented out.
        //I wasn't sure if this code was needed for submitting the assignment

        //AquaFish nemo;
        //if (randomInt == 0)
        //{
        //    nemo = new AquaFish(aqua, Color.RED);
        //}
        //else if (randomInt == 1 || randomInt == 2 || randomInt == 3)
        //{
        //    nemo = new AquaFish(aqua, Color.ORANGE);
        //}
        // else if (randomInt == 4)
        // {
        // nemo = new AquaFish(aqua, Color.YELLOW);
        // }
        // else if (randomInt == 5)
        // {
        // nemo = new AquaFish(aqua, Color.GREEN);
        // }
        // else if (randomInt == 6)
        // {
        // nemo = new AquaFish(aqua, Color.BLUE);
        // }
        // else
        // {
        // nemo = new AquaFish(aqua, Color.MAGENTA);
        // }

        // randomInt = generator.nextInt(6);
        // AquaFish dory;
        // if (randomInt == 0)
        // {
        // dory = new AquaFish(aqua, Color.RED);
        // }
        // else if (randomInt == 1)
        // {
        // dory = new AquaFish(aqua, Color.ORANGE);
        // }
        // else if (randomInt == 2)
        // {
        // dory = new AquaFish(aqua, Color.YELLOW);
        // }
        // else if (randomInt == 3)
        // {
        // dory = new AquaFish(aqua, Color.GREEN);
        // }
        // else if (randomInt == 4)
        // {
        // dory = new AquaFish(aqua, Color.BLUE);
        // }
        // else
        // {
        // dory = new AquaFish(aqua, Color.MAGENTA);
        // }

        // randomInt = generator.nextInt(6);
        // AquaFish flounder;
        // if (randomInt == 0)
        // {
        // flounder = new AquaFish(aqua, Color.RED);
        // }
        // else if (randomInt == 1)
        // {
        // flounder = new AquaFish(aqua, Color.ORANGE);
        // }
        // else if (randomInt == 2)
        // {
        // flounder = new AquaFish(aqua, Color.YELLOW);
        // }
        // else if (randomInt == 3)
        // {
        // flounder = new AquaFish(aqua, Color.GREEN);
        // }
        // else if (randomInt == 4)
        // {
        // flounder = new AquaFish(aqua, Color.BLUE);
        // }
        // else
        // {
        // flounder = new AquaFish(aqua, Color.MAGENTA);
        // }
        // //constructs three different fish which will be used in the aquarium

        // aqua.add(nemo);
        // aqua.add(dory);
        // aqua.add(flounder);
        //adds the fish to the aquarium

        // Construct a graphical user interface (GUI) to display and control
        // the simulation.  The user interface needs to know about the
        // aquarium, so we pass aqua and two true booleans to ask the user for number of steps and fish and this goes to the user interface constructor.
        AquaSimGUI userInterface;              
        userInterface = new AquaSimGUI(aqua, true, true);  

        // Tell the user how to start the aquarium simulation.
        System.out.println("Press the Start button to start the simulation.");

        // Now wait for the user to press the start button then show the aquarium.
        userInterface.waitForStart();
        userInterface.showAquarium();

        //Generate a random integer that determines the color of the fish.
        randomInt = generator.nextInt(12);

        //Initialize the fish object
        AquaFish fish;

        //Get the number of fish from the user
        int numberOfFish = userInterface.getNumberOfFish();

        //Create array list that will hold the fish
        ArrayList<AquaFish> fishList = new ArrayList<AquaFish>();

        //Loop that creates the fish for the aquarium and assigns random colors
        for(int i = 0; i < numberOfFish; i++)
        { 
            randomInt = generator.nextInt(6);
            if (randomInt == 0)
            {
                fish = new AquaFish(aqua, Color.RED);
            }
            else if (randomInt == 1)
            {
                fish = new AquaFish(aqua, Color.ORANGE);
            }
            else if (randomInt == 2)
            {
                fish = new AquaFish(aqua, Color.YELLOW);
            }
            else if (randomInt == 3)
            {
                fish = new AquaFish(aqua, Color.GREEN);
            }
            else if (randomInt == 4)
            {
                fish = new AquaFish(aqua, Color.BLUE);
            }
            else
            {
                fish = new AquaFish(aqua, Color.MAGENTA);
            }
            fishList.add(fish);
            aqua.add(fish);
        }

        // RUN THE AQUARIUM SIMULATION.
        // Make the fish move forward once and redisplay.
        //nemo.moveForward();
        //dory.moveForward();
        //flounder.moveForward();

        //Display the new aquarium post movement
        //userInterface.showAquarium();

        //Get number of steps
        int steps = userInterface.getNumberOfSteps();

        //Create int for random integer generator, will be used to randomly decide change of direction
        int randomNum;
        randomInt = generator.nextInt(4);

        //Loop to make the fish move forward and change direction when at a wall or randomly
        for (int i = 0; i < steps; i++)
        {
            for (AquaFish nextFish : fishList)
            {
                nextFish.moveForward();
                if (nextFish.atWall() || generator.nextInt(4) < 1)
                {
                    nextFish.changeDir();
                }
            }
            userInterface.showAquarium();
        }

        // WRAP UP.
        // Remind user how to quit application.
        userInterface.println ("Close GUI display window to quit.");

    }//end main

}//end class
