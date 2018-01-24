package com.bsgfb.cdp.patterns.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * Calculates total salary
 */
public class TotalSalaries implements Visitor {
    private final static Logger logger = LogManager.getLogger(TotalSalaries.class);

    @Override
    public void visit(final List<Employee> employee) {
        String total = employee.stream()
                .map(Employee::getSalary).reduce(new BigDecimal(0.0), (BigDecimal::add))
                .toString();

        logger.debug("Total salaries is [{}]", total);
    }
}
