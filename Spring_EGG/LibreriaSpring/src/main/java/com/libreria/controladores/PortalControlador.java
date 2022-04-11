package com.libreria.controladores;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.servicios.AutorServicio;
import com.libreria.servicios.EditorialServicio;
import com.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.mostrarTodos();
        modelo.put("editoriales", editoriales);

        List<Autor> autores = autorServicio.autoresAlta();
        modelo.put("autores", autores);

        List<Libro> libros = libroServicio.mostrarTodos();
        modelo.put("libros", libros);

        return "index.html";
    }

   
}
