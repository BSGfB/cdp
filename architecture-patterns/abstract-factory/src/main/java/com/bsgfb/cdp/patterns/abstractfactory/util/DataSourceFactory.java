package com.bsgfb.cdp.patterns.abstractfactory.util;

import javax.sql.DataSource;
import java.util.Properties;

public interface DataSourceFactory {
    DataSource createDataSource(Properties properties);
}
