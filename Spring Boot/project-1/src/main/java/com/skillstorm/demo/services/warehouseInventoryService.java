package com.skillstorm.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.demo.models.warehouse_inventory;
import com.skillstorm.demo.repositories.warehouseInventoryRepository;

import jakarta.transaction.Transactional;


/**
 * The Class warehouseInventoryService.
 * @author Simran Benipal
 */
@Service
@Transactional
public class warehouseInventoryService {
	
	/** The warehouse inventory repository. */
	@Autowired
	private warehouseInventoryRepository warehouseInventoryRepository;
	
	/**
	 * Find all items.
	 *
	 * @return the list
	 */
	public List<warehouse_inventory> findAllItems(){
		return warehouseInventoryRepository.findAll();
	}
	
	/**
	 * Find inventory by id.
	 *
	 * @param id the id
	 * @return the warehouse inventory
	 */
	public warehouse_inventory findInventoryById (long id) {
		return warehouseInventoryRepository.findById(id).orElseThrow();
	}
	
	/**
	 * Creates the inventory.
	 *
	 * @param warehouse_inventory the warehouse inventory
	 * @return the warehouse inventory
	 */
	public warehouse_inventory createInventory (warehouse_inventory warehouse_inventory) {
		return warehouseInventoryRepository.save(warehouse_inventory);
	}
	
	/**
	 * Delete inventory.
	 *
	 * @param id the id
	 */
	public void deleteInventory(long id) {
		warehouseInventoryRepository.deleteById(id);
	}
	
	/**
	 * Update inventory.
	 *
	 * @param warehouse_inventory the warehouse inventory
	 * @return the warehouse inventory
	 */
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
