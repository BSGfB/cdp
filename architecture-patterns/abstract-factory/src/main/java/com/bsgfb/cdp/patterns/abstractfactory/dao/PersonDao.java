package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * This dao to work with persons
 */
public interface PersonDao {

    /**
     * Save person
     * @param person to save, person id could be null
     */
    void writePerson(final Single<Person> person);

    /**
     * Reads all stored records
     * @return all records
     */
    Observable<Person> readPeople();

    /**
     * Reads one specific person by name
     * @param name person name
     * @return person with "name"
     */
    Single<Person> readPerson(String name);
}
