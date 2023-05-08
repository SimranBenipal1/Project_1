package com.skillstorm.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



/**
 * The Class item.
 * @author Simran Benipal
 */
@Entity
@Table
public class item {
	
	/** The item id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long item_id;
	
	/** The name. */
	@Column(nullable = false)
	private String name;
	
	/** The description. */
	@Column
	private String description;
	
	/** The category. */
	@Column (nullable = false)
	private String category;
	
	
	/**
	 * Instantiates a new item.
	 *
	 * @param item_id the item id
	 * @param name the name
	 * @param description the description
	 * @param category the category
	 */
	public item(long item_id, String name, String description, String category) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.description = description;
		this.category = category;
	}
	
	/**
	 * Instantiates a new item.
	 */
	public item() {
		
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(item_id);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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

	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public long getItem_id() {
		return item_id;
	}

	/**
	 * Sets the item id.
	 *
	 * @param id the new item id
	 */
	public void setItem_id(long id) {
		this.item_id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
