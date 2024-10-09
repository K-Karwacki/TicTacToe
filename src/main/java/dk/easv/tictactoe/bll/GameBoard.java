
package dk.easv.tictactoe.bll;

/**
 *
 * @author EASV
 */
public class GameBoard implements IGameBoard {

    private int currentPlayer = 0;  // 0 for Player 1, 1 for Player 2
    private static final int TOTAL_PLAYERS = 2;  // Number of players
    private int[][] board;
    private boolean gameOver;  // Flag to track if the game is over
    private int winner;        // The ID of the winner, or -1 if it's a draw
    private int rows;
    private int cols;
    private int width;

    public GameBoard(int width){
        this.width = width;
        this.rows = width;
        this.cols = width;
        this.board = new int[width][width];
        this.newGame();;
    }
    // Constructor to initialize the board
    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols]; // Initialize the board with the given size
        this.newGame(); // Reset game state
    }

    // Default constructor (optional)
    public GameBoard() {
        this(3, 3); // Default to a 3x3 board
    }



    // Implement the getNextPlayer method as required by IGameBoard
    @Override
    public int getNextPlayer() {
        if(currentPlayer == 0){
            return 1;
        }else{
            return 0;
        }
    }

    public void newGame() {
        // Reset the game board to empty state
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = 0; // Assuming 0 means an empty cell
            }
        }

        // Reset game state
        gameOver = false;
        winner = -1; // No winner yet
        currentPlayer = 0; // Reset to Player 1
    }


    public boolean play(int col, int row) {
        // Check if the game is over
        if (gameOver) {
            return false;
        }

        // Validate if the given coordinates are within bounds
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return false;
        }

        // Check if the position is already taken
        if (board[row][col] != 0) {  // Assuming 0 represents an empty position
            return false;
        }

        // Place the current player's marker
        board[row][col] = currentPlayer + 1; // Store 1 or 2 (for Player 1 or Player 2)

        // Check if this move results in a win for the current player
        if (checkWin(col, row)) {
            gameOver = true;
            winner = currentPlayer;
//            System.out.println("the winner is: " + currentPlayer);
            switchPlayer();
        }
        // If the board is full and no win, it's a draw
        else if (isBoardFull()) {
            gameOver = true;
            winner = -1;  // Draw
        }
        // Otherwise, switch to the next player
        else {
            switchPlayer();
        }

        return true;  // Move is successful
    }

    @Override public int getBoardWidth()
    {
        return width;
    }

    @Override public String getCurrentPlayerMark()
    {
        if(currentPlayer == 0){
            return "X";
        }else if(currentPlayer == 1){
            return "O";
        }
        return "";
    }

    @Override public String getNextPlayerMark()
    {

        return "";
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % TOTAL_PLAYERS;  // Switch to the next player
    }

    public boolean isBoardFull() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPositionTaken(int row, int col){
        return board[row][col] != 0;
    }

    private boolean checkWin(int col, int row) {
        // Check horizontal, vertical, and diagonal for a win condition
        return checkRow(row) || checkColumn(col) || checkDiagonals();
    }

    private boolean checkRow(int row) {
        for (int col = 0; col < cols; col++) {
            if (board[row][col] != board[row][0]) {
                return false; // Not a win
            }
        }
        return board[row][0] != 0; // Check if the first cell is not empty
    }


    private boolean checkColumn(int col) {
        for (int row = 0; row < rows; row++) {
            if (board[row][col] != board[0][col]) {
                return false; // Not a win
            }
        }
        return board[0][col] != 0; // Check if the first cell is not empty
    }

    private boolean checkDiagonals() {
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < rows; i++) {
            if (board[i][i] != board[0][0]) {
//                System.out.println(board[i][i] + " " + board[0][0]);
                diagonal1 = false; // Not a win
            }
            if (board[i][rows - 1 - i] != board[0][rows - 1]) {
                diagonal2 = false; // Not a win
            }
        }

        return (diagonal1 && board[0][0] != 0) || (diagonal2 && board[0][rows - 1] != 0);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }
}
