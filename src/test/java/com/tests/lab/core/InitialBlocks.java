package com.tests.lab.core;

public class InitialBlocks {
    private static final int counter;

    static {
        counter = 1;
    }

    {
        if (counter == 1)
            throw new RuntimeException("");
    }

    public InitialBlocks() {

    }
}
