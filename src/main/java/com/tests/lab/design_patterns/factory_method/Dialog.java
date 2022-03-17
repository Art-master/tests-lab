package com.tests.lab.design_patterns.factory_method;

abstract class Dialog {

    public void renderDialog() {
        Button button = createButton();
        button.render();
    }

    abstract Button createButton();
}
