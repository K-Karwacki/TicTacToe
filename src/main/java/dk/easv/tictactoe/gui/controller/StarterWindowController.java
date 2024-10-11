package dk.easv.tictactoe.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
                Stage stage = new Stage();
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.setTitle("TicTacToe");
                stage.centerOnScreen();

                stage.show();

                Stage currentStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                currentStage.close();


        }

}



