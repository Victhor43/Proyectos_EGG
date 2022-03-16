package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Producto;

public class ProductoDAO extends DAO {

    public List<Producto> listarProductos() throws Exception {

        List<Producto> productos = new ArrayList();

        try {
            consultarBase("SELECT * from producto"); // pasar linea de comando de sql

            while (resultado.next()) {  // usar get en cada una de las columnas/campos TODAS
               
                Producto producto = new Producto();
                
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));

                productos.add(producto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return productos;
    }

    public List<Producto> listarPrecio_120a202() throws Exception {
        List<Producto> productos = new ArrayList();

        try {
            consultarBase("SELECT *\n"
                    + "FROM producto\n"
                    + "WHERE precio BETWEEN 120 and 202;");

            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));

                productos.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return productos;
    }

    public List<Producto> listarPortatiles() throws Exception {
        List<Producto> productos = new ArrayList();

        try {
            consultarBase("SELECT *\n"
                    + "FROM producto\n"
                    + "WHERE lower(nombre) LIKE ('%portatil%')");

            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));

                productos.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return productos;
    }

    public List<Producto> listarProductoMasBarato() throws Exception {
        List<Producto> productos = new ArrayList();

        try {
            consultarBase("SELECT *\n"
                    + "from producto\n"
                    + "order by precio\n"
                    + "limit 1");

            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));

                productos.add(producto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return productos;
    }

    public void añadirProducto(Producto producto) throws Exception {

        try {
            String sql = "INSERT INTO producto VALUES("
                    + producto.getCodigo()
                    + ", '"
                    + producto.getNombre()
                    + "' ,"
                    + producto.getPrecio()
                    + ","
                    + producto.getCodigo_fabricante()
                    + ")";

            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public Producto buscarNombre(String nombre) throws Exception {
        Producto producto = null;

        try {

            String sql = "SELECT * FROM producto WHERE nombre LIKE '" + nombre + "'";

            consultarBase(sql);

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return producto;
    }

    public Producto buscarPorCodigo(int codigo) throws Exception {
        Producto producto = null;

        try {

            String sql = "SELECT * FROM producto WHERE codigo = " + codigo;

            consultarBase(sql);

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigo_fabricante(resultado.getInt(4));
// mejorar busqueda video EGG
            }

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

        return producto;
    }

    public void modificarProducto(Producto producto) throws Exception {

        try {

            String sql = "UPDATE producto SET nombre='" + producto.getNombre()
                    + "', precio= " + producto.getPrecio()
                    + ", codigo_fabricante = " + producto.getCodigo_fabricante()
                    + " WHERE codigo LIKE " + producto.getCodigo();

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        }

    }
  
}////////////////////
