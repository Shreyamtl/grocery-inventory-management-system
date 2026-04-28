package com.grocery.controller;

import com.grocery.model.GroceryItem;
import com.grocery.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class GroceryItemController {

    @Autowired
    private GroceryItemService service;

    // GET all items
    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAllItems() {
        return ResponseEntity.ok(service.getAllItems());
    }

    // GET item by ID
    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> getItemById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create item
    @PostMapping
    public ResponseEntity<GroceryItem> createItem(@RequestBody GroceryItem item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createItem(item));
    }

    // PUT update item
    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
        return ResponseEntity.ok(service.updateItem(id, item));
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    // GET low stock items
    @GetMapping("/low-stock")
    public ResponseEntity<List<GroceryItem>> getLowStockItems(
            @RequestParam(defaultValue = "10") Integer threshold) {
        return ResponseEntity.ok(service.getLowStockItems(threshold));
    }

    // GET items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<GroceryItem>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getItemsByCategory(category));
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Grocery Inventory API is running!");
    }
}
