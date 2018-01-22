package com.bsgfb.cdp.patterns.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class SalaryRaise implements Visitor {
    private final static Logger logger = LogManager.getLogger(SalaryRaise.class);

    @Override
    public void visit(final List<Employee> employee) {
        logger.debug("Salary raise:");

        employee.stream()
                .map(Employee::getSalary)
                .collect(groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * 100 / employee.size()))
                .forEach((salary, percent) -> logger.debug("Salary is [{}] percent is [{}]%", salary, percent));
    }
}
