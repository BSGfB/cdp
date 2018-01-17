package com.bsgfb.cdp.patterns.abstractfactory.dao;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import io.reactivex.Observable;
import io.reactivex.Single;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcPersonDao implements PersonDao {
    private static final String SQL_PERSON_FIND_BY_NAME = "sql.person.findByName";
    private static final String SQL_PERSON_FIND_ALL = "sql.person.findAll";
    private static final String SQL_PERSON_SAVE = "sql.person.save";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final String ID = "id";

    private Properties queries;
    private DataSource dataSource;

    public JdbcPersonDao(DataSource dataSource, Properties queries) {
        this.dataSource = dataSource;
        this.queries = queries;
    }

    @Override
    public void writePerson(final Single<Person> person) {
        person.subscribe(p -> {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement insert = connection.prepareStatement(queries.getProperty(SQL_PERSON_SAVE))) {
                    insert.setString(1, p.getUsername());
                    insert.setString(2, p.getPassword());
                    insert.execute();
                }
            }
        });
    }

    @Override
    public Observable<Person> readPeople() {
        return Observable.create(personObservableEmitter -> {
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(queries.getProperty(SQL_PERSON_FIND_ALL))) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next())
                            personObservableEmitter.onNext(mapToPerson(resultSet));

                        personObservableEmitter.onComplete();
                    }
                }
            }
        });
    }

    @Override
    public Single<Person> readPerson(final String name) {
        return Single.just(name).map(s -> {
            Person person;
            try (Connection connection = dataSource.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(queries.getProperty(SQL_PERSON_FIND_BY_NAME))) {
                    statement.setString(1, name);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        resultSet.next();
                        person = mapToPerson(resultSet);
                    }
                }
            }

            return person;
        });
    }

    private Person mapToPerson(ResultSet resultSet) throws SQLException {
        return Person.builder()
                .id(resultSet.getLong(ID))
                .username(resultSet.getString(USERNAME))
                .password(resultSet.getString(PASSWORD))
                .build();
    }
}
