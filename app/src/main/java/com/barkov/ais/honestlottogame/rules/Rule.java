package com.barkov.ais.honestlottogame.rules;

import com.barkov.ais.honestlottogame.elements.Element;

public class Rule implements IRule{

    public static final int GUESS_NUMBER_OUT_OF_TEN = 5;
    public static final int GUES_NUMBER_OUT_OF_FIFTY = 6;
    public static final int GUES_NUMBER_OUT_OF_HUNDRED = 7;

    private String ruleText;
    private Integer ruleType;

    public Rule() {}

    public String getRuleText() {
        return ruleText;
    }

    public void setRuleText(String ruleText) {
        this.ruleText = ruleText;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Element applyRule(Element[] elements) throws NoSuchMethodException {
       throw new NoSuchMethodException("Method not implemented");
    };

}
