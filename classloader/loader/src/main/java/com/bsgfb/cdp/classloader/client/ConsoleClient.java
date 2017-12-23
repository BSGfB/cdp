package com.bsgfb.cdp.classloader.client;

import com.bsgfb.cdp.classloader.loader.ModuleLoader;
import com.bsgfb.cdp.classloader.model.LanguageModule;
import com.bsgfb.cdp.classloader.util.UserInput;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * ConsoleClient client to work with classloader
 */
public class ConsoleClient {
    private final static Logger logger = LogManager.getLogger(ModuleLoader.class);
    private static final String PACKAGE = "com/bsgfb/cdp/classloader/model";

    private UserInput input;
    private List<Path> modules;
    private ClassLoader classLoader = null;
    private List<String> classNames;

    /**
     * Creates client to manage classloader
     *
     * @param input            to get input values
     * @param pathToModulesDir path, where modules(jar files) are stored
     * @throws IOException if, modules cannot be loaded
     */
    public ConsoleClient(final UserInput input, final String pathToModulesDir) throws IOException {
        this.input = input;
        this.modules = findModules(pathToModulesDir);
    }

    /**
     * Main method to run client
     * <p>
     * Available menu items: {@link com.bsgfb.cdp.classloader.client.ConsoleClient.GlobalMenuItem}
     */
    public void run() {
        while (true) {
            try {
                switch (showGlobalMenu()) {
                    case EXIT:
                        return;
                    case SHOW_MODULES:
                        showModules(modules);
                        break;
                    case SELECT_MODULE:
                        showModules(modules);
                        selectModule();
                        break;
                    case EXECUTE:
                        execute();
                        break;
                }
            } catch (IllegalArgumentException e) {
                logger.debug("Wrong input");
            } catch (IllegalStateException e) {
                logger.debug(e.getMessage());
            } catch (IllegalAccessException | IOException | InstantiationException | ClassNotFoundException e) {
                System.out.println("Class not found");
            }
        }
    }

    /**
     * Select new module to load
     *
     * @throws IOException if io exception occurs
     */
    private void selectModule() throws IOException {
        int i = input.nextInt();
        if (i < 0 || i >= modules.size())
            throw new IllegalStateException("Selected index doesn't exist");
        logger.debug("Selected [" + i + "]");

        loadNewClassLoader(modules.get(i));
    }


    /**
     * Loads new ClassLoader and classNames from jar
     *
     * @param path to module (jar file)
     * @throws IOException if io exception occurs
     */
    private void loadNewClassLoader(Path path) throws IOException {
        URI uri = URI.create("jar:" + path.toUri().toString());

        FileSystem fileSystem;
        try {
            fileSystem = FileSystems.getFileSystem(uri);
        } catch (FileSystemNotFoundException e) {
            fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
        }

        this.classNames = Files.list(fileSystem.getPath(PACKAGE))
                .filter(path1 -> path1.getName(path1.getNameCount() - 1).toString().endsWith(".class"))
                .map(path1 -> path1.toString().replaceAll("(^/)|(.class$)", "").replaceAll("/", "."))
                .collect(toList());

        this.classLoader = new ModuleLoader(fileSystem, ClassLoader.getSystemClassLoader());
        logger.debug("New module is loaded");
    }

    /**
     * Shows global menu and returns selected menu item
     *
     * @return selected menu item
     * @throws IllegalArgumentException if selected wrong menu item
     */
    private GlobalMenuItem showGlobalMenu() throws IllegalArgumentException {
        List<GlobalMenuItem> globalMenus = Arrays.asList(GlobalMenuItem.values());
        for (int i = 0; i < globalMenus.size(); i++)
            logger.debug("[" + i + "] - " + globalMenus.get(i));

        logger.debug("Select: ");
        int i = input.nextInt();
        GlobalMenuItem byId = GlobalMenuItem.getById(i);
        logger.debug("Selected: " + byId);

        return byId;
    }

    /**
     * Show numbered jar files.
     * <p>
     * Files indexes starts from 0 to modules size - 1
     *
     * @param paths list of path to jar files
     */
    private void showModules(List<Path> paths) {
        logger.debug("======================================");
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.get(i);
            logger.debug("[" + i + "] - " + path.getName(path.getNameCount() - 1));
        }
        logger.debug("======================================");
    }

    /**
     * Try to find all jar file by pathToModules
     *
     * @param pathToModules relative or full path to jar file
     * @return jar file list from pathToModules folder
     * @throws IOException if io exceptions occurs
     */
    private List<Path> findModules(String pathToModules) throws IOException {
        return Files
                .list(Paths.get(pathToModules).toAbsolutePath())
                .filter(path -> path.getFileName().toString().endsWith(".jar"))
                .collect(toList());
    }

    /**
     * Execute currentLanguageModule module, otherwise throw exception if currentLanguageModule is null
     *
     * @throws IllegalStateException if some error occurs
     */
    private void execute()
            throws IllegalStateException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (this.classLoader == null)
            throw new IllegalStateException("classLoader doesn't loaded!");

        for (int i = 0; i < classNames.size(); i++)
            logger.debug("[" + i + "] - " + classNames.get(i));

        int i = input.nextInt();
        if (i < 0 || i >= classNames.size())
            throw new IllegalStateException("Index doesn't exist");

        String s = classNames.get(i);
        logger.debug("Selected: [" + i + "] - [" + s + "]");
        logger.debug("Module say: " + ((LanguageModule) classLoader.loadClass(s).newInstance()).sayHelloWorld());
    }


    /**
     * Class to manage main menu
     */
    public enum GlobalMenuItem {
        /**
         * Terminate application
         */
        EXIT(0),
        /**
         * Show all available modules
         */
        SHOW_MODULES(1),
        /**
         * Select one of the available
         */
        SELECT_MODULE(2),

        /**
         * Execute module
         */
        EXECUTE(3);

        private int id;

        GlobalMenuItem(final int id) {
            this.id = id;
        }

        public static GlobalMenuItem getById(int id) throws IllegalArgumentException {
            return Arrays
                    .stream(values())
                    .filter(globalMenuItem -> globalMenuItem.id == id)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

        public int getId() {
            return id;
        }
    }
}
