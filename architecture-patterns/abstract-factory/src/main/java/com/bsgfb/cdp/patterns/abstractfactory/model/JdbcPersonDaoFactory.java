package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.bsgfb.cdp.patterns.abstractfactory.dao.JdbcPersonDao;
import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;

import javax.sql.DataSource;
import java.util.Properties;

public class JdbcPersonDaoFactory extends PersonDaoFactory {

    private DataSource dataSource;
    private Properties queries;

    public JdbcPersonDaoFactory(final DataSource dataSource, final Properties queries) {
        this.dataSource = dataSource;
        this.queries = queries;
    }

    @Override
    public PersonDao createPersonDao() {
        return new JdbcPersonDao(dataSource, queries);
    }
}
