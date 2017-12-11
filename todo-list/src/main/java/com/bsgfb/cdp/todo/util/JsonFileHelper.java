package com.bsgfb.cdp.todo.util;

import com.bsgfb.cdp.todo.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JsonFileHelper implements FileHelper {
    private ObjectMapper objectMapper = new ObjectMapper();

    public JsonFileHelper() {
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    @Override
    public void toFile(String path, List<Todo> todos) throws IOException {
        objectMapper.writeValue(new FileOutputStream(path), todos);
    }

    @Override
    public List<Todo> fromFile(String path) throws IOException {
        return objectMapper.readValue(new FileInputStream(path), new TypeReference<List<Todo>>(){});
    }
}
