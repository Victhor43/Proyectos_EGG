package com.libreria.servicios;

import com.libreria.entidades.Editorial;
import com.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public Editorial crear(String nombre) throws Exception {

        Editorial e = new Editorial();
        validar(nombre);

        e.setAlta(Boolean.TRUE);
        e.setNombre(nombre);

        return editorialRepositorio.save(e);
    }

    @Transactional(readOnly = true)
    public List<Editorial> mostrarTodos() {

        return editorialRepositorio.findAll();

    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void actualizar(String idEditorial, String nombre) throws Exception {

        Editorial e = buscarPorId(idEditorial);
        if (e == null) {
            throw new Exception("No existe la editorial");
        }else{
            e.setNombre(nombre);
            editorialRepositorio.save(e);
        }
    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
   public void baja(String nombre) throws Exception{
       
       Editorial e = (Editorial) buscarPorNombreUnico(nombre);
        if (e == null) {
            throw new Exception("La editorial NO existe");

        }
       
        if(!e.getAlta()){
            throw new Exception ("La editorial ya esta dada de baja");
        }else{
            e.setAlta(Boolean.FALSE);
        }
        
   }
           
    
    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe el nombre de la editorial");
        }

        Editorial e = (Editorial) buscarPorNombreUnico(nombre);

        if (e != null) {
            throw new Exception("La editorial ya existe");

        }
    }

    @Transactional(readOnly = true)
    public List<Editorial> buscarPorNombreLista(String nombre) {
        return editorialRepositorio.buscarPorNombreLista(nombre); //return editorialRepositorio.buscarPorNombreLista("%" + nombre + "%");
    }

    @Transactional(readOnly = true)
    public List buscarPorNombreUnico(String nombre) {
        return (List) editorialRepositorio.buscarPorNombreUnico(nombre);
    }

    @Transactional(readOnly = true)
    public Editorial buscarPorId(String id) throws Exception {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            return editorial;
        } else {
            return null;
        }
    }

}
