package com.tests.lab.design_patterns.factory.factory_method;

public class HtmlDialogFactory extends DialogFactory {

    @Override
    Button createButton() {
        return new HtmlButton();
    }

    @Override
    CheckBox createCheckBox() {
        return new HtmlCheckBox();
    }
}
