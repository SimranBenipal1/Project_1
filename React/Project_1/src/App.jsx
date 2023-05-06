import { useEffect, useState } from 'react'
import { Sidebar, Menu, MenuItem, useProSidebar } from "react-pro-sidebar";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import WarehouseIcon from '@mui/icons-material/Warehouse';
import Button from '@mui/material/Button';
import AddIcon from '@mui/icons-material/Add';

function App() {
  const { collapseSidebar } = useProSidebar();

  useEffect(() => {
    fetch('http://localhost:8080/warehouse')
    .then(data => {
      console.log(data.json());
      return data.json();
    }).then(resData => console.log(resData))
    .catch(err => {
      console.error(err);
    })
  }, []);


    return (
      <div id="app" style={({ height: "100vh" }, { display: "flex" })}>
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

          <MenuItem icon={<WarehouseIcon />}>Warehouse 1</MenuItem>
          <MenuItem icon={<WarehouseIcon />}>Warehouse 2</MenuItem>
          <MenuItem icon={<WarehouseIcon />}>Warehouse 3</MenuItem>
          <MenuItem icon={<WarehouseIcon />}>Warehouse 4</MenuItem>
          <MenuItem icon={<WarehouseIcon />}>Warehouse 5</MenuItem>
          <MenuItem icon={<WarehouseIcon />}>Warehouse 6</MenuItem>
          
        </Menu>

            <div>
            <Button style={{ color: 'black', position: 'absolute', bottom: 15, left: 0, width: '100%'}} variant="text"> Add </Button>
            </div>

      </Sidebar>

      <main>
        <h1 style={{ marginLeft: "5rem" }}>
          Inventory
        </h1>
      </main>
      </div>

      
    );
  }
  
  export default App;