package com.libreria.servicios;

import com.libreria.entidades.Autor;
import com.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public Autor crear(String nombre) throws Exception {

        validar(nombre);
        Autor a = new Autor();

        a.setAlta(Boolean.TRUE);
        a.setNombre(nombre);

        return autorRepositorio.save(a);
    }

    @Transactional(readOnly = true)
    public List<Autor> mostrarTodos() {

        return autorRepositorio.findAll();

    }

     @Transactional(readOnly = true)
    public List<Autor> autoresAlta() {

        return autorRepositorio.autoresAlta();

    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void actualizar(String idEditorial, String nombre) throws Exception {

        Autor a = buscarPorId(idEditorial);
        if (a == null) {
            throw new Exception("No existe la editorial");
        } else {
            a.setNombre(nombre);
            autorRepositorio.save(a);
        }
    }

    @Transactional(rollbackFor = Exception.class) // si algo falla, no modifica la DB
    public void baja(String nombre) throws Exception {

        List<Autor> a = buscarPorNombreLista(nombre);
        if (a == null) {
            throw new Exception("La editorial NO existe");

        }
//BUSCAR POR ID 
//        if (!a.getAlta()) {
//            throw new Exception("La editorial ya esta dada de baja");
//        } else {
//            a.setAlta(Boolean.FALSE);
//        }

    }

    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new Exception("Debe ingresar nombre de autor");
        }

        List<Autor> autores = buscarPorNombreLista(nombre);
        System.out.println("autores = " + autores);
        if (!autores.isEmpty()) {
            throw new Exception("El autor ya existe");

        }
    }

    @Transactional(readOnly = true)
    public List<Autor> buscarPorNombreLista(String nombre) {
        return autorRepositorio.buscarPorNombreLista(nombre); //return editorialRepositorio.buscarPorNombreLista("%" + nombre + "%");
    }

    @Transactional(readOnly = true)
    public Autor buscarPorId(String id) throws Exception {

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            return autor;
        } else {
            return null;
        }
    }

}
