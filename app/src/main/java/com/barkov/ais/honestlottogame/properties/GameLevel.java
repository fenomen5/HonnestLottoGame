package com.barkov.ais.honestlottogame.properties;

public class GameLevel {

    public final int LEVEL_DEFAULT_DISPENSE_TIMES = 3;
    private int level;

    /*
    the number of dispense times of the level
     */
    private int levelDispenseCount;

    private int levelDispensedCount;

    public GameLevel(int gameLevel, int ... dispenseTimes) {
        this.setLevel(gameLevel);
        this.levelDispenseCount = dispenseTimes.length > 0
                ? (int)dispenseTimes[0]
                : this.LEVEL_DEFAULT_DISPENSE_TIMES;
        this.levelDispensedCount = 0;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int dispenseLevel()
    {
        if (this.levelDispensedCount == this.levelDispenseCount*this.level) {
            this.level++;
            this.levelDispensedCount = 0;

        } else {
            this.levelDispensedCount++;
        }

        return this.level;
    }
}
