package com.tests.lab.design_patterns.factory.factory_method;

public class WindowsCheckBox implements CheckBox{

    @Override
    public void render() {
        System.out.println("Windows checkbox");
    }

    @Override
    public void onClick() {

    }
}
