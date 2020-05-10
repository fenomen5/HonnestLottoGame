package com.barkov.ais.honestlottogame.elements;

public class ElementString implements Element<Integer>{

    private String value;
    private String elementId;

    public ElementString(String value) {
        this.value = value;
    }

    public String getValue()
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
