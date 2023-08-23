package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Producto;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
                return entityManager.createNamedQuery("Producto.consultarNombrePorPrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
        }

        public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fecha){
                StringBuilder jpql = new StringBuilder( "SELECT p FROM Producto p WHERE 1 = 1 ") ;
                if(nombre != null && !nombre.trim().isEmpty()){
                        jpql.append("AND p.nombre = :nombre");
                }

                if(precio != null && !precio.equals(new BigDecimal(0))){
                        jpql.append(" AND p.precio = :precio");
                }

                if(fecha != null ){
                        jpql.append(" ANd p.fecha = :fecha");
                }

                TypedQuery<Producto> query = entityManager.createQuery(jpql.toString(), Producto.class);

                if(nombre != null && !nombre.trim().isEmpty()){
                        query.setParameter("nombre", nombre);
                }

                if(precio != null && !precio.equals(new BigDecimal(0))){
                        query.setParameter("precio", precio);
                }

                if(fecha != null ){
                        query.setParameter("fecha", fecha);
                }

                return query.getResultList();

        }

        public List<Producto> consultarPorParametrosAPi(String nombre, BigDecimal precio, LocalDate fecha){

                CriteriaBuilder builder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Producto> query = builder.createQuery(Producto.class);

                Root<Producto> from = query.from(Producto.class);
                Predicate filtro = builder.and();

                if(nombre != null && !nombre.trim().isEmpty()){
                        filtro = builder.and(filtro, builder.equal(from.get("nombre"), nombre));
                }

                if(precio != null && !precio.equals(new BigDecimal(0))){
                        filtro = builder.and(filtro, builder.equal(from.get("precio"), precio));
                }

                if(fecha != null ){
                        filtro = builder.and(filtro, builder.equal(from.get("fecha"), fecha));
                }

                query = query.where(filtro);
                return entityManager.createQuery(query).getResultList();



        }


}
