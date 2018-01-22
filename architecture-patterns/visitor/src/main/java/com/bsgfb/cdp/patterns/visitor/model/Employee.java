package com.bsgfb.cdp.patterns.visitor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
public class Employee {
    private String name;
    private BigDecimal salary;
    private Department department;

}
