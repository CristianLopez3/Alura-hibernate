package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ClienteDao;
import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.*;
import com.latam.alura.tienda.utils.JPAUtils;


import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedido {

    public static void main(String args[]){
        registrarProducto();
        EntityManager entityManager = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(entityManager);
        CategoriaDao categoriaDao  = new CategoriaDao(entityManager);
        PedidoDao pedidoDao = new PedidoDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);

        Categoria celulares = new Categoria("Celulares");
        Producto producto = productoDao.consultarPorId(1l);

        Cliente cliente = new Cliente("Juan", "ABC1234");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemPedido(5, producto, pedido));

        /*-----------------------------------------------------------------------------------*/
        entityManager.getTransaction().begin();
        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);
        entityManager.getTransaction().commit();
        /*-----------------------------------------------------------------------------------*/

        BigDecimal valorTotal = pedidoDao.valorTotalVendido();
        System.out.println(valorTotal);

        /*-----------------------------------------------------------------------------------*/

        List<Object[]> relatorio = pedidoDao.relatorioDeVentas();
        relatorio.forEach(objects -> {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            System.out.println(objects[2]);
        });

        /*-----------------------------------------------------------------------------------*/



    }

    public static void registrarProducto(){
        Categoria celulares = new Categoria("Celulares");
        Producto celular = new Producto("Samsung", "Celular usado", new BigDecimal(9000), celulares);
        Producto computador = new Producto("Dell", "Dell ultima generacion", new BigDecimal(2000), celulares);

        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();
        /*-------------------------------------------*/
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);
        productoDao.guardar(computador);
        /*-------------------------------------------*/
        entityManager.getTransaction().commit(); // --> Pasamos la entidad a estado detached
        entityManager.close();
        /*-------------------------------------------*/
    }

}











