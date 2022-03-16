package jpa_libreria.servicios;

import java.util.List;
import jpa_libreria.entidades.Cliente;
import jpa_libreria.persistenca.ClienteDAO;

public class ClienteServicio {

    private ClienteDAO dao;

    public ClienteServicio() {
        this.dao = new ClienteDAO();
    }

    public void crear(Long documento, String nombre, String apellido, String telefono) throws Exception {

        Cliente c = new Cliente();

        validar(documento, nombre, apellido, telefono);

        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setDocumento(documento);
        c.setTelefono(telefono);

        dao.guardar(c);
    }

    public void modificar(Long documento, String nombre, String apellido, String telefono) throws Exception {

        validarDNI(documento); // compruebo que el cliente exista para poder editarlo

        Cliente c = buscarPorDNI(documento); // deberia tener la opcion de poder editarlo buscando por id o nombre, porque se puede tener que cambiar el DNI

//        if (documento != null) {          // si se agrega la opcion de buscar por id
//            c.setDocumento(documento);
//        }
        if (nombre != null) {
            c.setNombre(nombre);
        }

        if (apellido != null) {
            c.setApellido(apellido);
        }

        if (telefono != null) {
            c.setTelefono(telefono);
        }

        dao.editar(c);

    }

    public void eliminar(Long documento) {
        Cliente c = buscarPorDNI(documento);

        dao.eliminar(c);
    }

    public List<Cliente> mostrarTodos() {
        return dao.mostrarTodos();
    }

    public void validar(Long documento, String nombre, String apellido, String telefono) throws Exception {

//        if (id == null || id < 0) {
//            throw new Exception("Debe ingresar un ID");   // no se valida porque es auto incremental
//        }
        if (documento == null || documento == 0) {
            throw new Exception("Debe ingresar un DNI");
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe ingresar un nombre");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new Exception("Debe ingresar un apellido");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new Exception("Debe ingresar un telefono");
        }

        validarDNI(documento);
    }

    public Cliente buscarPorDNI(Long documento) {
        return dao.buscarPorDNI(documento);

    }

    private void validarDNI(Long documento) throws Exception {
        Cliente c = buscarPorDNI(documento);

        if (c != null) {
            throw new Exception("El DNI de cliente ya existe");
        }
    }
}
