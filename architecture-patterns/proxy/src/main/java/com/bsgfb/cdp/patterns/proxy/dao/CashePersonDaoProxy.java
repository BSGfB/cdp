package com.bsgfb.cdp.patterns.proxy.dao;

import com.bsgfb.cdp.patterns.proxy.model.Person;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CashePersonDaoProxy implements PersonDao {
    private static final Logger LOGGER = LogManager.getLogger(CashePersonDaoProxy.class);

    LoadingCache<String, Person> cache;

    public CashePersonDaoProxy(final DataSource dataSource, final Properties queries) {
        PersonDao personDao = new JdbcPersonDao(queries, dataSource);

        cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Person>() {
                    @Override
                    public Person load(final String key) throws Exception {
                        LOGGER.debug("CacheLoader load with key [{}]", key);
                        return personDao.readPerson(key);
                    }
                });
    }

    @Override
    public Person readPerson(final String username) throws SQLException {
        LOGGER.debug("readPerson by username [{}]", username);
        return cache.getUnchecked(username);
    }
}
