
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public interface IGameBoard
{
    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    int getNextPlayer();

    /**
     * Attempts to let the current player play at the given coordinates. If the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver ==
     * true this method will always return false.
     */
    boolean play(int col, int row, int player);


    /**
     * Returns board width 3 -> (3x3) 5 -> (5x5)
     */
    int getBoardWidth();


    /**
     * returns true if board[row][col] = 0, empty state
     */
    boolean isPositionEmpty(int row, int col);


    boolean isBoardFull();
    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will retun false.
     */
    boolean isGameOver();

    /**
     * Gets the id of the winner, -1 if its a draw or if the game is still running.
     *
     * @return int id of winner, or -1 if draw or if gameOver() == false.
     */
    int getWinner();

    /**
     * Resets the game to a new game state.
     */
    void newGame();

    /**
     * Returns current player 0 or 1
     */
    int getCurrentPlayer();
    /**
     * sets board[row][col] to 0, empty state
     */
    void resetPosition(int row, int col);
    /**
     * Returns value on board[row][col] 0,1,2
     */
    int getPosition(int row, int col);
    /**
     * Switches players from 1 to 0, from 0 to 1
     */
    void switchPlayer();
    /**
     * Help function for Minimax algorithm
     */
    boolean playMarkAt(int row, int col, int i);
    /**
     * returns "O" for player 0, and "X" for player 1
     */
    String getPlayerMark(int player);
}
