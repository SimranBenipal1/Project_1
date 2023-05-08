import { useEffect, useState } from 'react'
import { Sidebar, Menu, MenuItem, useProSidebar, SubMenu } from "react-pro-sidebar";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import WarehouseIcon from '@mui/icons-material/Warehouse';
import Button from '@mui/material/Button';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import InventoryIcon from '@mui/icons-material/Inventory';
import * as React from 'react';
import EditIcon from '@mui/icons-material/Edit';
import "./App.css";



function App() {
  const { collapseSidebar } = useProSidebar();
  const [warehouses, setWarehouses] = useState([]);
  const [inventory, setInventory] = useState([]);
  const [inventoryForm, setInventoryForm] = useState(false);
  const [currentWarehouse, setCurrentWarehouse] = useState(0);

  //Get Warehouse Names
  useEffect(() => {
    fetch('http://localhost:8080/warehouse')
      .then(res => res.json())
      .then(data => setWarehouses(data))
      .catch(err => console.error(err))
  }, []);

  //Delete warehouse by ID and remove it from the State
  function warehouseDelete(id) {
    fetch('http://localhost:8080/warehouse/' + id, {
      method: 'delete'
    })
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Something went wrong');
      }
    })
    .then(data => {
      console.log(data);
    })
    .catch(error => {
      console.error('Error:', error);
    });

    const updatedWarehouses = warehouses.filter(warehouse => warehouse.warehouse_id !== id);
    setWarehouses(updatedWarehouses);
  }

  async function updateTable(id) {
    try {
      const res = await fetch('http://localhost:8080/inventory');
      const data = await res.json();
      setInventory(data.filter(item => item.warehouse.warehouse_id === id));
      setCurrentWarehouse(id);
      console.log(id)
      handleInventoryForm();
    } catch (err) {
      console.error(err);
    }
  }

  //Names for the columns in the table
  const columns = [
    { header: 'Warehouse Inventory ID', key: 'warehouse_inventory_id' },
    { header: 'Quantity', key: 'quantity' },
    { header: 'Value', key: 'value' },
    { header: 'Size', key: 'size' },
    { header: 'Item Name', key: 'item_name' },
    { header: 'Item Description', key: 'item_description' },
    { header: 'Item Category', key: 'item_category' },
    { header: 'Actions', key: 'actions' }, 
  ];

  function addItem(){
    //console.log(inventory);
    const form = document.getElementById('add-inventory-form');

    console.log(form)
    const itemData = {
      name: form.item_name.value,
      description: form.item_description.value,
      category: form.item_category.value
    }

    fetch('http://localhost:8080/items', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(itemData)
    })

    let matchingItem;

    fetch('http://localhost:8080/items')
    .then(response => response.json())
    .then(items => {
      // Find the item with matching criteria
      matchingItem = items.find(item => item.name === form.item_name.value && item.description === form.item_description.value && item.category === form.item_category.value);
      //console.log(matchingItem); 
      const inventoryData = {
        quantity: form.quantity.value,
        value: form.value.value,
        size: form.size.value,
        item: matchingItem,
        warehouse: inventory[0].warehouse
      };
      addInventory(inventoryData);
    })
    .catch(error => console.error(error));


  }

  function addInventory(inventoryData){
    //console.log(matchingItem)
    //console.log(inventoryData)

    fetch('http://localhost:8080/inventory', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(inventoryData)
    })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      updateTable(currentWarehouse);
    })
    .catch(error => console.error(error));


      //console.log(currentWarehouse);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
  };

  const handleInventoryForm = () => {
    setInventoryForm(true);
  };

    return (

      <div id="app" style={({ height: "100vh" }, { display: "flex" })}>
        {/*Side Bar HTML */}
        <Sidebar style={{ height: "100vh" }}>
        <Menu>
          <MenuItem
            icon={<MenuOutlinedIcon />}
            onClick={() => {
              collapseSidebar();
            }}
            style={{ textAlign: "center" }}>

            {" "}
            <h2>Warehouses</h2>
          </MenuItem>
          {warehouses?.map(warehouse => {
            //console.log(warehouse)
            return (
              <SubMenu key={warehouse.id} icon={<WarehouseIcon />} label={warehouse.name}>
              <MenuItem icon={<InventoryIcon />} onClick={() => updateTable(warehouse.warehouse_id)}>View Inventory</MenuItem>
              <MenuItem icon={<EditIcon />} >Edit Warehouse</MenuItem>
              <MenuItem icon={<DeleteOutlineIcon />} onClick={() => warehouseDelete(warehouse.warehouse_id)}>Delete Warehouse</MenuItem>
              </SubMenu>
            )
          })}

            <div>
            <Button style={{ color: 'black', position: 'absolute', bottom: 15, left: 0, width: '100%'}} variant="text" > Add </Button>
            </div>
        </Menu>


      </Sidebar>

      {/* Main HTML */}
      <main>
        <h1 style={{ marginLeft: "5rem" }}>
          Inventory
        </h1>

        <table>
      <thead>
        <tr>
          {columns.map((column) => (
            <th key={column.key}>{column.header}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {inventory.map((row) => (
          <tr key={row.warehouse_inventory_id}>
            <td>{row.warehouse_inventory_id}</td>
            <td>{row.quantity}</td>
            <td>{row.value}</td>
            <td>{row.size}</td>
            <td>{row.item.name}</td>
            <td>{row.item.description}</td>
            <td>{row.item.category}</td>
            <td> <EditIcon onClick={() => console.log("Hello World")}/><DeleteOutlineIcon onClick={() => console.log("Hello World")} /> </td>
          </tr>
        ))}
      </tbody>
    </table>
  {inventoryForm &&
  <>
  <h2 style={{ marginLeft: "5rem" }}>Add Items</h2>
  <form id="add-inventory-form" onSubmit={handleSubmit}>
  <label>
    Quantity:
    <input type="number" name="quantity" />
  </label>
  <label>
    Value:
    <input type="number" name="value" />
  </label>
  <label>
    Size:
    <input type="number" name="size" />
  </label>
  <label>
    Item ID:
    <input type="number" name="item_id" />
  </label>
  <label>
    Item Name:
    <input type="text" name="item_name" />
  </label>
  <label>
    Item Description:
    <input type="text" name="item_description"/>
  </label>
  <label>
    Item Category:
    <input type="text" name="item_category" />
  </label>
  <label>
    Warehouse ID:
    <input type="number" name="warehouse_id" />
  </label>
  <label>
    Warehouse Name:
    <input type="text" name="warehouse_name" />
  </label>
  <label>
    Warehouse Max Capacity:
    <input type="number" name="warehouse_max_capacity" />
  </label>
  <button type="Submit" onClick={() => addItem()} >Submit</button>
  </form> 
  </>}



      </main>
      </div>

      
    );
  }
  
  export default App;