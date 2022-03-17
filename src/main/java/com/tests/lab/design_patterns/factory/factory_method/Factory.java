package com.tests.lab.design_patterns.factory.factory_method;

public class Factory {

    public static void execute() {
        DialogFactory windowsDialog = new WindowsDialogFactory();
        Button button = windowsDialog.createButton();
        CheckBox checkBox = windowsDialog.createCheckBox();

        button.render();
        checkBox.render();
    }
}
