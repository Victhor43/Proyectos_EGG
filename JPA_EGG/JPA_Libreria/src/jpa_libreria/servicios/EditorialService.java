package jpa_libreria.servicios;

import java.util.List;
import jpa_libreria.entidades.Editorial;
import jpa_libreria.persistenca.EditorialDAO;

public class EditorialService {

    private final EditorialDAO dao;

    public EditorialService() {
        dao = new EditorialDAO();
    }

    public void crear(String nombre) throws Exception {

        Editorial e = new Editorial();
        validar(nombre);

        e.setAlta(true);
        e.setNombre(nombre);

        dao.guardar(e);
    }

    public List<Editorial> mostrarTodos() {
        return dao.mostrarTodos();
    }

    public void modificar(Integer id, String nombre, boolean alta) {

        Editorial e = buscarPorId(id);

        e.setAlta(alta);
        e.setNombre(nombre);

        dao.editar(e);

    }

    public void eliminar(Integer id) {

        Editorial e = buscarPorId(id);

        dao.eliminar(e);
    }

  
    public Editorial buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public List<Editorial> buscarPorNombre(String nombre) {
        return dao.buscarPorNombre(nombre);
    }

    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe el nombre de la editorial");
        }

        Editorial e = (Editorial) buscarPorNombre(nombre);

        if (e != null) {
            throw new Exception("La editorial ya existe");

        }
    }
}
