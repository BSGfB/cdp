package com.bsgfb.cdp.patterns.proxy.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T get(ResultSet resultSet) throws SQLException;
}
