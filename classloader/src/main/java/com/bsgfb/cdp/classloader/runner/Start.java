package com.bsgfb.cdp.classloader.runner;

import com.bsgfb.cdp.classloader.loader.ModuleLoader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class Start {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("classloader/src/main/resources/Module.jar").toAbsolutePath();
        URI uri = URI.create("jar:" + path.toUri().toString());
        FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());

        try {
            Object object = new ModuleLoader(fileSystem, ClassLoader.getSystemClassLoader())
                    .loadClass("com.test.module.model.SimpleLogModule")
                    .newInstance();
            Arrays.stream(object.getClass().getMethods()).filter(method -> "run".equals(method.getName())).findFirst().ifPresent(method -> {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
