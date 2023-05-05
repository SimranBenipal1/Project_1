package com.skillstorm.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class warehouse_inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long warehouse_inventory_id;
	
	@Column
	private int quantity;
	
	@Column
	private double value;
	
	@Column
	private double size;
	
    @ManyToOne
    @JoinColumn(referencedColumnName = "item_id")
    private item item;
	
    @ManyToOne
    @JoinColumn(referencedColumnName = "warehouse_id")
    private warehouse warehouse;

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
	
	public warehouse_inventory() {
		
	}

	public long getWarehouse_inventory_id() {
		return warehouse_inventory_id;
	}

	public void setWarehouse_inventory_id(long warehouse_inventory_id) {
		this.warehouse_inventory_id = warehouse_inventory_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public item getItem() {
		return item;
	}

	public void setItem(item item) {
		this.item = item;
	}

	public warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	
	
}
