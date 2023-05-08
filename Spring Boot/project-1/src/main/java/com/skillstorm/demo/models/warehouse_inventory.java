package com.skillstorm.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



/**
 * The Class warehouse_inventory.
 * @author Simran Benipal
 */
@Entity
@Table
public class warehouse_inventory {

	/** The warehouse inventory id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long warehouse_inventory_id;
	
	/** The quantity. */
	@Column
	private int quantity;
	
	/** The value. */
	@Column
	private double value;
	
	/** The size. */
	@Column
	private double size;
	
    /** The item. */
    @ManyToOne
    @JoinColumn(referencedColumnName = "item_id")
    private item item;
	
    /** The warehouse. */
    @ManyToOne
    @JoinColumn(referencedColumnName = "warehouse_id")
    private warehouse warehouse;

	/**
	 * Instantiates a new warehouse inventory.
	 *
	 * @param warehouse_inventory_id the warehouse inventory id
	 * @param quantity the quantity
	 * @param value the value
	 * @param size the size
	 * @param item the item
	 * @param warehouse the warehouse
	 */
	public warehouse_inventory(long warehouse_inventory_id, int quantity, double value, double size,
			com.skillstorm.demo.models.item item, com.skillstorm.demo.models.warehouse warehouse) {
		super();
		this.warehouse_inventory_id = warehouse_inventory_id;
		this.quantity = quantity;
		this.value = value;
		this.size = size;
		this.item = item;
		this.warehouse = warehouse;
	}
	
	/**
	 * Instantiates a new warehouse inventory.
	 */
	public warehouse_inventory() {
		
	}

	/**
	 * Gets the warehouse inventory id.
	 *
	 * @return the warehouse inventory id
	 */
	public long getWarehouse_inventory_id() {
		return warehouse_inventory_id;
	}

	/**
	 * Sets the warehouse inventory id.
	 *
	 * @param warehouse_inventory_id the new warehouse inventory id
	 */
	public void setWarehouse_inventory_id(long warehouse_inventory_id) {
		this.warehouse_inventory_id = warehouse_inventory_id;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public item getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(item item) {
		this.item = item;
	}

	/**
	 * Gets the warehouse.
	 *
	 * @return the warehouse
	 */
	public warehouse getWarehouse() {
		return warehouse;
	}

	/**
	 * Sets the warehouse.
	 *
	 * @param warehouse the new warehouse
	 */
	public void setWarehouse(warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	
	
}
