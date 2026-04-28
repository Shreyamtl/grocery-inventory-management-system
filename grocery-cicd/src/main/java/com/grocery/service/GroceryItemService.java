package com.grocery.service;

import com.grocery.model.GroceryItem;
import com.grocery.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository repository;

    public List<GroceryItem> getAllItems() {
        return repository.findAll();
    }

    public Optional<GroceryItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public GroceryItem createItem(GroceryItem item) {
        return repository.save(item);
    }

    public GroceryItem updateItem(Long id, GroceryItem updatedItem) {
        return repository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setCategory(updatedItem.getCategory());
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());
            item.setUnit(updatedItem.getUnit());
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public List<GroceryItem> getLowStockItems(Integer threshold) {
        return repository.findByQuantityLessThan(threshold);
    }

    public List<GroceryItem> getItemsByCategory(String category) {
        return repository.findByCategory(category);
    }
}
