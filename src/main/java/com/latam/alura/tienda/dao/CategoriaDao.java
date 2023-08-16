package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Categoria;

import javax.persistence.EntityManager;

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


}
