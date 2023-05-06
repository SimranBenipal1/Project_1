function EditWarehouseForm(props) {
    const [name, setName] = useState(props.warehouse.name);
    const [address, setAddress] = useState(props.warehouse.address);
  
    const handleSubmit = (event) => {
      event.preventDefault();
      // Call your update warehouse API here
      // ...
      props.onClose();
    }
  
    return (
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" value={name} onChange={(event) => setName(event.target.value)} />
        </label>
        <label>
          Address:
          <input type="text" value={address} onChange={(event) => setAddress(event.target.value)} />
        </label>
        <button type="submit">Save</button>
        <button type="button" onClick={props.onClose}>Cancel</button>
      </form>
    );
  }