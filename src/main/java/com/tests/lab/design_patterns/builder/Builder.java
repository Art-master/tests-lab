package com.tests.lab.design_patterns.builder;

public class Builder {

    public static void execute() {
        ButtonBuilder builder = new ButtonBuilder();
        builder.setColor(0x44444);
    }
}
