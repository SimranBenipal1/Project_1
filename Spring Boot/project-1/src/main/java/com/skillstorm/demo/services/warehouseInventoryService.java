package com.skillstorm.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.demo.models.warehouse_inventory;
import com.skillstorm.demo.repositories.warehouseInventoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class warehouseInventoryService {
	@Autowired
	private warehouseInventoryRepository warehouseInventoryRepository;
	
	public List<warehouse_inventory> findAllItems(){
		return warehouseInventoryRepository.findAll();
	}
	
	public warehouse_inventory findInventoryById (long id) {
		return warehouseInventoryRepository.findById(id).orElseThrow();
	}
	
	public warehouse_inventory createInventory (warehouse_inventory warehouse_inventory) {
		return warehouseInventoryRepository.save(warehouse_inventory);
	}
	
	public void deleteInventory(long id) {
		warehouseInventoryRepository.deleteById(id);
	}
	
	public warehouse_inventory updateInventory (warehouse_inventory warehouse_inventory) {
		warehouse_inventory warehouse_inventory2 = 
				new warehouse_inventory(
						warehouse_inventory.getWarehouse_inventory_id(), 
						warehouse_inventory.getQuantity(), 
						warehouse_inventory.getValue(), 
						warehouse_inventory.getSize(), 
						warehouse_inventory.getItem(), 
						warehouse_inventory.getWarehouse());
		return warehouseInventoryRepository.save(warehouse_inventory2);
	}
}
