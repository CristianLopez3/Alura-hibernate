package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;
import javax.persistence.EntityManager;


public class PedidoDao {
    private EntityManager entityManager;
    public PedidoDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void guardar(Pedido pedido){
        this.entityManager.persist(pedido);
    }

    public void actualizar(Pedido pedido){
        this.entityManager.merge(pedido);
    }

    public void remover(Pedido pedido){
        pedido = this.entityManager.merge(pedido);
        this.entityManager.remove(pedido);
    }


    public Pedido consultarPorId(Long id){
        return entityManager.find(Pedido.class, id);
    }




}
