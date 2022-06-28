package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Tile {
    private StackPane pane;

    private Controller controller;
    private Label label;
    public Tile(Controller controller) {

        this.controller = controller;

        pane = new StackPane();
        Rectangle rectangle = new Rectangle(150,150);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);

        label = new Label("");
        label.setFont(new Font(40));
        label.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(rectangle, label);

    }
    public void setText(String s) {
            label.setText(s);
    }

    public void clearTile(Tile tile){
        tile.label.setText("");
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public StackPane getPane() {
        return pane;
    }
    public Label getLabel() {
        return label;
    }
}
