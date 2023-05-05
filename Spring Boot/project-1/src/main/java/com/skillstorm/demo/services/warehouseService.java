package com.skillstorm.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.demo.models.warehouse;
import com.skillstorm.demo.repositories.warehouseRepository;

@Service
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
}
