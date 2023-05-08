package com.skillstorm.demo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The Class warehouse.
 * @author Simran Benipal
 */
@Entity
@Table
public class warehouse {
	
	/** The warehouse id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long warehouse_id;
	
	/** The name. */
	@Column(nullable = false)
	private String name;
	
	/** The maxium capacity. */
	@Column(nullable = false)
	private double maxium_capacity;

	
	
	
	
	/**
	 * Instantiates a new warehouse.
	 *
	 * @param warehouse_id the warehouse id
	 * @param name the name
	 * @param maxium_capacity the maxium capacity
	 */
	public warehouse(long warehouse_id, String name, double maxium_capacity) {
		super();
		this.warehouse_id = warehouse_id;
		this.name = name;
		this.maxium_capacity = maxium_capacity;
	}
	
	/**
	 * Instantiates a new warehouse.
	 */
	public warehouse() {
		
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(warehouse_id);
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
		warehouse other = (warehouse) obj;
		return warehouse_id == other.warehouse_id;
	}

	/**
	 * Gets the warehouse id.
	 *
	 * @return the warehouse id
	 */
	public long getWarehouse_id() {
		return warehouse_id;
	}

	/**
	 * Sets the warehouse id.
	 *
	 * @param warehouse_id the new warehouse id
	 */
	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
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
	 * Gets the maxium capacity.
	 *
	 * @return the maxium capacity
	 */
	public double getMaxium_capacity() {
		return maxium_capacity;
	}

	/**
	 * Sets the maxium capacity.
	 *
	 * @param maxium_capacity the new maxium capacity
	 */
	public void setMaxium_capacity(double maxium_capacity) {
		this.maxium_capacity = maxium_capacity;
	}
	
	
}
