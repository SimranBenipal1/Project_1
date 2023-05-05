package com.skillstorm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.warehouse_inventory;



@Repository
public interface warehouseInventoryRepository extends JpaRepository<warehouse_inventory, Long>{

}
