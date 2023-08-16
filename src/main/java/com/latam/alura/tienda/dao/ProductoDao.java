package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

public class ProductoDao {
        private EntityManager entityManager;
        public ProductoDao(EntityManager em){
                this.entityManager = em;
        }

        public void guardar(Producto producto){
                this.entityManager.persist(producto);
        }

        public void actualizar(Producto producto){
                this.entityManager.merge(producto);
        }

        public void remover(Producto producto){
                producto = this.entityManager.merge(producto);
                this.entityManager.remove(producto);
        }
}
