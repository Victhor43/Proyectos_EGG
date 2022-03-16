package jpa_libreria.persistenca;

import java.util.List;
import jpa_libreria.entidades.Autor;
import jpa_libreria.entidades.Libro;

public final class LibroDAO extends DAO {

    public void guardar(Libro objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
        }
    }

    public Libro editar(Libro objeto) {
        Libro l = null;
        try {
            conectar();
            em.getTransaction().begin();
            l = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return l;
    }

    public void eliminar(Libro objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public List<Libro> mostrarTodos() {
        List<Libro> libros = null;
        try {
            conectar();
            libros = em.createQuery("SELECT l FROM Libro l").getResultList();

            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return libros;

    }

    public Libro buscarPorIsbn(Long isbn) {
        Libro libro = null;
        try {
            conectar();
            libro = em.find(Libro.class, isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally { 
            desconectar();
        }

        return libro;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = null;

        try {
            conectar();

            libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList();

            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return libros;
    }

    public List<Libro> buscarPorAutor(Autor autor) {
        List<Libro> libros = null;
        try {
            conectar();

            libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor LIKE %:autor %")
                    .setParameter("autor", autor)
                    .getResultList();

            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }

        return libros;

    }
}
