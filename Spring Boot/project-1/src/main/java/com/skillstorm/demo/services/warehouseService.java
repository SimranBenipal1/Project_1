package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.models.warehouse;
import com.skillstorm.demo.repositories.warehouseRepository;

import jakarta.transaction.Transactional;

/**
 * The Class warehouseService.
 * @author Simran Benipal
 */
@Service
@Transactional
public class warehouseService {
	
	/** The warehouse repository. */
	@Autowired
	private warehouseRepository warehouseRepository;
	
	/**
	 * Find all warehouses.
	 *
	 * @return the list
	 */
	public List<warehouse> findAllWarehouses(){
		return warehouseRepository.findAll();
	}
	
	/**
	 * Find ware house by id.
	 *
	 * @param id the id
	 * @return the warehouse
	 */
	public warehouse findWareHouseById (long id) {
		return warehouseRepository.findById(id).orElseThrow();
	}
	
	/**
	 * Creates the warehouse.
	 *
	 * @param warehouse the warehouse
	 * @return the warehouse
	 */
	public warehouse createWarehouse (warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}
	
	/**
	 * Delete warehouse.
	 *
	 * @param id the id
	 */
	public void deleteWarehouse (long id) {
		warehouseRepository.deleteById(id);
	}
	
	/**
	 * Update warehouse.
	 *
	 * @param warehouse the warehouse
	 * @return the warehouse
	 */
	public warehouse updateWarehouse (warehouse warehouse) {
		warehouse warehouse2 = new warehouse (
				warehouse.getWarehouse_id(), 
				warehouse.getName(), 
				warehouse.getMaxium_capacity());
		return warehouseRepository.save(warehouse2);
	}
}
