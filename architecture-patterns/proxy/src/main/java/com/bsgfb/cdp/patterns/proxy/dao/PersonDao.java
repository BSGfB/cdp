package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.model.Person;

import java.sql.SQLException;

/**
 * This dao to work with persons
 */
public interface PersonDao {

    /**
     * Reads one specific person by name
     * @param username person name
     * @return person with "name"
     */
    Person readPerson(String username) throws SQLException;
}
