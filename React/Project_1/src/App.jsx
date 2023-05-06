import { useEffect, useState } from 'react'
import { Sidebar, Menu, MenuItem, useProSidebar, SubMenu } from "react-pro-sidebar";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import WarehouseIcon from '@mui/icons-material/Warehouse';
import Button from '@mui/material/Button';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import InventoryIcon from '@mui/icons-material/Inventory';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import useMediaQuery from '@mui/material/useMediaQuery';
import { useTheme } from '@mui/material/styles';
import * as React from 'react';
import EditIcon from '@mui/icons-material/Edit';


function App() {
  const { collapseSidebar } = useProSidebar();
  const [warehouses, setWarehouses] = useState([]);
  const [open, setOpen] = React.useState(false);
  const theme = useTheme();

  //Get Warehouse table
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
            console.log(warehouse)
            return (
              <SubMenu key={warehouse.id} icon={<WarehouseIcon />} label={warehouse.name}>
              <MenuItem icon={<InventoryIcon />}>View Inventory</MenuItem>
              <MenuItem icon={<EditIcon />}>Edit Warehouse</MenuItem>
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
        <h2>
          What is left to do:
          Warehouses CUD
          Inventory CRUD
          Items CRUD
          Checking for overflow size

        </h2>










      </main>
      </div>

      
    );
  }
  
  export default App;