package com.bsgfb.cdp.patterns.visitor.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class Company implements Element {

    private List<Employee> employees;

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(employees);
    }
}
