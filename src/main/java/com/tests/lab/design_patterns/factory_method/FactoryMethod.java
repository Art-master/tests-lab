package com.tests.lab.design_patterns.factory_method;

public class FactoryMethod {
    public static void execute() {
        Dialog windowsDialog = new WindowsDialog();
        windowsDialog.renderDialog();
    }
}
