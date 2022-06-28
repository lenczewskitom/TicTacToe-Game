package com.kodilla.tictactoe;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RankingWindow {
    Stage stage;
    Label ranking;
    Label name;
    Button save;
    String loadRanking;
    Button enterName;
    File rankingList;
    Map<String, Integer> map;
    TextInputDialog textInputDialog;
    Controller controller;
    StackPane pane;
    Scene scene;

    public RankingWindow (Controller controller) {
        this.controller = controller;
        rankingList = new File("ranking.list");
        map = new HashMap<>();
        loadRanking = new String("");
        stage = new Stage();
        stage.setMinHeight(500);
        stage.setMinWidth(300);
        stage.setX(600);
        stage.setY(100);
        textInputDialog = new TextInputDialog("");
        textInputDialog.setHeaderText("Enter your name");
        name = new Label("");
        name.setTranslateY(-200);
        name.setTranslateX(-10);
        ranking = new Label("");
        ranking.setTranslateY(50);
        ranking.setMinSize(150,300);
        ranking.setFont(new Font(15));
        enterName = new Button("Enter name");
        enterName.setTranslateY(-160);
        enterName.setTranslateX(-50);
        enterName.setFont(new Font(15));
        enterName.setOnMouseClicked(event -> enterName());
        save = new Button("Save Score");
        save.setFont(new Font(15));
        save.setTranslateX(50);
        save.setTranslateY(-160);
        save.setOnMouseClicked(event -> saveScore());
        name.setFont(new Font(25));
        pane = new StackPane();
        pane.getChildren().addAll(name, enterName, save, ranking);
        scene = new Scene(pane);
    }

    public void showRanking() {
        stage.setScene(scene);
        stage.setTitle("Ranking");
        stage.show();
        loadRanking = "";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rankingList));
            map = (HashMap<String, Integer>)ois.readObject();
            ois.close();
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(entry -> loadRanking = loadRanking + entry.getKey() + " - " + entry.getValue() + " wins\n");

            ranking.setText(loadRanking);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void enterName() {
        textInputDialog.showAndWait();
        name.setText(textInputDialog.getEditor().getText());
    }

    public void saveScore() {
        map.put(name.getText(), controller.getxWin());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rankingList));
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
