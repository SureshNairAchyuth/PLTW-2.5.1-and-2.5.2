/*
 * Activity 2.5.2
 *
 * A Board class the PhraseSolverGame
 */
import java.io.File;
import java.util.Scanner;

public class  Board
{
  private String solvedPhrase;
  private String phrase;
  private int currentLetterValue; 

  /* your code here - constructor(s) */ 
  public Board()
  {
    solvedPhrase = "";
    phrase = "";
    currentLetterValue = 0;
    phrase = loadPhrase();
    setLetterValue();
    System.out.println("Phrase: " + phrase);
  }

  /* your code here - accessor(s) */
  public String getSolvedPhrase() { 
    return solvedPhrase;
  }
  public int getLetterValue() { 
    return currentLetterValue;
  }
  
  
  /* your code here - mutator(s)  */

  /* ---------- provided code, do not modify ---------- */
  public void setLetterValue()
  {
    int randomInt = (int) ((Math.random() * 10) + 1) * 100;    
    currentLetterValue = randomInt;
  }

  public boolean solvePhrase(String guess)
  {
    if (phrase.equals(guess))
    {
      return true;
    }
    return false;
  }

  private String loadPhrase()
  {
    String tempPhrase = "";
    
    int numOfLines = 0;
    try 
    {
      Scanner sc = new Scanner(new File("phrases.txt"));
      while (sc.hasNextLine())
      {
        tempPhrase = sc.nextLine().trim();
        numOfLines++;
      }
    } catch(Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
		int randomInt = (int) ((Math.random() * numOfLines) + 1);
    
    try 
    {
      int count = 0;
      Scanner sc = new Scanner(new File("phrases.txt"));
      while (sc.hasNextLine())
      {
        count++;
        String temp = sc.nextLine().trim();
        if (count == randomInt)
        {
          tempPhrase = temp;
        }
      }
    } catch (Exception e) { System.out.println("Error reading or parsing phrases.txt"); }
    
    for (int i = 0; i < tempPhrase.length(); i++)
    {
      if (tempPhrase.substring(i, i + 1).equals(" "))
      {
        solvedPhrase += "  ";
      }  
      else
      {
        solvedPhrase += "_ ";
      }
    }  
    
    return tempPhrase;
  }  
  /*
  this method accepts one parameter: the letter guessed by the player (a string containing a single character)
  the method then iterates over the phrase to check if the guessed letter matches any in the phrase
    if a match is found, that letter is revealed in the puzzle
  returns a boolean indicating whether the guessed letter was present in the phrase 
  */
  public boolean guessLetter(String guess)
  {
    // boolean to check if a matching letter was found in the phrase
    boolean foundLetter = false;
    // String to build a new solved phrase with revealed letters
    String newSolvedPhrase = "";
    
    // iterate over each character in the phrase
    for (int i = 0; i < phrase.length(); i++)
    {
      // check if the guessed letter matches a letter in the phrase
      if (phrase.substring(i, i + 1).equals(guess))
      {
        // update newSolvedPhrase to include the guessed letter
        newSolvedPhrase += guess + " ";
        // set boolean to true since a match was found
        foundLetter = true;
      }
      else
      {
        // otherwise, retain the current state of the solved phrase for this character
        newSolvedPhrase += solvedPhrase.substring(i * 2, i * 2 + 1) + " ";  
      }
    }
    // update the solvedPhrase variable to the latest version with any newly revealed letters
    solvedPhrase = newSolvedPhrase;
    // return whether a matching letter was found
    return foundLetter;
  } 
} 