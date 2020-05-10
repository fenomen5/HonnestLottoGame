package com.barkov.ais.honestlottogame.rules;

import java.util.Random;

public class RulesFactory {

    public static final int GUESS_NUMBER_OUT_OF_TEN = 5;
    public static final int GUES_NUMBER_OUT_OF_FIFTY = 6;
    public static final int GUES_NUMBER_OUT_OF_HUNDRED = 7;

    private static String[] ruleClasses = new String[8];

    public RulesFactory() {
        ruleClasses [GUESS_NUMBER_OUT_OF_TEN] = RuleFindOutTen.class.getName();
        ruleClasses[GUES_NUMBER_OUT_OF_FIFTY] = RuleFindOutFifty.class.getName();
        ruleClasses[GUES_NUMBER_OUT_OF_HUNDRED] = RuleFindOutHundred.class.getName();
    }

    /**
     * Получить правило
     * @param gameType
     * @return
     */
    public Object getRule(int gameType)
    {
        Class ruleClass;
        Object rule = null;
        try {
            ruleClass = Class.forName(ruleClasses[gameType]);
            rule = ruleClass.getConstructor().newInstance();
        } catch (Exception e) {
            //<editor-fold desc="Description">
            //throw new ClassNotFoundException("class" + ruleClasses[gameType] + "not found");
            //</editor-fold>
        }
        return rule;
    }

    public Object getRandomRule() throws ClassNotFoundException {

        Random generator  = new Random();
        int index = generator.nextInt(this.ruleClasses.length);
        Class ruleClass;
        Object rule;
        try {
            ruleClass = Class.forName(ruleClasses[index]);
            rule = ruleClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new ClassNotFoundException("class" + ruleClasses[index] + "not found");
        }
        return rule;
    }
}
