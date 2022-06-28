package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class InfoPanel {
    private StackPane pane;
    private Label label;
    private Label score;
    private Label labelX;
    private Label labelO;

    public InfoPanel() {
        pane = new StackPane();
        pane.setMinSize(400,100);
        pane.setTranslateX(50);
        pane.setTranslateY(100);

        label = new Label("Tic Tac Toe");
        label.setMinSize(230,70);
        label.setFont(new Font(35));
        label.setAlignment(Pos.CENTER);
        label.setTranslateY(-70);
        label.setTranslateX(170);
        pane.getChildren().add(label);

        score = new Label("Score");
        score.setMinSize(230,70);
        score.setFont(new Font(35));
        score.setAlignment(Pos.CENTER);
        score.setTranslateY(-30);
        score.setTranslateX(170);
        pane.getChildren().add(score);

        labelX = new Label("X - 0");
        labelX.setMinSize(230,70);
        labelX.setFont(new Font(35));
        labelX.setAlignment(Pos.CENTER);
        labelX.setTranslateY(10);
        labelX.setTranslateX(50);
        pane.getChildren().add(labelX);

        labelO = new Label("O - 0");
        labelO.setMinSize(230,70);
        labelO.setFont(new Font(35));
        labelO.setAlignment(Pos.CENTER);
        labelO.setTranslateY(10);
        labelO.setTranslateX(280);
        pane.getChildren().add(labelO);

    }

    public void updateLabel(String s) {
        label.setText(s);
    }

    public void updateX(int xWin) {
        labelX.setText("X - " + xWin);
    }

    public void updateO(int oWin) {
        labelO.setText("O - " + oWin);
    }

    public StackPane getPane() {
        return pane;
    }


}
