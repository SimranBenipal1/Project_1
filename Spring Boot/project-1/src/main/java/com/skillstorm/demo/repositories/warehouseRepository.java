package com.skillstorm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.warehouse;

/**
 * The Interface warehouseRepository.
 */
@Repository
public interface warehouseRepository extends JpaRepository<warehouse, Long>{

}
