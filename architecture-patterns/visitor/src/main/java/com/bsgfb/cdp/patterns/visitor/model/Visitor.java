package com.bsgfb.cdp.patterns.visitor.model;

import java.util.List;

public interface Visitor {
    void visit(List<Employee> employee);
}
