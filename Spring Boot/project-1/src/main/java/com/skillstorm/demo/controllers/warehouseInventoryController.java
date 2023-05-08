package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.demo.models.warehouse;
import com.skillstorm.demo.models.warehouse_inventory;
import com.skillstorm.demo.services.warehouseInventoryService;


/**
 * The Class warehouseInventoryController.
 * @author Simran Benipal
 */
@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class warehouseInventoryController {

	/** The warehouse inventory service. */
	@Autowired
	private warehouseInventoryService warehouseInventoryService;
	
	/**
	 * Find all items.
	 *
	 * @return the list
	 */
	@GetMapping
	public List<warehouse_inventory> findAllItems(){
		return warehouseInventoryService.findAllItems();
	}
	
	/**
	 * Find inventory by id.
	 *
	 * @param id the id
	 * @return the warehouse inventory
	 */
	@GetMapping("/{id}")
	public warehouse_inventory findInventoryById(@PathVariable long id) {
		return warehouseInventoryService.findInventoryById(id);
	}
	
	/**
	 * Creates the warehouse.
	 *
	 * @param warehouse_inventory the warehouse inventory
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<warehouse_inventory>createWarehouse (@RequestBody warehouse_inventory warehouse_inventory){
		warehouse_inventory warehouse_inventory2 = warehouseInventoryService.createInventory(warehouse_inventory);
		return new ResponseEntity<>(warehouse_inventory2, HttpStatus.CREATED);
	}
	
	/**
	 * Delete inventory.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void deleteInventory(@PathVariable long id) {
		warehouseInventoryService.deleteInventory(id);
	}
	
	/**
	 * Update inventory.
	 *
	 * @param id the id
	 * @param warehouse_inventory the warehouse inventory
	 * @return the warehouse inventory
	 */
	@PutMapping("/{id}")
	public warehouse_inventory updateInventory (@PathVariable long id, @RequestBody warehouse_inventory warehouse_inventory) {
		warehouse_inventory.setWarehouse_inventory_id(id);
		return warehouseInventoryService.updateInventory(warehouse_inventory);
	}

}
