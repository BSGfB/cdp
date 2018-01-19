package com.bsgfb.cdp.patterns.abstractfactory.model;

import com.bsgfb.cdp.patterns.abstractfactory.dao.FilePersonDao;
import com.bsgfb.cdp.patterns.abstractfactory.dao.PersonDao;
import com.bsgfb.cdp.patterns.abstractfactory.util.FileHelper;
import com.bsgfb.cdp.patterns.abstractfactory.util.JsonFileHelper;

import java.io.IOException;

public class FilePersonDaoFactory extends PersonDaoFactory {
    private FileHelper FILE_HELPER = new JsonFileHelper();

    private String path;
    private Integer backupTime;

    public FilePersonDaoFactory(final String path, final Integer backupTime) {
        this.path = path;
        this.backupTime = backupTime;
    }

    @Override
    public PersonDao createPersonDao() {
        PersonDao personDao = null;
        try {
            personDao = new FilePersonDao(path, FILE_HELPER, backupTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personDao;
    }
}
