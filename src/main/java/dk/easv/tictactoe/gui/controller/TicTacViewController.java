
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
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
import javafx.scene.paint.Color;

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
    
    private static final String TXT_PLAYER = "Mode: ";
    private IGameBoard game;
    private boolean aiMode;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());

            int player = game.getNextPlayer();

            Button b = (Button)event.getSource();

            switchColors();
            if(aiMode){
                if(game.play(row,col, 0)){
                    b.setText("O");
                    b.setDisable(true);
                    game.switchPlayer();
                    handleAIMove();
                    if(game.isGameOver()){
                        displayWinner(game.getWinner());
                    }
                }
            }else{
                if (game.play(row, col, game.getCurrentPlayer()))
                {
                    b.setText(game.getPlayerMark(game.getCurrentPlayer()));
                    b.setDisable(true);
                    game.switchPlayer();
                    setPlayer();

                    if(game.isGameOver()){
                        displayWinner(game.getWinner());
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void handleAIMove(){
        if(game.getCurrentPlayer()== 1){
            int[] bestMove = MiniMax.getBestMove(game);
            if(game.play(bestMove[0], bestMove[1], 1)){
                for(Node n : gridPane.getChildren())
                {
                    if (GridPane.getRowIndex(n) == bestMove[0]
                        && GridPane.getColumnIndex(n) == bestMove[1]){
                        ((Button)n).setText("X");
                        n.setDisable(true);
                        game.switchPlayer();
                    }
                }

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
        setPlayer();
        clearBoard();
        if(aiMode){
            handleAIMove();
        }
    }

    @FXML
    private void handleSwitchMode(ActionEvent event){
        aiMode = !aiMode;
        game.newGame();
        setPlayer();
        clearBoard();
        if(aiMode && game.getCurrentPlayer() == 1){
            handleAIMove();
        }
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
        game = new GameBoard();
        aiMode = true;
        setPlayer();
        initBoard();
        if(aiMode && game.getCurrentPlayer() == 1){
            handleAIMove();
        }
    }

    /**
     * Set the next player
     */
    private void setPlayer()
    {
        String msg = "";
        String nextMoveMark = game.getPlayerMark(game.getNextPlayer());
        if (aiMode){
            msg = "Player vs Computer";
        }else{
            msg = "Player vs Player - Next move: " + nextMoveMark;
        }
        lblPlayer.setText(msg);
    }

    public void switchColors(){
        double rand = Math.random() * 365;
        double rand2 = Math.random() * 365;
        double rand3 = Math.random() * 365;
        gridPane.getChildren().forEach(node -> {
            Button b = (Button) node;
            b.setStyle("-fx-color: rgb("+rand+","+rand2+","+rand3 +");");
        });
    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = switch (winner)
            {
                case -1 -> "It's a draw :-(";
                case 1 -> "Player X wins!!!";
                case 0 -> "Player O wins!!!";
                default -> "";
            };
        if(aiMode){
            message = switch (winner)
                {
                    case 1 -> "Computer win! Try again :)";
                    case -1 -> "Draw :O";
                    case 0 -> "some quantum computing i see...";
                    default -> "";
                };
        }
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
            btn.setDisable(false);
            btn.setText("");
        }
    }

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
