package com.barkov.ais.honestlottogame.elements;

public interface Element<T> {

    public Object getValue();
    public void setElementId(String elementId);
    public String getElementId();
}

