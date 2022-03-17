package com.tests.lab.design_patterns.factory.factory_method;

public class WindowsButton implements Button {

    public void render() {
        System.out.println("Windows button");
    }

    public void onClick() {
    }
}
