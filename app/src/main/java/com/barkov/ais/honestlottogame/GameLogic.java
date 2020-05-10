package com.barkov.ais.honestlottogame;

import android.util.Log;

import com.barkov.ais.honestlottogame.elements.Element;
import com.barkov.ais.honestlottogame.elements.ElementInteger;
import com.barkov.ais.honestlottogame.elements.ElementString;
import com.barkov.ais.honestlottogame.properties.GameAttempts;
import com.barkov.ais.honestlottogame.properties.GameChances;
import com.barkov.ais.honestlottogame.properties.GameLevel;
import com.barkov.ais.honestlottogame.properties.GameScore;
import com.barkov.ais.honestlottogame.rules.IRule;
import com.barkov.ais.honestlottogame.rules.Rule;
import com.barkov.ais.honestlottogame.rules.RulesFactory;

public class GameLogic {

    private GameLevel level;
    private GameScore score;
    private GameAttempts attempts;
    private GameChances chances;
    private RulesFactory rulesFactory;
    private IRule currentRule;
    private String lastResponse;
    private final int CHANGE_RULE_START_LEVEL = 3;

    public GameLogic() {

        this.rulesFactory = new RulesFactory();

        this.level = new GameLevel(1);
        this.score = new GameScore();
        this.attempts = new GameAttempts();
        this.chances = new GameChances();
        this. lastResponse = "";
        this.initRound();
    }

    protected boolean initRound()
    {
        IRule rule;
        try {
            this.currentRule = (IRule)rulesFactory.getRandomRule();
        } catch (ClassNotFoundException e) {
            //Toast.makeText(this, "unable to load rule class", Toast.LENGTH_LONG);
            return false;
        }

        return true;
    }

    public boolean evaluateChoice(int value, int score)
    {
        Element element = new ElementInteger(value);
        Element[] elements = new Element[]{element};

        Element result;
        try {
            result = currentRule.applyRule(elements);
            lastResponse = result.getValue().toString();
        } catch (NoSuchMethodException e) {
            return false;
        }

        this.attempts.increaseAttempts();
        if (result.getValue().equals("That is the right answer!")) {
            this.score.increaseScore(score);

            return true;
        }

        this.chances.decreaseChances();
        return false;
    }

    public GameLevel getLevel() {
        return level;
    }

    public GameScore getScore() {
        return score;
    }

    public boolean decreaseScore(int value) {
        this.score.decreaseScore(value);
        if (this.score.getScore() == 0) {
            return false;
        }

        return  true;
    }

    public GameAttempts getAttempts() {
        return attempts;
    }

    public GameChances getChances() {
        return chances;
    }

    public IRule getCurrentRule() {
        return currentRule;
    }

    public boolean setGameType(int gameType) {

        RulesFactory rfactory = new RulesFactory();
        currentRule = (IRule)rfactory.getRule(gameType);

        if (gameType == Rule.GUES_NUMBER_OUT_OF_FIFTY) {
            this.chances.setChances(6);
        }

        if (gameType == Rule.GUES_NUMBER_OUT_OF_HUNDRED) {
            this.chances.setChances(8);
        }

        return true;
    }

    public boolean checkGameFinished()
    {
        if (this.chances.getChances() == 0) {
            return true;
        }

        return false;
    }

    public String getLastResponse() {
        return lastResponse;
    }

    public void resetChanses()
    {
        this.chances.resetChances();
    }
}
