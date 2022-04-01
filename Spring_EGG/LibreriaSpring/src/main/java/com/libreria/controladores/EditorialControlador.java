package com.libreria.controladores;

import com.libreria.servicios.EditorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @PostMapping("/crearEditorial")
    public String crearEditorial(ModelMap modelo, String nombre) {
        try {
            editorialServicio.crear(nombre);
            modelo.put("cargado", nombre + " se cargó correctamente");

        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        }
        return "index.html";
    }
}
