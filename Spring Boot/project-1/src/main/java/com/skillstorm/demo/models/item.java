package com.skillstorm.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long item_id;
	
	@Column(nullable = false)
	private String name;
	@Column
	private String description;
	@Column (nullable = false)
	private String category;
	
	
	
	
	
	public item(long item_id, String name, String description, String category) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.description = description;
		this.category = category;
	}
	
	public item() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(item_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		item other = (item) obj;
		return item_id == other.item_id;
	}

	public long getItem_id() {
		return item_id;
	}

	public void setItem_id(long id) {
		this.item_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
