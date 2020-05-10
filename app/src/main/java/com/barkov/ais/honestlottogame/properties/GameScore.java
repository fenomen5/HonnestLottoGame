package com.barkov.ais.honestlottogame.properties;

public class GameScore {

    private int score;

    public GameScore() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int value) {
        this.score += value;
    }

    public void decreaseScore(int value) {
        if (value > this.score) {
            this.score = 0;
        } else {
            this.score -= value;
        }
    }

}
