package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.*;
import java.util.*;

public class Board {
    private StackPane pane;
    private Controller controller;
    private InfoPanel infoPanel;
    private Button vsComputer;
    private Button vsPlayer;
    private Button ranking;
    private File savedGame;
    private Map<String, Integer> map;
    private RankingWindow rankingWindow;
    private Random random = new Random();
    private Tile[][] tiles = new Tile[3][3];
    private Tile[][] loadGame;
    public Board (Controller controller, InfoPanel infoPanel) {
        this.controller = controller;
        this.infoPanel = infoPanel;
        rankingWindow = new RankingWindow(controller);
        savedGame = new File("game.list");
        map = new HashMap<>();

        pane = new StackPane();
        pane.setMinSize(300,400);
        pane.setAlignment(Pos.CENTER);
        pane.setTranslateX(50);
        pane.setTranslateY(150);


        vsComputer = new Button("Player vs Computer");
        vsComputer.setMinSize(130,30);
        vsComputer.setAlignment(Pos.CENTER);
        vsComputer.setTranslateY(10);
        vsComputer.setTranslateX(300);
        vsComputer.setOnMouseClicked(event -> startGameWithComputer());
        pane.getChildren().add(vsComputer);

        vsPlayer = new Button("Player vs player");
        vsPlayer.setMinSize(130,30);
        vsPlayer.setAlignment(Pos.CENTER);
        vsPlayer.setTranslateY(10);
        vsPlayer.setTranslateX(50);
        vsPlayer.setOnMouseClicked(event -> startNewGame());
        pane.getChildren().add(vsPlayer);

        ranking = new Button("Show ranking");
        ranking.setMinSize(130,30);
        ranking.setAlignment(Pos.CENTER);
        ranking.setTranslateY(10);
        ranking.setTranslateX(170);
        ranking.setOnMouseClicked(event -> rankingWindow.showRanking());
        pane.getChildren().add(ranking);

        addTiles();
    }

    public void startGameWithComputer(){
        clearBoard();
        controller.setStarted(true);
        controller.setVsComputer(true);
        controller.setComputerMoved(false);
        controller.setGameEnd(false);
        if (random.nextInt(2) == 0) {
            infoPanel.updateLabel("Player turn");
            controller.setPlayerX(true);
        } else {
            infoPanel.updateLabel("Computer turn");
            controller.setPlayerX(false);
            computerMove();
        }
    }
    public void startNewGame() {
        clearBoard();
        controller.setStarted(true);
        controller.setVsComputer(false);
        if (random.nextInt(2) == 0) {
            infoPanel.updateLabel("Player X turn");
            controller.setPlayerX(true);
        } else {
            infoPanel.updateLabel("Player O turn");
            controller.setPlayerX(false);
            controller.setGameEnd(false);
        }
    }

    public void clearBoard(){
        controller.setCounter(0);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col <3; col++) {
                tiles[row][col].setText("");
            }
        }
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedGame));
            oos.writeObject(tiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void loadGame(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedGame));
            loadGame = (Tile[][]) ois.readObject();
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col <3; col++) {
                tiles[row][col].setText(loadGame[row][col].getLabel().getText());
            }
        }
    }

    public void changePlayer(){
        if (controller.isPlayerX()) {
            if (controller.isVsComputer()) {
                infoPanel.updateLabel("Computer turn");
            } else {
                infoPanel.updateLabel("Player O turn");
            }
            controller.setPlayerX(false);
        } else {
            if (controller.isVsComputer()) {
                infoPanel.updateLabel("Player turn");
            } else {
                infoPanel.updateLabel("Player X turn");
            }
            controller.setPlayerX(true);
        }
    }
    public void addTiles() {

        for (int row = 0; row < 3; row++){
            for (int col = 0; col <3; col++){
                Tile tile = new Tile(controller);
                tile.getPane().setTranslateX(row * 150 + 35);
                tile.getPane().setTranslateY(col * 150 + 120);
                tiles[row][col] = tile;
                pane.getChildren().add(tile.getPane());
                tile.getPane().setOnMouseClicked(event -> {
                    if (tile.getLabel().getText().isEmpty() && controller.isStarted() && !controller.isGameEnd()) {
                        if (controller.isPlayerX()) {
                            tile.setText("X");
                        } else {
                            tile.setText("O");
                        }
                        controller.checkForWin(tiles);
                        if (!controller.isGameEnd()) {
                            changePlayer();
                            if (controller.isVsComputer()){
                                computerMove();
                            }
                        }
                    }
                });
            }
        }
    }

    public void computerMove(){
        do {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (tiles[row][col].getLabel().getText().equals("")) {
                if (controller.isPlayerX()) {
                    tiles[row][col].setText("X");
                } else {
                    tiles[row][col].setText("O");
                }
                controller.setComputerMoved(true);
            }
        } while (!controller.isComputerMoved());
        controller.checkForWin(tiles);
        controller.setComputerMoved(false);
        if (!controller.isGameEnd()) {changePlayer();}
    }

    public StackPane getPane() {
        return pane;
    }

}
