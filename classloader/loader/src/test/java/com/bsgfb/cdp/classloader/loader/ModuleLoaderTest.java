package com.bsgfb.cdp.classloader.loader;

import com.bsgfb.cdp.classloader.model.LanguageModule;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class ModuleLoaderTest {
    private static final String TEST_JAR_PATH = "src/test/resources/TestLanguageModule.jar";
    private static final String CLASS_NAME = "com.bsgfb.cdp.classloader.model.EnglishLanguageModule";

    @Test
    public void findClass() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object obj = createClassLoader().loadClass(CLASS_NAME).newInstance();

        assertTrue(obj instanceof LanguageModule);
    }

    @Test
    public void findExistedClass() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = createClassLoader();
        assertTrue(classLoader.loadClass(CLASS_NAME).newInstance() instanceof LanguageModule);
        assertTrue(classLoader.loadClass(CLASS_NAME).newInstance() instanceof LanguageModule);
    }

    private static ClassLoader createClassLoader() throws IOException {
        URI uri = URI.create("jar:" + Paths.get(TEST_JAR_PATH).toAbsolutePath().toUri().toString());
        FileSystem fileSystem;
        try {
            fileSystem = FileSystems.getFileSystem(uri);
        } catch (FileSystemNotFoundException e) {
            fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        }

        return new ModuleLoader(fileSystem, ClassLoader.getSystemClassLoader());
    }
}