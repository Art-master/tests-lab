package com.tests.lab.design_patterns.factory_method;

public class HtmlDialog extends Dialog {

    @Override
    Button createButton() {
        return new HtmlButton();
    }
}
