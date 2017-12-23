package com.bsgfb.cdp.classloader.client;

import com.bsgfb.cdp.classloader.util.UserInput;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.easymock.EasyMock.*;

public class ConsoleClientTest {
    private static final String MODULE_DIR = "src/test/resources/";

    private ConsoleClient consoleClient;
    private UserInput userInput;

    @Before
    public void setUp() throws IOException {
        userInput = EasyMock.createNiceMock(UserInput.class);
        consoleClient = new ConsoleClient(userInput, MODULE_DIR);
    }

    @After
    public void afterTestVerify() {
        replay(userInput);
        consoleClient.run();
        verify(userInput);
    }

    @Test
    public void exitFlowTest() {
        expect(userInput.nextInt()).andReturn(ConsoleClient.GlobalMenuItem.EXIT.getId());
    }

    @Test
    public void showModules() {
        expect(userInput.nextInt()).andReturn(ConsoleClient.GlobalMenuItem.SHOW_MODULES.getId());
    }

    @Test
    public void selectModules() {
        expect(userInput.nextInt())
                .andReturn(ConsoleClient.GlobalMenuItem.SELECT_MODULE.getId())
                .andReturn(0);
    }

    @Test
    public void executeModule() {
        expect(userInput.nextInt())
                .andReturn(ConsoleClient.GlobalMenuItem.SELECT_MODULE.getId())
                .andReturn(0)
                .andReturn(ConsoleClient.GlobalMenuItem.EXECUTE.getId())
                .andReturn(0);
    }
}