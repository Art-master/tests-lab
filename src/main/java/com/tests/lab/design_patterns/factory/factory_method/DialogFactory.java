package com.tests.lab.design_patterns.factory.factory_method;

abstract class DialogFactory {
    abstract Button createButton();
    abstract CheckBox createCheckBox();
}
