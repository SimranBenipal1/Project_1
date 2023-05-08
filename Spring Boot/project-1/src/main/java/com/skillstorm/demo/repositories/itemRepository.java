package com.skillstorm.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.demo.models.item;

/**
 * The Interface itemRepository.
 */
@Repository
public interface itemRepository extends JpaRepository<item, Long>{

}
