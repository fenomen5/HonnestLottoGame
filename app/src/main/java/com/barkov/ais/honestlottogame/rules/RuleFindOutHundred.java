package com.barkov.ais.honestlottogame.rules;

import android.util.Log;

import com.barkov.ais.honestlottogame.elements.Element;
import com.barkov.ais.honestlottogame.elements.ElementInteger;
import com.barkov.ais.honestlottogame.elements.ElementString;

import java.util.Random;

public class RuleFindOutHundred extends Rule
{
    private int value = 0;
    public RuleFindOutHundred() {
        this.setRuleText("Guess the number in the range 1-99");
        this.setRuleType(Rule.GUES_NUMBER_OUT_OF_HUNDRED);
        value = generateValue();
    }

    public Element applyRule(Element[] inputValues) {

        Log.d("dbg", "answer is " + value);
        ElementInteger inputValue = (ElementInteger)inputValues[0];

        if (inputValue.getValue().equals(this.value)) {
            return new ElementString("That is the right answer!");
        }
        if (((ElementInteger)inputValue).getValue() > this.value) {
            return new ElementString("Incorrect answer! " +
                    System.getProperty("line.separator") + "The value is smaller than " + inputValue.getValue()) ;
        }
        if (((ElementInteger)inputValue).getValue() < this.value) {
            return new ElementString("Incorrect answer! " +
                    System.getProperty("line.separator")+ "The value is bigger than " + inputValue.getValue());
        }

        return new ElementString("");
    }

    private int generateValue()
    {
        Random generator = new Random();
        int value = (int)generator.nextInt((int)Math.round(99))
                + 1;

        return value;
    }
}
