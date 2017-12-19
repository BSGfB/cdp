package com.bsgfb.cdp.classloader.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModuleLoader extends ClassLoader {
    private final FileSystem fileSystem;

    public ModuleLoader(final FileSystem fileSystem, final ClassLoader parent) {
        super(parent);
        this.fileSystem = fileSystem;
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        String pathToFile = "/" + name.replace(".", "/") + ".class";
        System.out.println("Path: " + pathToFile);
        try {
            Path pathToClass = fileSystem.getPath(pathToFile);
            byte[] bytes = readClassByPath(pathToClass);

            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }

    private byte[] readClassByPath(final Path path) throws IOException {
        try (InputStream inputStream = Files.newInputStream(path)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = inputStream.read();

            while (data != -1) {
                buffer.write(data);
                data = inputStream.read();
            }

            return buffer.toByteArray();
        }
    }
}
