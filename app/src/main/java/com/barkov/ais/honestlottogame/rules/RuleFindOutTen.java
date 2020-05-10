package com.barkov.ais.honestlottogame.rules;

import android.util.Log;

import com.barkov.ais.honestlottogame.elements.Element;
import com.barkov.ais.honestlottogame.elements.ElementInteger;
import com.barkov.ais.honestlottogame.elements.ElementString;

import java.util.Random;

public class RuleFindOutTen extends Rule
{
    private int value = 0;
    public RuleFindOutTen() {
        this.setRuleText("Guess the number in the range 1-10");
        this.setRuleType(Rule.GUESS_NUMBER_OUT_OF_TEN);
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
        int value = (int)generator.nextInt((int)Math.round(Math.pow(10, 1)))
                + 1;

        return value;
    }
}
