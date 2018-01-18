package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;

public abstract class PersonDaoFactory {

    public static PersonDaoFactory getFactory(DaoType daoType) {
        switch (daoType) {
            case FILE:
                break;
            case JDBC:
                break;
        }

        return null;
    }

    abstract PersonDao createPersonDao();
}
