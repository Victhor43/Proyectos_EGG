package com.libreria.servicios;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired  // inyeccion de dependencia
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public Libro crear(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial) throws Exception {

        validar(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
        Libro libro = new Libro(); // nuevo libro vacio

        // id automatico
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);    // se crea el libro, no se prestó ninguno
        libro.setEjemplaresRestantes(ejemplares); // todos los libros en stock
        libro.setAlta(Boolean.TRUE);

        libro.setAutor(autorServicio.buscarPorId(idAutor));
        libro.setEditorial(editorialServicio.buscarPorId(idEditorial));

        libroRepositorio.save(libro);
        return null;

    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void actualizar(String id, Long isbn,
            String titulo,
            Integer anio,
            Integer ejemplares,
            Integer ejemplaresPrestados,
            String idAutor,
            String idEditorial) throws Exception {

        Libro l = buscarPorId(id); /// cambiar
        if (l == null) {

            throw new Exception("El libro NO existe");
        }

        if (!l.getAlta()) {
            throw new Exception("El libro está dado de baja");
        }

        validarActualizar(isbn, titulo, anio, ejemplares, idAutor, idEditorial);

        l.setIsbn(isbn);
        l.setTitulo(titulo);

        l.setAnio(anio);
        l.setEjemplares(ejemplares);

//        if (ejemplaresPrestados != null) {
//            l.setEjemplaresPrestados(l.getEjemplaresPrestados() + ejemplaresPrestados);//PRESTADOS
//            l.setEjemplaresRestantes(l.getEjemplares() - l.getEjemplaresPrestados());//RESTANTES
//        }
        l.setAutor(autorServicio.buscarPorId(idAutor));
        l.setEditorial(editorialServicio.buscarPorId(idEditorial));

        System.out.println(l.getAutor().getNombre());
        System.out.println(l.getEditorial().getNombre());

        libroRepositorio.save(l);

    }

    @Transactional(readOnly = true)
    public List<Libro> mostrarTodos() {

        return libroRepositorio.findAll();

    }

    
    @Transactional(readOnly = true)
    public List<Libro> librosAlta() {

        return libroRepositorio.librosAlta();
    }
    
     @Transactional(readOnly = true)
    public List<Libro> librosBaja() {

        return libroRepositorio.librosBaja();
    }
    

    @Transactional(readOnly = true)
    public Libro buscarPorId(String id) {

        Optional<Libro> respuesta = libroRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            return libro;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Libro> buscarPorTitulo(String titulo) {

        List<Libro> libros = libroRepositorio.buscarPorTitulo(titulo);

        return libros;

    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void baja(String id) throws Exception {

        Libro l = buscarPorId(id);
        if (l == null) {
            throw new Exception("El libro NO existe");
        }

        if (!l.getAlta()) {
            throw new Exception("El libro ya está dado de baja");
        }

        l.setAlta(Boolean.FALSE);

        libroRepositorio.save(l);
    }

    
    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void alta(String id) throws Exception {

        Libro l = buscarPorId(id);
        if (l == null) {
            throw new Exception("El libro NO existe");
        }

        if (l.getAlta()) {
            throw new Exception("El libro ya está dado de alta");
        }

        l.setAlta(Boolean.TRUE);

        libroRepositorio.save(l);
    }

    public void validar(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial) throws Exception {

        if (isbn == null) {
            throw new Exception("Debe ingresar un ISBN");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }

        if (anio == null) {
            throw new Exception("Debe ingresar un año");
        }

        if (ejemplares < 0) {
            throw new Exception("Debe ingresar un numero de ejemplares");
        }

        if (idAutor == null || idAutor.isEmpty()) {
            throw new Exception("El id de autor debe ser mayor a cero");
        }
        Autor a = autorServicio.buscarPorId(idAutor);
        if (a == null) {
            throw new Exception("El autor no existe");
        }

        Editorial e = editorialServicio.buscarPorId(idEditorial);
        if (e == null) {
            throw new Exception("La editorial no existe");
        }

        Libro l = libroRepositorio.buscarPorIsbn(isbn);
        if (l != null) {
            throw new Exception("El libro ya existe");
        }

    }

    public void validarActualizar(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial) throws Exception {

        if (isbn == null) {
            throw new Exception("Debe ingresar un ISBN");
        }

        if (titulo == null || titulo.isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }

        if (anio == null) {
            throw new Exception("Debe ingresar un año");
        }

        if (ejemplares < 0) {
            throw new Exception("Debe ingresar un numero de ejemplares");
        }

        if (idAutor == null || idAutor.isEmpty()) {
            throw new Exception("El id de autor debe ser mayor a cero");
        }
        Autor a = autorServicio.buscarPorId(idAutor);
        if (a == null) {
            throw new Exception("El autor no existe");
        }

        Editorial e = editorialServicio.buscarPorId(idEditorial);
        if (e == null) {
            throw new Exception("La editorial no existe");
        }

    }

}
