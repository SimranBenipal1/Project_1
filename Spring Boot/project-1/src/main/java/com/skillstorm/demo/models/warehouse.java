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
public class warehouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long warehouse_id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private double maxium_capacity;

	
	
	
	
	public warehouse(long warehouse_id, String name, double maxium_capacity) {
		super();
		this.warehouse_id = warehouse_id;
		this.name = name;
		this.maxium_capacity = maxium_capacity;
	}
	
	public warehouse() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(warehouse_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		warehouse other = (warehouse) obj;
		return warehouse_id == other.warehouse_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMaxium_capacity() {
		return maxium_capacity;
	}

	public void setMaxium_capacity(double maxium_capacity) {
		this.maxium_capacity = maxium_capacity;
	}
	
	
}
