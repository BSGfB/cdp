package com.bsgfb.cdp.patterns.proxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class Person {

    private Long id;

    private String username;

    private String password;
}
