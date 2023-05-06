package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.models.warehouse;
import com.skillstorm.demo.repositories.warehouseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class warehouseService {
	
	@Autowired
	private warehouseRepository warehouseRepository;
	
	public List<warehouse> findAllWarehouses(){
		return warehouseRepository.findAll();
	}
	
	public warehouse findWareHouseById (long id) {
		return warehouseRepository.findById(id).orElseThrow();
	}
	
	public warehouse createWarehouse (warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}
	
	public void deleteWarehouse (long id) {
		warehouseRepository.deleteById(id);
	}
	
	public warehouse updateWarehouse (warehouse warehouse) {
		warehouse warehouse2 = new warehouse (
				warehouse.getWarehouse_id(), 
				warehouse.getName(), 
				warehouse.getMaxium_capacity());
		return warehouseRepository.save(warehouse2);
	}
}
