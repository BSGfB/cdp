package com.bsgfb.cdp.patterns.abstractfactory.util;

import com.bsgfb.cdp.patterns.abstractfactory.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Json implementation of FileHelper
 * Will convert person items from list to json and from json to person list
 */
public class JsonFileHelper implements FileHelper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonFileHelper() {
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    @Override
    public void toFile(String path, List<Person> todos) throws IOException {
        objectMapper.writeValue(new FileOutputStream(path), todos);
    }

    @Override
    public List<Person> fromFile(String path) throws IOException {
        return objectMapper.readValue(new FileInputStream(path), new TypeReference<List<Person>>() {
        });
    }
}
