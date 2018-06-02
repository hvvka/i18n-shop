package com.hania.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
class ItemsProvider {

    Map<ItemType, Integer> getItems(File storage) {
        if (!storage.exists() || storage.length() == 0) {
            return createStorage(storage);
        } else {
            return loadStorage(storage);
        }
    }

    private Map<ItemType, Integer> createStorage(File storage) {
        Map<ItemType, Integer> items = Arrays.stream(ItemType.values())
                .collect(Collectors.toMap(it -> it, it -> 0));

        try (Writer writer = new FileWriter(storage)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(items, writer);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return items;
    }

    private Map<ItemType, Integer> loadStorage(File storage) {
        Map<ItemType, Integer> items;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(storage))) {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<ItemType, Integer>>() {
            }.getType();
            items = gson.fromJson(bufferedReader, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return items;
    }
}
