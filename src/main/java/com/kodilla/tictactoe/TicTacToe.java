package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class TicTacToe extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        primaryStage.getIcons().add(new Image("board.png"));
        InfoPanel infoPanel = new InfoPanel();
        Controller controller = new Controller(infoPanel);
        Board board = new Board(controller, infoPanel);

        root.getChildren().add(infoPanel.getPane());
        root.getChildren().add(board.getPane());

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(new Scene(root, 470, 650));
        primaryStage.show();

    }
}
