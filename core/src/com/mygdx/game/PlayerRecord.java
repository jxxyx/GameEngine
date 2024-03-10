package com.mygdx.game;

public class PlayerRecord {
    private String playerName;
    private double playTime;

    public PlayerRecord(String playerName, double playTime) {
        this.playerName = playerName;
        this.playTime = playTime;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getPlayTime() {
        return playTime;
    }
}
