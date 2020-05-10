package com.barkov.ais.honestlottogame.rules;

import com.barkov.ais.honestlottogame.elements.Element;

public interface IRule {

    public Element applyRule(Element[] elements) throws NoSuchMethodException;
    public String getRuleText();
}
