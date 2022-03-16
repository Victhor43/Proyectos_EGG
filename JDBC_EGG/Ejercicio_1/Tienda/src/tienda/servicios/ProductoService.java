package tienda.servicios;

import java.util.List;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

public class ProductoService {

    private ProductoDAO dao;

    public ProductoService() {
        this.dao = new ProductoDAO();
    }

    public List<Producto> listarProdcutos() throws Exception {
        List<Producto> productos = dao.listarProductos();

        return productos;
    }

    public List<Producto> listarPrecio_120a202() throws Exception {
        List<Producto> productos = dao.listarPrecio_120a202();

        return productos;
    }

    public List<Producto> listarPortatiles() throws Exception {
        List<Producto> productos = dao.listarPortatiles();

        return productos;
    }

    public List<Producto> listarProductoMasBarato() throws Exception {
        List<Producto> productos = dao.listarProductoMasBarato();
        return productos;
    }

    public void añadirProducto(int codigo, String nombre, double precio, int codFabricante) throws Exception {

        validar(codigo, nombre, precio, codFabricante);

        Producto producto = new Producto();

        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCodigo_fabricante(codFabricante);

        dao.añadirProducto(producto);
    }

    private void validar(int codigo, String nombre, double precio, int codFabricante) throws Exception {

        if (codigo < 0) { // corroborar que se ingreso cada campo del elemento
            throw new Exception("Debe indicar el codigo");
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe indicar el nombre");
        }

        if (precio < 0) {
            throw new Exception("Debe indicar el precio");
        }

        if (codFabricante < 0) {
            throw new Exception("Debe indicar el codigo de fabricante");
        }

        Producto p = buscarNombre(nombre);

        if (p != null) {
            throw new Exception("Ya existe el producto");
        }
    }

    public Producto buscarNombre(String nombre) throws Exception {
        Producto producto = dao.buscarNombre(nombre);
        return producto;
    }
    
    public Producto buscarPorCodigo(int codigo) throws Exception {
        return dao.buscarPorCodigo(codigo);
    }

    public void modificarProducto(String nombre, double precio, int codigo, int codFabricante) throws Exception {

        
        
        Producto p = new Producto();
        
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCodigo(codigo);
        p.setCodigo_fabricante(codFabricante);
        
        dao.modificarProducto(p);

    }

}
