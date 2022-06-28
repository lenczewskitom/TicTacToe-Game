package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class StartGameWindow {
    Stage stage;
    Board board;
    Label label;
    Button vsPlayer;
    Button vsComputer;
    StackPane pane;
    Scene scene;

    public StartGameWindow(Board board) {
        this.board = board;
        stage = new Stage();
        stage.setMinHeight(200);
        stage.setMinWidth(350);
        stage.setX(600);
        stage.setY(100);

        label = new Label("Choose game");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font(25));
        label.setTranslateY(20);


        vsPlayer = new Button("Player vs player");
        vsPlayer.setFont(new Font(15));
        vsPlayer.setTranslateX(-80);
        label.setTranslateY(-50);
        vsPlayer.setOnMouseClicked(event -> {
            board.startNewGame();
            stage.hide();
        });
        vsComputer = new Button("Player vs Computer");
        vsComputer.setFont(new Font(15));
        vsComputer.setTranslateX(60);
        label.setTranslateY(-50);
        vsComputer.setOnMouseClicked(event -> {
            board.startGameWithComputer();
            stage.hide();
        });
        pane = new StackPane();
        pane.getChildren().addAll(vsPlayer, vsComputer, label);
        scene = new Scene(pane);
    }

    public void startGame() {
        stage.setScene(scene);
        stage.show();
    }
}
