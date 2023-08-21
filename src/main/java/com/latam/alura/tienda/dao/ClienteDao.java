package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Cliente;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager entityManager;
    public ClienteDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardar(Cliente cliente){
        this.entityManager.persist(cliente);
    }

    public void actualizar(Cliente cliente){
        this.entityManager.merge(cliente);
    }

    public void remover(Cliente cliente){
        cliente = this.entityManager.merge(cliente);
        this.entityManager.remove(cliente);
    }


}
