package jpa_libreria.servicios;

import java.util.List;
import jpa_libreria.entidades.Autor;
import jpa_libreria.entidades.Editorial;
import jpa_libreria.entidades.Libro;
import jpa_libreria.persistenca.DAO;
import jpa_libreria.persistenca.LibroDAO;

public class LibroService extends DAO {

    private final LibroDAO dao;
    private final AutorService sAutor;
    private final EditorialService sEditorial;

    public LibroService() {
        this.dao = new LibroDAO();
        this.sAutor = new AutorService();
        this.sEditorial = new EditorialService();
    }

    public void crear(Long isbn, String titulo,
            Integer anio, Integer ejemplares, Integer idAutor,
            Integer idEditorial) throws Exception {

        Integer ejemplaresPrestados = 0;
        Autor autor = sAutor.buscarPorId(idAutor);
        Editorial editorial = sEditorial.buscarPorId(idEditorial);

        Libro l = new Libro();

        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, idAutor, idEditorial);

        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados); // si se crea un libro nuevo, la cantidad de prestados es cero
        l.setEjemplaresRestantes(l.getEjemplares() - l.getEjemplaresPrestados()); // por calculo
        l.setAlta(true); // preseteado
        l.setAutor(autor);
        l.setEditorial(editorial);

        dao.guardar(l);
    }

    public void modificar(Long isbn,
            String titulo,
            Integer anio,
            Integer ejemplares,
            Integer ejemplaresPrestados,
            boolean alta,
            int idAutor,
            int idEditorial) {

        Libro l = buscarPorIsbn(isbn);

        l.setIsbn(isbn);

        if (titulo != null) {
            l.setTitulo(titulo);
        }

        if (anio != null) {
            l.setAnio(anio);
        }
        if (ejemplares != null) {
            l.setEjemplares(ejemplares);
        }

        if (ejemplaresPrestados != null) {
            l.setEjemplaresPrestados(l.getEjemplaresPrestados() + ejemplaresPrestados);//PRESTADOS
            l.setEjemplaresRestantes(l.getEjemplares() - l.getEjemplaresPrestados());//RESTANTES
        }

        l.setAlta(alta);

        if (idAutor != 0) {
            Autor autor = sAutor.buscarPorId(idAutor);
            l.setAutor(autor);
        }

        if (idEditorial != 0) {
            Editorial editorial = sEditorial.buscarPorId(idEditorial);
            l.setEditorial(editorial);

        }

        dao.editar(l);

    }

    public void eliminar(Long isbn) {
        Libro l = buscarPorIsbn(isbn);

        dao.eliminar(l);
    }

    public List<Libro> mostrarTodos() {
        return dao.mostrarTodos();
    }

    public Libro buscarPorIsbn(Long isbn) {
        return dao.buscarPorIsbn(isbn);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return dao.buscarPorTitulo(titulo);
    }

    public List<Libro> buscarPorAutor(int idAutor) {
        Autor autor = sAutor.buscarPorId(idAutor);
        return dao.buscarPorAutor(autor);
    }

    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemPrestados, Integer idAutor, Integer idEditorial) throws Exception { //ejemplares restantes se calcula, alta es por defecto

        if (isbn < 0) {
            throw new Exception("Debe ingresar un ISBN");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }

        if (anio < 0) {
            throw new Exception("Debe ingresar un año");
        }

        if (ejemplares < 0) {
            throw new Exception("Debe ingresar un numero de ejemplares");
        }

        if (ejemPrestados >= 0 && ejemPrestados < ejemplares) {

        } else {
            throw new Exception("La cantidad de ejemplares prestados debe ser igual o menor que la cantidad de ejemplares totales");
        }

        if (idAutor < 0) {
            throw new Exception("El id de autor debe ser mayor a cero");
        }
        Autor a = sAutor.buscarPorId(idAutor);
        if (a == null) {
            throw new Exception("El autor no existe");
        }

        if (idEditorial < 0) {
            throw new Exception("El id de editorial debe ser mayor a cero");
        }
        Editorial e = sEditorial.buscarPorId(idEditorial);
        if (e == null) {
            throw new Exception("La editorial no existe");
        }
        Libro l = buscarPorIsbn(isbn);

        if (l != null) {
            throw new Exception("El libro ya existe");
        }
    }

    public void inexistente(Long isbn) throws Exception {
        Libro l = buscarPorIsbn(isbn);

        if (l == null) {
            throw new Exception("El libro no existe");
        }
    }
}
