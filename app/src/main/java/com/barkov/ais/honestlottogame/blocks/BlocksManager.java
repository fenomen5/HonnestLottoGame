package com.barkov.ais.honestlottogame.blocks;

import com.barkov.ais.honestlottogame.elements.Element;
import com.barkov.ais.honestlottogame.elements.ElementInteger;

import java.util.Random;

public class BlocksManager {

    public Element[] generateIntNumbers(int blocksCount, int level) {

        Element[] blockValues = new Element[blocksCount];

        for (int i = 0; i < blocksCount; i++) {
            Element generatedValue = this.generateIntValue(level);
            boolean valueExists = false;
            for (Element blockValue: blockValues) {
                if (blockValue.getValue().equals(generatedValue.getValue())) {
                    valueExists = true;
                }
            }
            if (valueExists) {
                i--;
                continue;
            }

            blockValues[i] = generatedValue;
        }

        return blockValues;
    }

    protected Element generateIntValue(int level) {
        Random generator = new Random();
        int value = (int)generator.nextInt((int)Math.round(Math.pow(10, level)-1))
            + (int)Math.round(Math.pow(10, level-1));
        Element element = new ElementInteger(value);
        return element;
    }
}
