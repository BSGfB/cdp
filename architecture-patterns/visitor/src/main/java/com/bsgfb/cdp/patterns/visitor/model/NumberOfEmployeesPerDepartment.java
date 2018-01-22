package com.bsgfb.cdp.patterns.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class NumberOfEmployeesPerDepartment implements Visitor {
    private final static Logger logger = LogManager.getLogger(NumberOfEmployeesPerDepartment.class);

    @Override
    public void visit(final List<Employee> employee) {
        logger.debug("Number of employees per department:");
        employee.stream()
                .collect(groupingBy(Employee::getDepartment, Collectors.counting()))
                .forEach((department, count) ->
                        logger.debug("department is [{}] contains [{}] employee", department, count));
    }
}
