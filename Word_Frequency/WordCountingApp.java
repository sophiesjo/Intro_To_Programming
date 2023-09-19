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
 *  will print the length and number of words in that line then skip the next 60 lines and print out
 *  the 62nd line and do the same analysis as the first line. The program will then analyze the text as
 *  a whole and print the average length, average number of words per line, and the average length of words
 *  through the entire text.
 *  In the next exercise, the program prints the number of distinct words and finds how many
 *  occur once and how many are longer than 14 characters along with if any contain the word "tale"
 *  and then the program prints how many times certain characters are referenced.
 *
 *
 *  @author Sophie Sjogren
 *  @version 26 January, 2020
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

        //Assign and get the text file
        String fileName = "HistoryOfAustralia.txt";
        WordReader book = new WordReader(fileName);

        //Initialize some variables that will be used
        int counter = 0;
        int totalPrintedLength = 0;
        ArrayList<String> listOfWords = new ArrayList<String>();
        ArrayList<String> fullListOfWords = new ArrayList<String>();

        //Get the first line of text to print
        String firstLine = book.getNextLine();
        System.out.println("The first line in HistoryOfAustralia.txt is: ");
        System.out.println("     " + firstLine);

        //Get the length of the first line and the number of words, also add the line to the total print length and list of words
        int lengthFirstLine = firstLine.length();
        totalPrintedLength = totalPrintedLength + lengthFirstLine;
        listOfWords = book.breakIntoWords(firstLine);
        int firstLineWords = 0;
        for (String nextWord: listOfWords)
        {
            firstLineWords++;
            fullListOfWords.add(nextWord);
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
            //Check for the 62nd line and analzye similarily to first line
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
            //Loop through words to get amount of characters
            for (String nextWord : listOfWords)
            {
                numberOfWords++;
                int lineLength = nextWord.length();
                characterSum = characterSum + lineLength;
                fullListOfWords.add(nextWord);
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

        //New Exercise - 22 January 2021
        System.out.println("\n-------- New Exercise --------");

        //Create array list for the distinct words in the file and call on WordReader class
        ArrayList<String> distinctWords = new ArrayList<String>();
        distinctWords = book.getFullWordList();

        //Print the number of distinct words
        int numberOfDistinct = distinctWords.size();
        System.out.println("HistoryOfAustralia.txt has " + numberOfDistinct + " distinct words.");

        //Count the number of words that appear only once in the test
        int numberOfOnce = 0;
        for (String word : distinctWords)
        {
            int count = book.getCountFor(word);

            //the if statement determines if the word shows up once then adds one to the total if it is true
            if(count == 1)
            {
                numberOfOnce++;
            }
        }
        //Print out results from count of words that appear once
        System.out.println("There are " + numberOfOnce + " words that occur only once in HistoryOfAustralia.txt");

        //Loop through the distinct words and count/print how many are longer than 14 characters
        System.out.println("Words that are more than 14 characters long in HistoryOfAustralia.txt: \t");
        int longWordCount = 0;
        for (String newWord : distinctWords)
        {
            int length = newWord.length();

            //Check if the word is longer than 14 characters and print word and add to counter if it is true
            if(length > 14)
            {
                longWordCount++;
                System.out.print("   " + newWord + " ");
            }
        }
        //Print out number of long words
        System.out.println("\nHistoryOfAustralia.txt has " + longWordCount + " words that are more than 14 characters long.");

        //Loop through the list of words and see how many words contain the word "cat"
        System.out.print("Words containing 'cat': ");
        for (String aWord : distinctWords)
        {
            if(aWord.contains("cat"))
            {
                System.out.print(" " + aWord + " (" + book.getCountFor(aWord) + "); ");
            }
        }

        //Loop through all the words and count the number of times a character's name is mentioned
        int hansCounter = 0;
        int carterCounter = 0;
        int tomCounter = 0;
        int marleenCounter = 0;
        int gretelCounter = 0;
        for (String thisWord : fullListOfWords)
        {
            //Search for Hans's, Carter's, and Tom's name
            if(thisWord.equalsIgnoreCase("Cook"))
            {
                hansCounter++;
            }
            else if(thisWord.equalsIgnoreCase("England"))
            {
                carterCounter++;
            }
            else if(thisWord.equalsIgnoreCase("Macarthur"))
            {
                tomCounter++;
            }
            else if(thisWord.equalsIgnoreCase("Phillip"))
            {
                marleenCounter++;
            }
            else if(thisWord.equalsIgnoreCase("Australia"))
            {
                gretelCounter++;
            }
        }
        //Print the total references for each character
        System.out.println("\nThere are " + hansCounter + " references to Captain Cook in HistoryOfAustralia.txt");
        System.out.println("There are " + carterCounter + " references to England in HistoryOfAustralia.txt");
        System.out.println("There are " + tomCounter + " references to Macarthur in HistoryOfAustralia.txt");
        System.out.println("There are " + marleenCounter + " references to Phillip in HistoryOfAustralia.txt");
        System.out.println("There are " + gretelCounter + " references to Australia in HistoryOfAustralia.txt");

        //Start of new exercise - 25 January 2021
        System.out.println("\n-------- New Exercise --------");

        //Find the most frequently used word by looping through all the words and finding a maximum from the getCountFor() method
        String frequentWord = "null";
        int occurMax = -1;

        //Loop through each distint word
        for (String aWord : distinctWords)
        {
            //Determine if larger or smaller word count
            if(book.getCountFor(aWord) > book.getCountFor(frequentWord))
            {
                frequentWord = aWord;
                occurMax = book.getCountFor(aWord);
            }
        }
        //Print results from most frequent word
        System.out.println("The most frequently-used word is " + frequentWord + ". It occurs " + occurMax + " times.");

        //Find the most frequent word(s)
        ArrayList<String> frequent = new ArrayList<String>();
        int upperBound = 8000;
        //Loop 40 times to get the 40 most frequent words
        for (int i = 0; i < 40; i++)
        {
            //Initialize maximum frequency counter
            int maxFrequency = -1;
            String freqWord = "null";
            //Loop through the distint words for the most frequent words
            for (String newWord : distinctWords)
            {
                if(book.getCountFor(newWord) > book.getCountFor(freqWord) && book.getCountFor(newWord) < upperBound)
                {
                    maxFrequency = book.getCountFor(newWord);
                    freqWord = newWord;
                }
            }
            //Loop through the words to add all freuqently 
            for (String nextWord : distinctWords)
            {
                if(book.getCountFor(nextWord) == maxFrequency)
                {
                    frequent.add(nextWord);
                }
            }
            upperBound = maxFrequency;
        }

        //Loop through all the strings in the frequent array list and print them along with their count
        System.out.println("The 40 most frequently-used word(s) are: ");
        for (String word : frequent)
        {
            System.out.println( word + " (" + book.getCountFor(word) + ")");
        }

    }//end main
}//end class
