package com.tests.lab.design_patterns.factory_method;

public class WindowsDialog extends Dialog {

    @Override
    Button createButton() {
        return new WindowsButton();
    }
}
