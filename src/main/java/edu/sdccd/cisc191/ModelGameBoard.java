package edu.sdccd.cisc191;

// Adapted from GoneFishing by Tasha Frankie and Allan Schoolyard
import java.util.Random;

/**
 * Contains all the data required for the game play
 */
public class ModelGameBoard
{
    public static int DIMENSION = 6;
    public static int GUESSES = 30;
    public static int TOTAL_FISH = 5;    // Initial fish count to be 5, got a chance to win. ^_^

    private final boolean[][] gameBoard;
    private int guessesRemaining;
    private int fishRemaining;

    /**
     * Initializes the game board with fish
     */
    public ModelGameBoard()
    {
        gameBoard = new boolean[DIMENSION][DIMENSION];  // defaults to false
        guessesRemaining = GUESSES;
        fishRemaining = TOTAL_FISH;
        Random randomNumberGenerator = new Random();
        for (int fishCounter = 0; fishCounter < TOTAL_FISH; fishCounter++)
        {
            int x, y;
            // finds an empty gameBoard slot
            do
            {
                x = randomNumberGenerator.nextInt(DIMENSION);
                y = randomNumberGenerator.nextInt(DIMENSION);
            } while (gameBoard[x][y]);
            gameBoard[x][y] = true;
        }
    }

    /**
     * @param row FishAt-button's row location
     * @param col FishAt-button's column location
     * @return Returns true if fish is found at row,col
     */
    public boolean fishAt(int row, int col)
    {
        return gameBoard[row][col];
    }

    /**
     * @param row Clicked-button's row location
     * @param col Clicked-button's column location
     */
    public void makeGuess(int row, int col)
    {
        boolean foundFish = fishAt(row, col);
        guessesRemaining--;
        if (foundFish)
        {
            fishRemaining--;
        }
    }

    public int getGuessesRemaining()
    {
        return guessesRemaining;
    }

    public int getFishRemaining()
    {
        return fishRemaining;
    }
}