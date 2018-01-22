package com.bsgfb.cdp.patterns.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class NumberEmployees implements Visitor {
    private final static Logger logger = LogManager.getLogger(NumberEmployees.class);

    @Override
    public void visit(final List<Employee> employee) {
        logger.debug("Number of employees is [{}]", employee.size());
    }
}
