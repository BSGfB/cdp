package com.bsgfb.cdp.patterns.abstractfactory.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Person {
    private Long id;
    private String username;
    private String password;
}
