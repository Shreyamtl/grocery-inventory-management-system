package com.grocery;

import com.grocery.model.GroceryItem;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.service.GroceryItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroceryApplicationTests {

    @Autowired
    private GroceryItemService service;

    @Autowired
    private GroceryItemRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void contextLoads() {
        assertNotNull(service);
    }

    @Test
    void testCreateAndRetrieveItem() {
        GroceryItem item = new GroceryItem("Tomatoes", "Vegetables", 50, 2.99, "kg");
        GroceryItem saved = service.createItem(item);

        assertNotNull(saved.getId());
        assertEquals("Tomatoes", saved.getName());
        assertEquals("Vegetables", saved.getCategory());
        assertEquals(50, saved.getQuantity());
    }

    @Test
    void testGetAllItems() {
        service.createItem(new GroceryItem("Milk", "Dairy", 20, 1.49, "litre"));
        service.createItem(new GroceryItem("Bread", "Bakery", 15, 2.50, "loaf"));

        List<GroceryItem> items = service.getAllItems();
        assertEquals(2, items.size());
    }

    @Test
    void testUpdateItem() {
        GroceryItem item = service.createItem(new GroceryItem("Apples", "Fruits", 30, 3.00, "kg"));
        item.setQuantity(25);
        GroceryItem updated = service.updateItem(item.getId(), item);
        assertEquals(25, updated.getQuantity());
    }

    @Test
    void testDeleteItem() {
        GroceryItem item = service.createItem(new GroceryItem("Rice", "Grains", 100, 1.20, "kg"));
        service.deleteItem(item.getId());
        assertTrue(service.getItemById(item.getId()).isEmpty());
    }

    @Test
    void testLowStockAlert() {
        service.createItem(new GroceryItem("Salt", "Condiments", 3, 0.99, "kg"));
        service.createItem(new GroceryItem("Sugar", "Condiments", 50, 1.50, "kg"));

        List<GroceryItem> lowStock = service.getLowStockItems(10);
        assertEquals(1, lowStock.size());
        assertEquals("Salt", lowStock.get(0).getName());
    }
}
