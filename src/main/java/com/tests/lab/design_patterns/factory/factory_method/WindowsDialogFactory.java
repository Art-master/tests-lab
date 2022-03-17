package com.tests.lab.design_patterns.factory.factory_method;

public class WindowsDialogFactory extends DialogFactory {

    @Override
    Button createButton() {
        return new WindowsButton();
    }

    @Override
    CheckBox createCheckBox() {
        return new WindowsCheckBox();
    }
}
