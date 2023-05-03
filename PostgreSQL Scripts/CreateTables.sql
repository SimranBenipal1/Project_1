-- Warehouse table
CREATE TABLE Warehouse (
    warehouse_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    maximum_capacity DECIMAL NOT NULL -- Going to store in CM^2
	
);

-- Item table
-- All generic item traits belong here
CREATE TABLE Item (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(255) NOT NULL	
);

-- Warehouse_Inventory table
-- All Warehouse Unique item traits belong here
CREATE TABLE Warehouse_Inventory (
    warehouse_inventory_id SERIAL PRIMARY KEY,
    warehouse_id INTEGER REFERENCES Warehouse(warehouse_id),
    item_id INTEGER REFERENCES Item(item_id),
    quantity INTEGER NOT NULL,
	value NUMERIC(18, 2) NOT NULL,
	size DECIMAL NOT NULL -- Going to store in CM^2
);