package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

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

        public Producto consultarPorId(Long id){
                return entityManager.find(Producto.class, id);
        }

        public List<Producto> consultarTodos(){
                String jpql = "SELECT P FROM Producto AS P";
                return entityManager.createQuery(jpql, Producto.class).getResultList();
        }

        public List<Producto> consultarPorNombre(String nombre){
                String jpql = "SELECT P FROM Producto as P WHERE P.nombre = :nombre"; // para añadir mas parametros  usamos el and
                return entityManager.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
        }

        public List<Producto> consultarPorNombreCategoria(String nombre){
                String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre = :nombre";
                return entityManager.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
        }

        public BigDecimal consultarPrecioPorNombre(String nombre){
                String jpql = "SELECT P.precio FROM Producto AS P WHERE P.nombre = :nombre";
                return entityManager.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
        }

}
