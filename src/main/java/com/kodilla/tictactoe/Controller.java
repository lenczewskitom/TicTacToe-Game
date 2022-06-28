package com.kodilla.tictactoe;


public class Controller {

    private InfoPanel infoPanel;
    EndGameWindow endGameWindow;
    private boolean playerX;
    private boolean isStarted;
    private boolean gameEnd;
    private boolean vsComputer;
    private  boolean computerMoved;
    private int counter = 0;
    private int xWin = 0;
    private  int oWin = 0;
    Controller(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        endGameWindow = new EndGameWindow();
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getxWin() {
        return xWin;
    }

    public boolean isPlayerX() {
        return playerX;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public boolean isVsComputer(){
        return vsComputer;
    }

    public boolean isComputerMoved() {
        return computerMoved;
    }

    public void setComputerMoved(boolean computerMoved) {
        this.computerMoved = computerMoved;
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    public void setPlayerX(boolean playerX) {
        this.playerX = playerX;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public void checkForWin(Tile[][] tiles){
        counter++;
        if (counter < 10) {
            for (int i = 0; i < 8; i++) {
                String line = switch (i) {
                    case 0 -> tiles[0][0].getLabel().getText()+tiles[0][1].getLabel().getText()+tiles[0][2].getLabel().getText();
                    case 1 -> tiles[1][0].getLabel().getText()+tiles[1][1].getLabel().getText()+tiles[1][2].getLabel().getText();
                    case 2 -> tiles[2][0].getLabel().getText()+tiles[2][1].getLabel().getText()+tiles[2][2].getLabel().getText();
                    case 3 -> tiles[0][0].getLabel().getText()+tiles[1][0].getLabel().getText()+tiles[2][0].getLabel().getText();
                    case 4 -> tiles[0][1].getLabel().getText()+tiles[1][1].getLabel().getText()+tiles[2][1].getLabel().getText();
                    case 5 -> tiles[0][2].getLabel().getText()+tiles[1][2].getLabel().getText()+tiles[2][2].getLabel().getText();
                    case 6 -> tiles[0][0].getLabel().getText()+tiles[1][1].getLabel().getText()+tiles[2][2].getLabel().getText();
                    case 7 -> tiles[0][2].getLabel().getText()+tiles[1][1].getLabel().getText()+tiles[2][0].getLabel().getText();
                    default -> null;
                };
                if (line.equals("XXX")){
                    xWin++;
                    infoPanel.updateX(xWin);
                    gameEnd = true;
                    endGameWindow.xWon(vsComputer);
                    break;
                } else if (line.equals("OOO")) {
                    oWin++;
                    infoPanel.updateO(oWin);
                    gameEnd = true;
                    endGameWindow.oWon(vsComputer);
                    break;
                }
            }
        } else {
            gameEnd = true;
            endGameWindow.stalemate();
        }
    }
}


