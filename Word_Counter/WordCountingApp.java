// Import statements go here.  For example,
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Lab 2: Word Counter
 *
 *  The <code>WordCountingApp</code> class provides a main method
 *  for a program that analyzes text and counts the words. 
 *  The program will read the first line from the text and
 *  will analyze the length and number of words in that line then skip the next 60 lines and print out
 *  the 62nd line and do the same analysis as the first line. The program will then analyze the text as
 *  a whole and find the average length, average number of words per line, and the average length of words
 *  through the entire text.
 *
 *
 *  @author Sophie Sjogren
 *  @version 20 January, 2020
 */
public class WordCountingApp
{
    /**
     *  The main function initiates execution of this program.
     *    @param    String[] args not used in this program
     *              (but main methods always need this parameter)
     **/
    public static void main(String[] args)
    {
        //Introduce user to program
        System.out.println ("Welcome to Programming Project Word Counter.");
        System.out.println(" ");
        
        //Get the Grimm.txt file
        String fileName = "Grimm.txt";
        WordReader book = new WordReader(fileName);

        //Initialize some variables that will be used
        int counter = 0;
        int totalPrintedLength = 0;
        ArrayList<String> listOfWords = new ArrayList<String>();

        //Get the first line of text to print
        String firstLine = book.getNextLine();
        System.out.println("The first line in Grimm.txt is: ");
        System.out.println("     " + firstLine);

        //Get the length of the first line and the number of words, also add the line to the total print length and list of words
        int lengthFirstLine = firstLine.length();
        totalPrintedLength = totalPrintedLength + lengthFirstLine;
        listOfWords = book.breakIntoWords(firstLine);
        int firstLineWords = 0;
        for (String nextWord: listOfWords)
        {
            firstLineWords++;
        }
        System.out.println("The length of the first line is " + lengthFirstLine +". There are " + firstLineWords + " words in the first line.");

        //Collect data for the rest of the lines in the text and when it reaches line 62, read off the line and analyze length and word count
        System.out.println(" ");
        System.out.println("Skipping 60 lines: .......................");
        System.out.println(" ");
        int numberOfWords = 0;
        String nextLine;
        int characterSum = 0;
        for (nextLine = book.getNextLine(); nextLine !=null; nextLine = book.getNextLine())
        {
            if(counter == 60)
            {
                System.out.println("Line 62: " + nextLine);
                int lengthOfLine = nextLine.length();
                ArrayList<String> singleLineWords = new ArrayList<String>();
                singleLineWords = book.breakIntoWords(nextLine);
                int lineWords = 0;
                for (String word : singleLineWords)
                {
                    lineWords++;
                }
                System.out.println("Length of this line is " + lengthOfLine + ". There are " + lineWords + " words in the line.");
            }
            totalPrintedLength = totalPrintedLength + nextLine.length();
            listOfWords = book.breakIntoWords(nextLine);
            for (String nextWord : listOfWords)
            {
                numberOfWords++;
                int lineLength = nextWord.length();
                characterSum = characterSum + lineLength;
            }
            counter++;
        }
        
        //Use the data collected above to display average length and number of words
        System.out.println(" ");
        System.out.println("Including the title, table of contents, licensing, etc...");
        System.out.println("The average length of the lines in the text is: " + (totalPrintedLength/counter));
        double averageWords = (numberOfWords*1.0)/counter;
        System.out.println("The average number of words per line of the text is: " + averageWords);
        double averageCharacters = (characterSum*1.0)/numberOfWords;
        System.out.println("Words are about " + averageCharacters + " characters long.");

    }//end main

}//end class
