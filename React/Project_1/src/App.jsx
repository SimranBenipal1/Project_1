import { useEffect, useState } from 'react'
import { Sidebar, Menu, MenuItem, useProSidebar, SubMenu } from "react-pro-sidebar";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import WarehouseIcon from '@mui/icons-material/Warehouse';
import Button from '@mui/material/Button';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import InventoryIcon from '@mui/icons-material/Inventory';
import { useTheme } from '@mui/material/styles';
import * as React from 'react';
import EditIcon from '@mui/icons-material/Edit';
import "./App.css";



function App() {
  const { collapseSidebar } = useProSidebar();
  const [warehouses, setWarehouses] = useState([]);
  const [open, setOpen] = React.useState(false);
  const theme = useTheme();
  const [inventory, setInventory] = useState([]);

  //Get Warehouse Names
  useEffect(() => {
    fetch('http://localhost:8080/warehouse')
      .then(res => res.json())
      .then(data => setWarehouses(data))
      .catch(err => console.error(err))
  }, []);

  // useEffect(() => {
  //   fetch('http://localhost:8080/inventory')
  //     .then(res => res.json())
  //     .then(data => setInventory(data))
  //     .catch(err => console.error(err))
  // }, []);

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

  function updateTable(id){
    fetch('http://localhost:8080/inventory')
      .then(res => res.json())
      .then(data => setInventory(data.filter(item => item.warehouse.warehouse_id === id)))
      .catch(err => console.error(err))

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


        </Menu>
            <div>
            <Button style={{ color: 'black', position: 'absolute', bottom: 15, left: 0, width: '100%'}} variant="text" > Add </Button>

            </div>

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


      </main>
      </div>

      
    );
  }
  
  export default App;