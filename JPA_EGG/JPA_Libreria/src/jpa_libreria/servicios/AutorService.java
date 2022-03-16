package jpa_libreria.servicios;

import java.util.List;
import jpa_libreria.entidades.Autor;
import jpa_libreria.persistenca.AutorDAO;

public class AutorService {

    private final AutorDAO dao;

    public AutorService() {
        dao = new AutorDAO();
    }

    public void crear(String nombre) throws Exception {

        Autor a = new Autor();
        validar(nombre);
        
        a.setAlta(true);
        a.setNombre(nombre);

        dao.guardar(a);
    }

    public List<Autor> mostrarTodos() {
        return dao.mostrarTodos();
    }

    public void modificar(Integer id, String nombre, boolean alta) {

        Autor a = buscarPorId(id);

        a.setAlta(alta);
        a.setNombre(nombre);

        dao.editar(a);

    }

    public void eliminar(Integer id) {

        Autor a = buscarPorId(id);

        dao.eliminar(a);
    }

    public Autor buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }

    public Autor buscarPorNombre(String nombre) {
        return dao.buscarPorNombre(nombre);
    }

    private void validar(String nombre) throws Exception { // id no se valida porque es creacion automatica
        // alta se setea por defecto
        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe indicar un nombre");
        }

        Autor a = buscarPorNombre(nombre);

        if (a != null) {
            throw new Exception("El autor ya existe");
        }
    }
}
