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
import com.skillstorm.demo.services.warehouseService;


/**
 * The Class warehouseController.
 * @author Simran Benipal
 */
@RestController
@RequestMapping("/warehouse")
@CrossOrigin
public class warehouseController {
	
	/** The warehouse service. */
	@Autowired
	private warehouseService warehouseService;
	
	/**
	 * Find all warehouses.
	 *
	 * @return the list
	 */
	@GetMapping
	public List<warehouse> findAllWarehouses(){
		return warehouseService.findAllWarehouses();
	}
	
	/**
	 * Find item by id.
	 *
	 * @param id the id
	 * @return the warehouse
	 */
	@GetMapping("/{id}")
	public warehouse findItemById(@PathVariable long id) {
		return warehouseService.findWareHouseById(id);
	}
	
	/**
	 * Creates the warehouse.
	 *
	 * @param warehouse the warehouse
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<warehouse>createWarehouse (@RequestBody warehouse warehouse){
		warehouse warehouse2 = warehouseService.createWarehouse(warehouse);
		return new ResponseEntity<>(warehouse2, HttpStatus.CREATED);
	}
	
	/**
	 * Delete warehouse.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void deleteWarehouse(@PathVariable long id) {
		warehouseService.deleteWarehouse(id);
	}
	
	/**
	 * Update warehouse.
	 *
	 * @param id the id
	 * @param warehouse the warehouse
	 * @return the warehouse
	 */
	@PutMapping("/{id}")
	public warehouse updateWarehouse(@PathVariable long id, @RequestBody warehouse warehouse) {
		warehouse.setWarehouse_id(id);
		return warehouseService.updateWarehouse(warehouse);
	}
}
