package com.barkov.ais.honestlottogame.elements;

public class ElementInteger implements Element<Integer>{

    private int value;
    private String elementId;

    public ElementInteger(Integer value) {
        this.value = value;
    }

    public Integer getValue()
    {
        return this.value;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public String toString()
    {
        return ""+this.elementId + " " + this.value;
    }
}
