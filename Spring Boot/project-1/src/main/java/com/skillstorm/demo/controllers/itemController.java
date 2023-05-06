package com.skillstorm.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.demo.models.item;
import com.skillstorm.demo.services.itemService;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class itemController {
	
	@Autowired
	private itemService itemService;
	
	@GetMapping
	public List<item> findAllItems(){
		return itemService.findAllItems();
	}
	
	@GetMapping("/{id}")
	public item finditembyId (@PathVariable long id) {
		return itemService.findItemById(id);
	}
	
	@PostMapping
	public ResponseEntity<item>createItem (@RequestBody item item) {
		item item2 = itemService.createItem(item);
		return new ResponseEntity<>(item2, HttpStatus.CREATED);
	}
}
