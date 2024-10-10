
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import dk.easv.tictactoe.bll.MiniMax;
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
public class TicTacAiController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;


    private static final String TXT_PLAYER = "Player vs Computer";
    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        Integer col = GridPane.getColumnIndex((Node) event.getSource());

        Button b = (Button)event.getSource();

        if(game.play(row,col, 0)){
            b.setText("O");
            game.switchPlayer();
            handleAIMove();
            if(game.isGameOver()){
                displayWinner(game.getWinner());
            }
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
        lblPlayer.setText(TXT_PLAYER);
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
        initBoard();
        lblPlayer.setText(TXT_PLAYER);
    }

    /**
    * Handle AI move
    */
    private void handleAIMove(){
        if(game.getCurrentPlayer()== 1){
            int[] bestMove = MiniMax.getBestMove(game);
            if(game.play(bestMove[0], bestMove[1], 1)){
                System.out.println(bestMove[0] + " : "+bestMove[1]);
                for(Node n : gridPane.getChildren())
                {
                    if (GridPane.getRowIndex(n) == bestMove[0]
                        && GridPane.getColumnIndex(n) == bestMove[1]){
                        ((Button)n).setText("X");
                        game.switchPlayer();
                    }
                }

            }
        }
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = switch (winner)
            {
                case 0 -> "Player: wins!!!";
                case 1 -> "Computer wins :(, keep trying ;)";
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

    /**
     * Init board buttons and setConstraints for gridPane
     */
    private void initBoard(){
        int width = game.getBoardWidth();
        for(int i = 0; i<width; i++){
            for(int c=0; c<width; c++){
                Button btn = new Button();
                btn.setOnAction(this::handleButtonAction);
                GridPane.setConstraints(btn, i, c);
                gridPane.getChildren().add(btn);
                btn.setPrefWidth(200);
                btn.setPrefHeight(200);
            }
        }
    }
}