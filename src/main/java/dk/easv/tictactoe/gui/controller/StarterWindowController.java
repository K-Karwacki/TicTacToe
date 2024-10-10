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
        public Button btnStartAI;


        public void onBtnStartAi(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/TicTacAiView.fxml"));
                Parent scene = loader.load();
                Stage stage = new Stage();

                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.setTitle("TicTacToe PvC");
                stage.centerOnScreen();

                stage.show();

        }
        public void onBtnStart(ActionEvent event) throws IOException {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/TicTacView.fxml"));
                Parent scene = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(scene));
                stage.setResizable(false);
                stage.setTitle("TicTacToe PvP");
                stage.centerOnScreen();

                stage.show();

        }

}



