package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    private static EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardar(Categoria categoria){
        entityManager.persist(categoria);
    }

    public void actualizar(Categoria categoria){
        this.entityManager.merge(categoria);
    }

    public void remover(Categoria categoria){
        categoria = this.entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }

    public Categoria consultarPorNombre(String nombre){
        String jpql =" SELECT C FROM Categoria AS C WHERE C.nombre=:nombre ";
        return entityManager.createQuery(jpql,Categoria.class).setParameter("nombre", nombre).getSingleResult();
    }



}
