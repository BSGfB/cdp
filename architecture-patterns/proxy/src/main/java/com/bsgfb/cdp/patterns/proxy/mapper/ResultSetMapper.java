package com.bsgfb.cdp.patterns.proxy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class mapper to convert data from ResultSet to T class
 * @param <T>
 */
public interface ResultSetMapper<T> {
    T get(ResultSet resultSet) throws SQLException;
}
