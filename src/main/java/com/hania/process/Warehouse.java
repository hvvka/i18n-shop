package com.hania.process;

import java.util.Map;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public interface Warehouse {

    Map<ItemType, Integer> getItems();

    void loadItems();

    void addItem(ItemType itemType);

    void deleteItem(ItemType itemType);

    String getStorageName();
}
