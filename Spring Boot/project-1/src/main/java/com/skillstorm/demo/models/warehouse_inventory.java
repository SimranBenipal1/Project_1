package com.skillstorm.demo.models;

import java.util.Objects;

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
	private int warehouse_inventory_id;
	
	@Column
	@ManyToOne
	@JoinColumn
	private int warehouse_id;
	
	@Column
	@ManyToOne
	@JoinColumn
	private int item_id;
	
	@Column
	private int quantity;
	
	@Column
	private double value;
	
	@Column
	private double size;

	public warehouse_inventory(int warehouse_inventory_id, int warehouse_id, int item_id, int quantity, double value,
			double size) {
		super();
		this.warehouse_inventory_id = warehouse_inventory_id;
		this.warehouse_id = warehouse_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.value = value;
		this.size = size;
	}
	
	public warehouse_inventory() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(warehouse_inventory_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		warehouse_inventory other = (warehouse_inventory) obj;
		return warehouse_inventory_id == other.warehouse_inventory_id;
	}

	public int getWarehouse_inventory_id() {
		return warehouse_inventory_id;
	}

	public void setWarehouse_inventory_id(int warehouse_inventory_id) {
		this.warehouse_inventory_id = warehouse_inventory_id;
	}

	public int getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(int warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
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
	
	
}
