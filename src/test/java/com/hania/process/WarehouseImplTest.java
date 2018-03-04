package com.hania.process;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:226154@student.pwr.edu.pl">Hanna Grodzicka</a>
 */
public class WarehouseImplTest {

    private static Warehouse warehouse;
    private static Map<ItemType, Integer> items;
    private File storage;

    @Before
    public void setUp() {
        warehouse = new WarehouseImpl();
        storage = new File(warehouse.getStorageName());
    }

    @After
    public void tearDown() {
        storage.delete();
    }

    @Test
    public void loadItems() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // when
        warehouse.loadItems();

        ItemsProvider itemsProvider = new ItemsProvider();
        Method method = itemsProvider.getClass().getDeclaredMethod("createStorage", File.class);
        method.setAccessible(true);
        Object expectedItems = method.invoke(itemsProvider, storage);

        // then
        assertEquals(expectedItems, warehouse.getItems());
    }

    @Test
    public void getItems() {
        // given
        Map<ItemType, Integer> expectedItems = Arrays.stream(ItemType.values())
                .collect(Collectors.toMap(it -> it, it -> 0));
        warehouse.loadItems();

        // then
        assertEquals(expectedItems, warehouse.getItems());
    }

    @Test
    public void addItem() {
        // given
        warehouse.loadItems();
        ItemType itemToBeAdded = ItemType.DIODE;

        // when
        warehouse.addItem(itemToBeAdded);

        // then
        Map<ItemType, Integer> expectedItems = Arrays.stream(ItemType.values())
                .collect(Collectors.toMap(it -> it, it -> {
                    if (it != itemToBeAdded) return 0;
                    else return 1;
                }));
        assertEquals(expectedItems, warehouse.getItems());
    }

    @Test
    public void deleteItem() {
        // given
        warehouse.loadItems();
        populateStorage();
        ItemType itemToBeDeleted = ItemType.DIODE;

        // when
        warehouse.deleteItem(itemToBeDeleted);

        // then
        Map<ItemType, Integer> expectedItems = Arrays.stream(ItemType.values())
                .collect(Collectors.toMap(it -> it, it -> {
                    switch (it) {
                        case DIODE:
                            return 1;
                        case BATTERY:
                            return 2;
                        case CAPACITOR:
                            return 2;
                        case DETECTOR:
                            return 1;
                        case INDUCTOR:
                            return 1;
                        default:
                            return 0;
                    }
                }));
        assertEquals(expectedItems, warehouse.getItems());
    }

    private void populateStorage() {
        warehouse.addItem(ItemType.DIODE);
        warehouse.addItem(ItemType.DIODE);
        warehouse.addItem(ItemType.BATTERY);
        warehouse.addItem(ItemType.BATTERY);
        warehouse.addItem(ItemType.CAPACITOR);
        warehouse.addItem(ItemType.CAPACITOR);
        warehouse.addItem(ItemType.DETECTOR);
        warehouse.addItem(ItemType.INDUCTOR);
    }
}