package com.bsgfb.cdp.patterns.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AverageSalary implements Visitor {
    private final static Logger logger = LogManager.getLogger(AverageSalary.class);

    @Override
    public void visit(final List<Employee> employee) {
        employee.stream()
                .mapToDouble(e -> e.getSalary().doubleValue())
                .average()
                .ifPresent(v -> logger.debug("Average salary is [{}]", v));
    }
}
