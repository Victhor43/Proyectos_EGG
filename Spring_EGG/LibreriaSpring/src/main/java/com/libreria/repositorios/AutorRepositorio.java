package com.libreria.repositorios;

import com.libreria.entidades.Autor;
import com.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {

    @Query("SELECT a FROM Autor a WHERE a.nombre LIKE %:nombre% ")
    public List<Autor> buscarPorNombreLista(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE a.alta = 1")
    public List<Autor> autoresAlta();
}
