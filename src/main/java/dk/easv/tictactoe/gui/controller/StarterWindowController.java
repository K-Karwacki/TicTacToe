package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StarterWindowController {


        public Button btnStart;

        public void onBtnStart(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/TicTacView.fxml"));
                Parent scene = loader.load();
                Stage Stage = new Stage();
                javafx.stage.Stage stage = new Stage();
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.setTitle("Tic Tac Toe");
                stage.centerOnScreen();

                stage.show();

        }
}



