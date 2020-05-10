package com.barkov.ais.honestlottogame.properties;

public class GameAttempts {

    private int attempts;

    public GameAttempts() {
        this.attempts = 0;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public void increaseAttempts()
    {
        this.attempts++;
    }

}
