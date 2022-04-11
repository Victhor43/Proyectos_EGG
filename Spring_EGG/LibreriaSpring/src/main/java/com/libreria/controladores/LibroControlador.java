package com.libreria.controladores;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Editorial;
import com.libreria.entidades.Libro;
import com.libreria.servicios.AutorServicio;
import com.libreria.servicios.EditorialServicio;
import com.libreria.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private AutorServicio autorServicio;

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
    public String editarLibro(RedirectAttributes attr, @RequestParam() String id, @RequestParam() Long isbn, @RequestParam() String titulo, @RequestParam() Integer anio,
            @RequestParam() Integer ejemplares, @RequestParam() String idAutor, @RequestParam() String idEditorial) {

        try {
            libroServicio.actualizar(id, isbn, titulo, anio, ejemplares, ejemplares, idAutor, idEditorial);
            attr.addFlashAttribute("actualizado", titulo + " se actualizó correctamente");

        } catch (Exception ex) {
            attr.addFlashAttribute("errorActualizado", ex.getMessage());
        }

        return "redirect:/libros"; // para mostrar el mensaje necesito un redirect en lugar de un modal

    }

    @GetMapping("/libros")
    public String libros(ModelMap modelo, RedirectAttributes attr, @RequestParam(required = false) String search, @RequestParam(required = false) boolean baja) {
        List<Libro> libros;

        if (baja && search != null) {
            libros = libroServicio.librosBaja(search);
        } else {
            if (baja) {
                libros = libroServicio.librosBaja();
                modelo.put("baja", baja);
            } else {
                if (search == null) {
                    libros = libroServicio.librosAlta();

                } else {
                    modelo.put("search", search);
                    libros = libroServicio.buscarPorTitulo(search);
                }
            }
        }
        modelo.put("libros", libros);
        List<Editorial> editoriales = editorialServicio.mostrarTodos();
        modelo.put("editoriales", editoriales);

        List<Autor> autores = autorServicio.mostrarTodos();
        modelo.put("autores", autores);

        return "libros.html";
    }

    @GetMapping("/bajaLibro")
    public String bajaLibro(@RequestParam String id, RedirectAttributes attr
    ) {

        try {
            libroServicio.baja(id);
        } catch (Exception ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/libros";
    }

    @GetMapping("/altaLibro")
    public String altaLibro(@RequestParam String id, RedirectAttributes attr) {

        try {
            libroServicio.alta(id);
            attr.addFlashAttribute("error", "Se dio de alta correctamente");
        } catch (Exception ex) {
            attr.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/libros";
    }
//    @GetMapping("/librosSerch")
//    public String librosSerch(ModelMap modelo,  @RequestParam()String buscar) {
//        List<Libro> libros = libroServicio.mostrarTodos();
//        modelo.put("libros", libros);
//
//        List<Editorial> editoriales = editorialServicio.mostrarTodos();
//        modelo.put("editoriales", editoriales);
//
//        List<Autor> autores = autorServicio.mostrarTodos();
//        modelo.put("autores", autores);
//        
//        System.out.println(buscar + " LIBROSERCH");
//        return "librosSerch.html";
//    }
//    @PostMapping("/listaLibros")
//    public String listaLibros(RedirectAttributes attr,@RequestParam() String buscar) {
//
//        System.out.println(buscar + " LISTALIBRO" );
//        
//        return "redirect:/librosSerch.html";
//    }
}
