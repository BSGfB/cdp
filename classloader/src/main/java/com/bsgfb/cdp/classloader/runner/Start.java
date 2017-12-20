package com.bsgfb.cdp.classloader.runner;

import com.bsgfb.cdp.classloader.loader.ModuleLoader;
import com.bsgfb.cdp.classloader.model.LanguageModule;
import com.bsgfb.cdp.classloader.util.ScannerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class Start {
    private static final String MODULE_DIR = "classloader/src/main/resources/";
    private static final String CLASS_PACKAGE = "com.test.module.model.";
    private final static Logger logger = LogManager.getLogger(Start.class);

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<String> modules = findModules(MODULE_DIR);

        while (true) {
            try {
                logModules(modules);

                String moduleName = selectModule(modules, scanner);

                LanguageModule languageModule = newInstance(moduleName);

                logger.debug("Say: " + languageModule.sayHelloWorld());

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalStateException e) {
                e.printStackTrace();
            }

            if (isExit(scanner)) return;
        }
    }

    /**
     * Makes numbered module list out
     * @param modules list of modules
     */
    private static void logModules(List<String> modules) {
        logger.debug("======================================");
        for (int i = 0; i < modules.size(); i++)
            logger.debug("[" + i + "] - " + modules.get(i));
        logger.debug("======================================");
    }

    /**
     * Return selected moduleName from modules, using scanner as input tool
     * @param modules list of modules
     * @param scanner
     * @return selected module
     * @throws IllegalStateException if input value is out of range
     */
    private static String selectModule(List<String> modules, Scanner scanner) throws IllegalStateException {
        int selection = ScannerUtil.nextInt(scanner);

        if (selection < 0 || selection >= modules.size())
            throw new IllegalStateException("Input value must be from 0 to" + modules.size());

        String moduleName = modules.get(selection);
        logger.debug("You select: [" + moduleName + "]");

        return moduleName;
    }

    /**
     * Try to find all jar file by pathToModules
     * @param pathToModules
     * @return jar file list from pathToModules folder
     * @throws IOException
     */
    private static List<String> findModules(String pathToModules) throws IOException {
        return Files
                .list(Paths.get(pathToModules).toAbsolutePath())
                .filter(path -> path.getFileName().toString().endsWith(".jar"))
                .map(path -> path.getFileName().toString().replaceAll(".+/", ""))
                .collect(toList());
    }

    private static LanguageModule newInstance(String moduleName) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Path path = Paths.get(MODULE_DIR + moduleName).toAbsolutePath();
        URI uri = URI.create("jar:" + path.toUri().toString());

        FileSystem fileSystem;
        try {
            fileSystem = FileSystems.getFileSystem(uri);
        } catch (FileSystemNotFoundException e) {
            fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        }

        return (LanguageModule) new ModuleLoader(fileSystem, ClassLoader.getSystemClassLoader())
                .loadClass(CLASS_PACKAGE + moduleName.replace(".jar", ""))
                .newInstance();
    }

    private static boolean isExit(Scanner scanner) {
        logger.debug("Exit? (yes/no):");
        return "yes".equalsIgnoreCase(scanner.nextLine());
    }
}
