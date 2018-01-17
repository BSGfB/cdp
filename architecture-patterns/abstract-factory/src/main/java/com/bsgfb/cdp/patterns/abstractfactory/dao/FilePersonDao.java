package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.bsgfb.cdp.patterns.abstractfactory.util.FileHelper;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FilePersonDao implements PersonDao {

    private String path;
    private FileHelper fileHelper;
    private List<Person> people;

    public FilePersonDao(final String path, FileHelper fileHelper, final Integer backupPeriod) throws IOException {
        this.path = path;
        this.fileHelper = fileHelper;

        this.people = fileHelper.fromFile(path);

        Observable.interval(backupPeriod, TimeUnit.SECONDS).subscribe(this::createBackup);
    }

    @Override
    public void writePerson(final Single<Person> person) {
        person.subscribe(p -> people.add(p));
    }

    @Override
    public Observable<Person> readPeople() {
        return Observable.fromIterable(people);
    }

    @Override
    public Single<Person> readPerson(final String name) {
        return Single.just(people.stream().filter(person -> Objects.equals(person.getUsername(), name)).findFirst().get());
    }

    private void createBackup(Long val) throws IOException {
        fileHelper.toFile(path, people);
    }
}
