package com.libreria.controladores;

import com.libreria.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @PostMapping("/crearAutor")
    public String crearAutor(ModelMap modelo, @RequestParam String nombre) {
        try {
            autorServicio.crear(nombre);
             modelo.put("cargado", nombre + " se cargó correctamente");
             
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        }
        return "index.html";
    }
}
