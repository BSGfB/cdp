package com.bsgfb.cdp.patterns.visitor.model;

public interface Element {
    void accept(Visitor visitor);
}
