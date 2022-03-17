package com.tests.lab.design_patterns.factory.factory_method;

public class HtmlCheckBox implements CheckBox {

    @Override
    public void render() {
        System.out.println("Html checkbox");
    }

    @Override
    public void onClick() {

    }
}
