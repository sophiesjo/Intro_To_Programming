------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know to use it. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Programming Project 2

PURPOSE OF PROJECT: To create a grid that stores text, picture, and color objects in the different squares

VERSION or DATE: 10 January 2021

HOW TO START THIS PROJECT:  Within BlueJ, compile the project, if
necessary (see next paragraph).  Then right-click (or control-click)
on the SimpleGridObjectApp class.  Select "void main(String[] args)", then
click on "Ok".

If a Java class needs to be compiled, BlueJ indicates this by
painting stripes across the class's icon.  You can compile a single class
from its editor window by clicking on the Compile button at the top of that
window.  Alternatively, you can compile all classes in the project by
clicking on the Compile button in the toolbar to the left of the central
icon diagram for the project.  (Actually, BlueJ will check which classes
need to be compiled and compile only those.)

Expected results during test: 
Stage 1
-I predict there will be a grid of 3 rows and 4 columns.
-The parameters for the SimpleGridObjectsGUI class takes the grid object and uses the string text of the student name, who helped, and the date of when the program is complete.
-The constructor being used in the main class is the PictureBlock(java.lang.String imageFilename, java.lang.String description).
The parameters being passed to this constructor give the name of the file that the constructor needs to retrieve and gives a decsription of what the file is. 
-I expect to see a grid with 3 rows and 4 columns with a picture of a cactus in the top, left most grid square and a skier in the square 2 to the right and 3 down from the other image.
Stage 2
-It is expected to have 6 rows and 5 columns with new graphics and descriptions along with an edited author. 

Actual results:
Stage 1
-Yes the grid was the same size as expected and the objects were in their expected places. 
-The name of the author and date were published as expected.
Stage 2
-The program acted as expected and displayed everything correctly. 

When trying to put two objects in the same grid square the program says illegal argument exception

AUTHORS: Sophie Sjogren, based on a template by Alyce Brady and Kelly Schultz

USER INSTRUCTIONS:  This application does everything it needs to do on its own,
so there is nothing for the user to do other than to compile and start the project,
as described above.
