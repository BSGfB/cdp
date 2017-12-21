package com.bsgfb.cdp.classloader.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * The ModuleLoader loads classes from FileSystem
 */
public class ModuleLoader extends ClassLoader {
    private final static Logger logger = LogManager.getLogger(ModuleLoader.class);

    private final FileSystem fileSystem;

    public ModuleLoader(final FileSystem fileSystem, final ClassLoader parent) {
        super(parent);
        this.fileSystem = fileSystem;
    }

    /**
     * Loads classes from inner FileSystem
     *
     * @param name file name
     * @return requested class
     * @throws ClassNotFoundException if class cannot be found
     */
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        logger.debug("finding class: [" + name + "]");

        Class<?> c = findLoadedClass(name);
        if (Objects.nonNull(c)) {
            logger.debug("class: [" + name + "] already have been loaded");

            resolveClass(c);
            return c;
        }

        logger.debug("class: [" + name + "] doesn't exist, so it will be loaded");
        String pathToFile = "/" + name.replace(".", "/") + ".class";
        try {
            Path pathToClass = fileSystem.getPath(pathToFile);
            byte[] bytes = readClassByPath(pathToClass);

            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }

    /**
     * Reads class file to byte array from path
     *
     * @param path to stored file
     * @return class file as byte array
     * @throws IOException if an I/O error occurs
     */
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
