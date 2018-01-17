package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.google.common.collect.ImmutableList;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface PersonDao {
    void writePerson(final Single<Person> person);

    Observable<Person> readPeople();

    Single<Person> readPerson(String name);
}
