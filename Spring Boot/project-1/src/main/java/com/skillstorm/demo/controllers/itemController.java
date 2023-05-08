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

import com.skillstorm.demo.models.item;
import com.skillstorm.demo.services.itemService;

/**
 * The Class itemController.
 * @author Simran Benipal
 */
@RestController
@RequestMapping("/items")
@CrossOrigin
public class itemController {
	
	/** The item service. */
	@Autowired
	private itemService itemService;
	
	/**
	 * Find all items.
	 *
	 * @return the list
	 */
	@GetMapping
	public List<item> findAllItems(){
		return itemService.findAllItems();
	}
	
	/**
	 * Finditemby id.
	 *
	 * @param id the id
	 * @return the item
	 */
	@GetMapping("/{id}")
	public item finditembyId (@PathVariable long id) {
		return itemService.findItemById(id);
	}
	
	/**
	 * Creates the item.
	 *
	 * @param item the item
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<item>createItem (@RequestBody item item) {
		item item2 = itemService.createItem(item);
		return new ResponseEntity<>(item2, HttpStatus.CREATED);
	}
	
	/**
	 * Delete item.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/{id}")
	public void deleteItem(@PathVariable long id) {
		itemService.deleteItem(id);
	}
	
	/**
	 * Update item.
	 *
	 * @param id the id
	 * @param item the item
	 * @return the item
	 */
	@PutMapping("{id}")
	public item updateItem(@PathVariable long id, @RequestBody item item) {
		item.setItem_id(id);
		return itemService.updateItem(item);
	}
	
}
