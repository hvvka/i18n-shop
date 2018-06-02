package com.hania.process;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Map;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class WarehouseImpl implements Warehouse {

    private static final String STORAGE_PATH = "storage.json";
    private File storage;
    private Map<ItemType, Integer> items;
    private ItemsProvider itemsProvider;

    public WarehouseImpl() {
        storage = new File(STORAGE_PATH);
        itemsProvider = new ItemsProvider();
    }

    @Override
    public void loadItems() {
        items = itemsProvider.getItems(storage);
    }


    @Override
    public Map<ItemType, Integer> getItems() {
        return items;
    }

    @Override
    public void addItem(ItemType itemType) {
        items.put(itemType, items.get(itemType) + 1);
        saveToJSON();
    }

    @Override
    public void deleteItem(ItemType itemType) {
        if (items.get(itemType) > 0)
            items.put(itemType, items.get(itemType) - 1);
        saveToJSON();
    }

    @Override
    public String getStorageName() {
        return STORAGE_PATH;
    }

    private void saveToJSON() {
        try (Writer writer = new FileWriter(storage)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(items, writer);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
