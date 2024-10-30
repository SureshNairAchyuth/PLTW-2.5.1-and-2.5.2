/*
 * Activity 2.5.2
 *
 *  The PhraseSolver class for the PhraseSolverGame
 */
import java.util.Scanner;


public class PhraseSolver
{
  // attributes for players, game board, and solved status
  private Player player1;
  private Player player2;
  private Board board;
  private Boolean solved;
 
  // constructor for PhraseSolver
  public PhraseSolver()
  {
    Scanner input = new Scanner(System.in);
   
    // Prompting players for their names
    System.out.print("Enter name for Player 1: ");
    String name1 = input.nextLine();
    System.out.print("Enter name for Player 2: ");
    String name2 = input.nextLine();
   
    // Initializing players with names and setting up board
    player1 = new Player(name1);
    player2 = new Player(name2);
    board = new Board();
    solved = false;
  }
 
  /* Accessor(s) */
 
  /* Mutator(s) */


  public void play()
  {
    // Main Game loop
    solved = false;
    int currentPlayer = 1;


    Scanner input = new Scanner(System.in);
   
    while (!solved)
    {
      // Display the board state
      System.out.println("Current board: " + board.getSolvedPhrase());
     
      // Determine the active player
      Player activePlayer = (currentPlayer == 1) ? player1 : player2;
      System.out.println(activePlayer.getName() + "'s turn");


      // Player makes a guess
      System.out.print("Enter your guess (letter or full phrase): ");
      String guess = input.nextLine();


      // Check if the guess solves the phrase
      if (guess.length() > 1) {
        // Full phrase guess
        solved = board.solvePhrase(guess);
        if (solved) {
          System.out.println("Congratulations, " + activePlayer.getName() + "! You've solved the puzzle!");
        }
      } else {
        // Creates single letter guess
        boolean correct = board.guessLetter(guess);
        if (!correct) {
          System.out.println("Incorrect guess.");
        }
      }


      // Swap the current player for the next turn
      currentPlayer = (currentPlayer == 1) ? 2 : 1;


      // Condition to end the game
      if (solved || board.getSolvedPhrase().replace(" ", "").equals(board.getSolvedPhrase())) {
        solved = true;
      }
    }
  }
}


