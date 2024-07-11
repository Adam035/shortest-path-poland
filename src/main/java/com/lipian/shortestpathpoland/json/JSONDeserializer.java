package com.lipian.shortestpathpoland.json;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
public class JSONDeserializer {
    private String filePath;

    public <T> List<T> deserializeList(Class<T> class_) {
        Type type = TypeToken.getParameterized(List.class, class_).getType();
        try (FileReader reader = new FileReader(filePath)) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
    }
}
