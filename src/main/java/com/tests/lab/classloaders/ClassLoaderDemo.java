package com.tests.lab.classloaders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoaderDemo demoObject = new ClassLoaderDemo();
        ClassLoader applicationClassLoader = demoObject.getClass().getClassLoader();
        printClassLoaderDetails(applicationClassLoader);

        // java.sql classes are loaded by platform classloader
        java.sql.Date now = new Date(System.currentTimeMillis());
        ClassLoader platformClassLoder = now.getClass().getClassLoader();
        printClassLoaderDetails(platformClassLoder);

        // java.lang classes are loaded by bootstrap classloader
        ClassLoader bootstrapClassLoder = args.getClass().getClassLoader();
        printClassLoaderDetails(bootstrapClassLoder);

        try {
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void printClassLoaderDetails(ClassLoader classLoader) {
        // bootstrap classloader is represented by null in JVM
        if (classLoader != null) {
            System.out.println("ClassLoader name : " + classLoader.getName());
            System.out.println("ClassLoader class : " + classLoader.getClass().getName());
        } else {
            System.out.println("Bootstrap classloader");
        }
    }
}
