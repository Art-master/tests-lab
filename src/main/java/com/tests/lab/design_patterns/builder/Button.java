package com.tests.lab.design_patterns.builder;

public record Button(int color, int size, String text) {
    public void print() {
        System.out.println("color = " + color);
        System.out.println("size = " + size);
        System.out.println("text = " + text);
    }
}
