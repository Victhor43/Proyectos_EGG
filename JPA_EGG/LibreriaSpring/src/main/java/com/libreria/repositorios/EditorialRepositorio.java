package com.libreria.repositorios;

import com.libreria.entidades.Editorial;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE %:nombre% ")
    public List<Editorial> buscarPorNombreLista(@Param("nombre") String nombre);
    

    @Query("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre ")
    public Editorial buscarPorNombreUnico(@Param("nombre") String nombre);
}

