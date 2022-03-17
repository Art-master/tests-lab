package com.tests.lab.classloaders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FromFileClassloader extends ClassLoader {
    @Override
    public Class<?> findClass(String samplename) throws ClassNotFoundException {
        byte[] b = customLoadClassFromFile(samplename);
        return defineClass(samplename, b, 0, b.length);
    }

    private byte[] customLoadClassFromFile(String demofilename)  {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream(
                demofilename.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inStream.read()) != -1 ) {
                bStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = bStream.toByteArray();
        return buffer;
    }
}
