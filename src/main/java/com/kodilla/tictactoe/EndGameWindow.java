package com.kodilla.tictactoe;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class EndGameWindow {
    Stage stage;
    Label label;
    Button nextGame;
    StackPane pane;
    Scene scene;
    public EndGameWindow(){
        stage = new Stage();
        stage.setMinHeight(150);
        stage.setMinWidth(200);
        label = new Label();
        label.setTranslateY(-30);
        nextGame = new Button("Next Game");
        nextGame.setTranslateY(20);
        nextGame.setFont(new Font(20));
        nextGame.setOnMouseClicked(event -> stage.hide());
        label.setFont(new Font(25));
        pane = new StackPane();
        pane.getChildren().addAll(label, nextGame);
        scene = new Scene(pane);
    }

    public void xWon(boolean vsComputer){
        if (vsComputer) {
            label.setText("Player won");
        } else {
            label.setText("Player X won");
        }
        stage.setScene(scene);
        stage.show();
    }

    public void oWon(boolean vsComputer){
        if (vsComputer) {
            label.setText("Computer won");
        } else {
            label.setText("Player O won");
        }
        stage.setScene(scene);
        stage.show();
    }

    public void stalemate(){
        label.setText("Stalemate");
        stage.setScene(scene);
        stage.show();
    }
}



