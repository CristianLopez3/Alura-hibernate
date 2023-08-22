package com.latam.alura.tienda.dao;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.vo.RelatorioDeVentas;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


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

    public List<Pedido> consultarTodos(){
        String jpql = "SELECT P FROM Pedido AS P";
        return entityManager.createQuery(jpql, Pedido.class).getResultList();
    }

    /**
     * En las consultas Jpql cuando hacemos la referencia del from, nos referimos a la entidad en nuestro codigo,
     * por lo tal colocamos el nombre de la clase más no el nombre de la base de datos.
     * @return BigDecimal
     */
    public BigDecimal valorTotalVendido(){
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido as p";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public BigDecimal valorPromedioVendido() {
        String jpql = "SELECT AVG(P.valorTotal) FROM Pedido AS P";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<Object[]> relatorioDeVentas(){
        String jpql = "SELECT producto.nombre, " +
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha) " +
                "FROM Pedido pedido " +
                "jOIN pedido.item item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return entityManager.createQuery(jpql, Object[].class).getResultList();
    }

    /**
     * Cuando añadimos código java en nuestra consulta a la base de datos es necesario especificar todo
     * el nombre de la clase, eso incluye los paquetes en los que ella se encuentra, como se observa en la consulta
     * de la siguiente función.
     * @return
     */
    public List<RelatorioDeVentas> relatorioDeVentasVO(){
        String jpql = "SELECT new com.latam.alura.tienda.vo.RelatorioDeVentas (producto.nombre, " +
                "SUM(item.cantidad), " +
                "MAX(pedido.fecha)) " +
                "FROM Pedido pedido " +
                "jOIN pedido.item item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return entityManager.createQuery(jpql, RelatorioDeVentas.class).getResultList();
    }





}
