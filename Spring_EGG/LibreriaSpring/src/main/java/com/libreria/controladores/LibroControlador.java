package com.libreria.controladores;

import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.servicios.EditorialServicio;
import com.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @PostMapping("/crearLibro")
    public String crearLibro(ModelMap modelo, @RequestParam(required = false) Long isbn, @RequestParam(required = false) String titulo, @RequestParam(required = false) Integer anio,
            @RequestParam(required = false) Integer ejemplares, @RequestParam(required = false) String idAutor, @RequestParam(required = false) String idEditorial) {
        try {

            libroServicio.crear(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
            modelo.put("cargado", titulo + " se cargó correctamente");
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());

            modelo.put("isbn", isbn);
            modelo.put("titulo", titulo);
            modelo.put("anio", anio);
            modelo.put("ejemplares", ejemplares);

            return "index.html";
        }

        return "index.html";

    }

    @PostMapping("/editarLibro")
    public String editarLibro(ModelMap modelo, @RequestParam() Long isbn, @RequestParam() String titulo, @RequestParam() Integer anio,
            @RequestParam() Integer ejemplares, @RequestParam() String idAutor, @RequestParam() String idEditorial) {

        modelo.put("isbn", isbn);
        modelo.put("titulo", titulo);
        modelo.put("anio", anio);
        modelo.put("ejemplares", ejemplares);
        return null;

    }

    @GetMapping("/libros")
    public String libros(ModelMap modelo) {
        List<Libro> libros = libroServicio.mostrarTodos();
        modelo.put("libros", libros);
        
        return "libros.html";
    }

}
