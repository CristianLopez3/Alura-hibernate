package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Cliente;
import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

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


    public List<Cliente> consultarTodos(){
        String jqpl= "SELECT P FROM Cliente AS P";
        return entityManager.createQuery(jqpl,Cliente.class).getResultList();
    }

    public List<Cliente> consultaPorNombre(String nombre){
        String jpql =" SELECT P FROM Cliente AS P WHERE P.nombre=:nombre ";
        return entityManager.createQuery(jpql,Cliente.class).setParameter("nombre", nombre).getResultList();
    }
}
