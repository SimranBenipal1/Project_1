package com.skillstorm.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skillstorm.demo.models.item;
import com.skillstorm.demo.repositories.itemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class itemService {
	
	@Autowired
	private itemRepository itemRepository;
	
	
	public List<item> findAllItems(){
		return itemRepository.findAll();
	}
	
	public item findItemById (long id) {
		return itemRepository.findById(id).orElseThrow();
	}
	
	public item createItem (item item) {
		return itemRepository.save(item);
	}
	
	public void deleteItem (long id) {
		itemRepository.deleteById(id);
	}
	
	public item updateItem (item item) {
		item item2 = new item (
				item.getItem_id(), 
				item.getName(),
				item.getDescription(), 
				item.getCategory());
		return itemRepository.save(item2);
	}
}
