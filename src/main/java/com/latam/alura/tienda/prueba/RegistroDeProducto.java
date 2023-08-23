package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;


public class RegistroDeProducto {

    public static void main(String[] args){
        registrarProducto();
        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);
        Producto producto = productoDao.consultarPorId(1L);
        System.out.println(producto.getNombre());

        // List<Producto> productos = productoDao.consultarPorNombreCategoria("Dell");
        BigDecimal precio = productoDao.consultarPrecioPorNombre("Samsung");
        System.out.println(precio);
    }

    public static void registrarProducto(){
        Categoria celulares = new Categoria("Celulares");
        Producto celular = new Producto("Samsung", "Celular usado", new BigDecimal(1000), celulares);
        Producto computador = new Producto("Dell", "Dell ultima generación", new BigDecimal(2000), celulares);

        EntityManager entityManager = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();
        /*-------------------------------------------*/
        categoriaDao.guardar(celulares);
        productoDao.guardar(celular);
        productoDao.guardar(computador);
        /*-------------------------------------------*/
        entityManager.getTransaction().commit(); // --> Pasamos la entidad ha estado detached
        entityManager.close();
        /*-------------------------------------------*/
    }


}
