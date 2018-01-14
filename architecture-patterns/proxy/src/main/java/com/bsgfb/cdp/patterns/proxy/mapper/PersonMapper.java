package com.bsgfb.cdp.patterns.proxy.mapper;

import com.bsgfb.cdp.patterns.proxy.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements ResultSetMapper<Person> {

    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String ID = "id";

    @Override
    public Person get(final ResultSet resultSet) throws SQLException {
        resultSet.next();
        return Person.builder()
                .id(resultSet.getLong(ID))
                .username(resultSet.getString(USERNAME))
                .password(resultSet.getString(PASSWORD))
                .build();
    }
}
