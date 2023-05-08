package com.skillstorm.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.demo.models.item;
import com.skillstorm.demo.repositories.itemRepository;

import jakarta.transaction.Transactional;

/**
 * The Class itemService.
 * @author Simran Benipal
 */
@Service
@Transactional
public class itemService {
	
	/** The item repository. */
	@Autowired
	private itemRepository itemRepository;
	
	
	/**
	 * Find all items.
	 *
	 * @return the list
	 */
	public List<item> findAllItems(){
		return itemRepository.findAll();
	}
	
	/**
	 * Find item by id.
	 *
	 * @param id the id
	 * @return the item
	 */
	public item findItemById (long id) {
		return itemRepository.findById(id).orElseThrow();
	}
	
	/**
	 * Creates the item.
	 *
	 * @param item the item
	 * @return the item
	 */
	public item createItem (item item) {
		return itemRepository.save(item);
	}
	
	/**
	 * Delete item.
	 *
	 * @param id the id
	 */
	public void deleteItem (long id) {
		itemRepository.deleteById(id);
	}
	
	/**
	 * Update item.
	 *
	 * @param item the item
	 * @return the item
	 */
	public item updateItem (item item) {
		item item2 = new item (
				item.getItem_id(), 
				item.getName(),
				item.getDescription(), 
				item.getCategory());
		return itemRepository.save(item2);
	}
}
