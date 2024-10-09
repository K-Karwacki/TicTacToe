
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

/**
 *
 * @author EASV
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    boolean xOrO = false; // declare a boolean

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        Integer col = GridPane.getColumnIndex((Node) event.getSource());
        int r = (row == null) ? 0 : row;
        int c = (col == null) ? 0 : col;

        Button b = (Button)event.getSource();

        if(game.play(c,r)){
            b.setText(game.getPlayerMark(game.getCurrentPlayer()));
            setPlayerLbl();
            if(game.isGameOver()){
                displayWinner(game.getWinner());
            };
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        clearBoard();
//        lblPlayer.setText(TXT_PLAYER + game.getCurrentPlayerMark());
        setPlayerLbl();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard(3);
        setPlayerLbl();
//        lblPlayer.setText(TXT_PLAYER + game.getCurrentPlayerMark());
    }

    /**
     * Set the next player
     */
    private void setPlayerLbl()
    {
        String nextMoveMark = game.getPlayerMark(game.getNextPlayer());
        lblPlayer.setText("Next move by: " + nextMoveMark);
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = switch (winner)
            {
                case 0 -> "Player: " + winner + " wins!!!";
                case 1 -> "Player " + "O" + " wins!!!";
                case -1 -> "It's a draw!";
                default -> "TicTacToe";
            };
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
        }
    }
}