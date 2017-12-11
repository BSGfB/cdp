package com.bsgfb.cdp.todo.service;

import org.junit.Test;

public class TodoServiceImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void todoServiceImplConstructorNull () {
        new TodoServiceImpl(null);
    }

}