package com.skillstorm.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.demo.models.warehouse_inventory;
import com.skillstorm.demo.repositories.warehouseInventoryRepository;

@Service
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
}
