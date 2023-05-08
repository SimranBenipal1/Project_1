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
  const [editForm, setEditForm] = useState(false);
  const [rowData, setRow] = useState([]);
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
      //console.error('Error:', error);
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
      //console.log(id)
      handleInventoryForm();
      setEditForm(false);
    } catch (err) {
      console.error(err);
    }
  }

  function addWarehouse(){
    fetch('http://localhost:8080/warehouse', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        name: 'Default Warehouse',
        maxium_capacity: 10000
      })
    })
      .then(response => response.json())
      .then(
        fetch('http://localhost:8080/warehouse')
        .then(res => res.json())
        .then(data => {
          //console.log(data);
          fetch('http://localhost:8080/warehouse')
          .then(res => res.json())
          .then(data => setWarehouses(data))
          .catch(err => console.error(err))
        })
        .catch(err => console.error(err))
      )
      .catch(error => console.error(error));
  }

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

    //console.log(form)
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
      .then(res => {
        return fetch('http://localhost:8080/warehouse/' + currentWarehouse);
      })
      .then(res => res.json())
      .then(warehouseData => {
        return fetch('http://localhost:8080/items')
          .then(response => response.json())
          .then(items => {
            let matchingItem = items.find(item => item.name === form.item_name.value && item.description === form.item_description.value && item.category === form.item_category.value);
    
            const inventoryData = {
              quantity: form.quantity.value,
              value: form.value.value,
              size: form.size.value,
              item: matchingItem,
              warehouse: warehouseData
            };
    
            addInventory(inventoryData);
          });
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
      //console.log(data);
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

  function removeInventory(inventoryID, itemID){
    //console.log(inventoryID);
    //console.log(itemID);
    fetch('http://localhost:8080/inventory/' + inventoryID, {
      method: 'delete'
    })
    .then(data => {
      fetch('http://localhost:8080/items/' + itemID, {
        method: 'delete'
      })
      .catch(error => {
        console.error('Error:', error);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });

    updateTable(currentWarehouse);
  }

  function handleWarehouseEdit(warehouseId){
    let warehouse;

    fetch("http://localhost:8080/warehouse/" + warehouseId)
      .then(response => response.json())
      .then(data => {
        warehouse = data;
    
        const form = document.getElementById('edit-warehouse-form-' + warehouseId);
        const formData = new FormData(form);
        
        //console.log(formData);

        const warehouseData = {
          name: formData.get('Name'),
          maxium_capacity: parseInt(formData.get('Size'))
        }

        //console.log(warehouseData.name)
        //console.log(warehouseData.maximum_capacity)
        //console.log(warehouseData)
        if (warehouseData.name == ""){
          warehouseData.name = warehouse.name;
        }
    
        if (warehouseData.maxium_capacity == ''){
          warehouseData.maxium_capacity = parseInt(warehouse.maxium_capacity);
        }
        //console.log(warehouseData);
    
        fetch('http://localhost:8080/warehouse/' + warehouseId, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(warehouseData)
        })
          .then(response => response.json())
          .then(data => {
            //console.log(data);
            fetch('http://localhost:8080/warehouse')
            .then(res => res.json())
            .then(data => setWarehouses(data))
            .catch(err => console.error(err))
          })
          .catch(error => {
            console.error(error);
          });
    
      })
      .catch(error => {
        console.error(error);
      });

  }

  function cancelEditRow(){
    setEditForm(false)
    setInventoryForm(true)
  }

  function editRowForm(row){
    setInventoryForm(false)
    setEditForm(true)
    setRow(row);

    //const form = document.getElementById('edit-row-form');
    //const formData = new FormData(form);


    
  }

  function putRow(){
    //console.log(rowData)
    const form = document.getElementById('edit-row-form');


    let quantity = form.elements['edit-quantity'].value.trim();
    quantity = quantity ? Number(quantity) : rowData.quantity;

    let value = form.elements['edit-value'].value.trim();
    value = value ? Number(value) : rowData.value;
  
    let size = form.elements['edit-size'].value.trim();
    size = size ? Number(size) : rowData.size;
  
    let itemName = form.elements['edit-item_name'].value.trim();
    itemName = itemName ? itemName : rowData.item.name;
  
    let itemDescription = form.elements['edit-item_description'].value.trim();
    itemDescription = itemDescription ? itemDescription : rowData.item.description;
  
    let itemCategory = form.elements['edit-item_category'].value.trim();
    itemCategory = itemCategory ? itemCategory : rowData.item.category;

    const inventoryData = {
      "quantity": quantity,
      "value": value,
      "size": size,
      "item": rowData.item,
      "warehouse": rowData.warehouse
    };

    const itemData = {
      "name": itemName,
      "description": itemDescription,
      "category": itemCategory
    }

    fetch('http://localhost:8080/inventory/' + rowData.warehouse_inventory_id, {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(inventoryData)
    })
    .then(response => {
    if (response.ok) {
        return response.json();
    } else {
        throw new Error('Something went wrong');
    }
    })
    .then(data => {
      //console.log(data);
      fetch('http://localhost:8080/items/' + rowData.item.item_id, {
        method: 'PUT',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify(itemData)
      })
      .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Something went wrong');
        }
        })
        .then(data => {
          //console.log(data);
          updateTable(currentWarehouse);
        })
    })
    .catch(error => {
      console.error('Error:', error);
    });

  }

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
              <SubMenu icon={<EditIcon />} label= "Edit Warehouse">
              <form id={`edit-warehouse-form-${warehouse.warehouse_id}`} onSubmit={handleSubmit}>
                  <label>
                  Name:
                  <input type="text" name="Name" placeholder={warehouse.name}/>
                  </label>
                  <label>
                  Size:
                  <input type="number" name="Size" placeholder={warehouse.maxium_capacity}/>
                  </label>
                  <button type="Submit" onClick={() => handleWarehouseEdit(warehouse.warehouse_id)} >Submit</button>
                  </form>
              </SubMenu>
              <MenuItem icon={<DeleteOutlineIcon />} onClick={() => warehouseDelete(warehouse.warehouse_id)}>Delete Warehouse</MenuItem>
              </SubMenu>
            )
          })}

          <div>
            <Button style={{ color: 'black', position: 'absolute', bottom: 15, left: 0, width: '100%'}} variant="text" onClick={addWarehouse}> Add </Button>
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
            <td> <EditIcon onClick={() => editRowForm(row)}/><DeleteOutlineIcon onClick={() => removeInventory(row.warehouse_inventory_id, row.item.item_id)} /> </td>
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
    <input type="number" name="quantity"/>
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
  <button type="Submit" onClick={() => addItem()} >Submit</button>
  </form> 
  </>}

  {editForm &&
  <>
  <h2 style={{ marginLeft: "5rem" }}>Edit Items</h2>
  <form id="edit-row-form" onSubmit={handleSubmit}>
  <label>
    Quantity:
    <input type="number" name="edit-quantity" placeholder={rowData.quantity}/>
  </label>
  <label>
    Value:
    <input type="number" name="edit-value" placeholder={rowData.value}/>
  </label>
  <label>
    Size:
    <input type="number" name="edit-size" placeholder={rowData.size}/>
  </label>
  <label>
    Item Name:
    <input type="text" name="edit-item_name" placeholder={rowData.item.name}/>
  </label>
  <label>
    Item Description:
    <input type="text" name="edit-item_description"placeholder={rowData.item.description}/>
  </label>
  <label>
    Item Category:
    <input type="text" name="edit-item_category" placeholder={rowData.item.category}/>
  </label>
  <button type="Submit" onClick={() => putRow()} >Edit</button>
  <button type="Submit" style={{ backgroundColor: 'red', color: 'white' }} onClick={() => cancelEditRow() }>Cancel Edit Mode</button>
  </form> 
  
  </>}



      </main>
      </div>

      
    );
  }
  
  export default App;