package com.tests.lab.design_patterns.builder;

public class ButtonBuilder {
    int color;
    int size;
    String text;

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
