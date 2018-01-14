package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.model.Person;

import java.sql.SQLException;

public interface PersonDao {
    Person readPerson(String username) throws SQLException;
}
