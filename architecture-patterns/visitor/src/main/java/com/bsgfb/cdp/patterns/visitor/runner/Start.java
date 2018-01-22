package com.bsgfb.cdp.patterns.visitor.runner;

import com.bsgfb.cdp.patterns.visitor.model.*;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Start {
    public static void main(String[] args) {
        ArrayList<Employee> employees = Lists.newArrayList(
                new Employee("N1", new BigDecimal("100"), Department.JAVA_DEPARTMENT),
                new Employee("N2", new BigDecimal("200"), Department.JAVA_DEPARTMENT),
                new Employee("N3", new BigDecimal("100"), Department.JAVA_DEPARTMENT),
                new Employee("N4", new BigDecimal("300"), Department.CPP_DEPARTMENT),
                new Employee("N5", new BigDecimal("0"), Department.DOT_NET_DEPARTMENT));

        ArrayList<Visitor> visitors = Lists.newArrayList(
                new AverageSalary(),
                new NumberEmployees(),
                new NumberOfEmployeesPerDepartment(),
                new SalaryRaise(),
                new TotalSalaries());

        Company company = new Company(employees);

        visitors.forEach(company::accept);
    }
}
