package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Person.PersonBuilder.class)
public class Person {
    private Long id;
    private String username;
    private String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PersonBuilder {
    }

}
