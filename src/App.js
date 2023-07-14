import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Component } from 'react';
import { Button } from 'reactstrap';
import axios from 'axios';

const url = 'http://localhost:9000/product/'
class App extends Component {

  state = {
    products: [],
    form: {
      nombre: '',
      descripcion: '',
      precio: 0
    },
    idSelected: null,
    saveState: true,
    validation: '',
    search: {
      id: ''
    }
  }

  getAllProducts = () => {
    axios.get(url + 'all').then(response => {
      this.setState({ products: response.data })
    })
  }

  componentDidMount() {
    this.getAllProducts();
  }

  handleChange = async e => {
    e.persist();
    await this.setState({
      form: {
        ...this.state.form,
        [e.target.name]: e.target.value
      }
    })
  }

  saveProduct = () => {
    this.setState({ validation: '' })
    if (this.state.form.precio > 0 && this.state.form.nombre.length > 0 && this.state.form.descripcion.length > 0) {
      this.state.form.precio = parseFloat(this.state.form.precio);
      axios.post(url, this.state.form).then(_ => {
        this.getAllProducts();
        this.setFields()
      });
    } else {
      this.setState({ validation: 'Ingresa los datos correctamente' })
    }
  }

  deleteProduct = (id) => {
    axios.delete(url + id).then(_ => {
      this.getAllProducts();
      this.setFields();
    })
  }

  updateProduct = () => {
    this.setState({ validation: '' })
    if (this.state.form.precio > 0 && this.state.form.nombre.length > 0 && this.state.form.descripcion.length > 0) {
      this.setState({ saveState: true })
      this.state.form.precio = parseFloat(this.state.form.precio);
      axios.put(url + this.state.idSelected, this.state.form).then(_ => {
        this.getAllProducts();
        this.setFields();
      })
    } else {
      this.setState({ validation: 'Ingresa los datos correctamente' })
    }
  }

  setFields = () => {
    this.setState({
      form: {
        precio: '',
        nombre: '',
        descripcion: ''
      }
    })
  }

  selectProductUpdate = (product) => {
    this.state.idSelected = product.id;
    this.setState({ saveState: false })
    this.setState({
      form: {
        precio: product.precio,
        nombre: product.nombre,
        descripcion: product.descripcion
      }
    })
  }

  handleSearch = async e => {
    if (e.target.value > 0) {
      e.persist();
      axios.get(url + e.target.value).then(response => {
        this.setState({ products: [response.data] })
      }).catch(ex => {
        console.log(ex)
      })
    } else {
      this.getAllProducts()
    }
  }

  render() {
    const { form } = this.state
    return (
      <div className="App container">
        <h2 className='mt-5'>Crud productos</h2>
        <form>
          <div className='form-group mt-5'>
            <input className='form-control' type='text' name='nombre' id='nombre' placeholder='Nombre producto' onChange={this.handleChange} value={form.nombre}></input>
          </div>
          <div className='form-group mt-2'>
            <input className='form-control' type='text' name='descripcion' id='descripcion' placeholder='Descripcion' onChange={this.handleChange} value={form.descripcion}></input>
          </div>
          <div className='form-group mt-2'>
            <input className='form-control' type='number' name='precio' id='precio' placeholder='Precio' onChange={this.handleChange} value={form.precio}></input>
          </div>
        </form>

        {this.state.saveState ?
          <Button color='success' className='mt-5' onClick={this.saveProduct}>Guardar producto</Button> :
          <Button color='success' className='mt-5' onClick={this.updateProduct}>Actualizar producto</Button>
        }
        {
          this.state.validation !== '' ?
            <div class="alert alert-success mt-2" role="alert">
              {this.state.validation}
            </div> : ''
        }
        <div className='container mt-5'>
          <div className='row'>
            <div className='col-lg-3'>
              <input id='id' name='id' placeholder='Buscar por id' className='form-control' onChange={this.handleSearch} type='number'></input>
            </div>
          </div>
        </div>
        <table className='table'>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Descripcion</th>
              <th>Precio</th>
              <th>Opciones</th>
            </tr>
          </thead>
          <tbody>
            {
              this.state.products.map(product => {
                return (
                  <tr>
                    <td>{product.id}</td>
                    <td>{product.nombre}</td>
                    <td>{product.descripcion}</td>
                    <td>{product.precio}</td>
                    <td>
                      <button className='btn btn-danger' onClick={() => this.deleteProduct(product.id)}>Eliminar</button>
                      <button className='btn btn-primary' onClick={() => this.selectProductUpdate(product)}>Actualizar</button>
                    </td>
                  </tr>
                )
              })
            }
          </tbody>
        </table>
      </div>
    );
  }
}

export default App;
