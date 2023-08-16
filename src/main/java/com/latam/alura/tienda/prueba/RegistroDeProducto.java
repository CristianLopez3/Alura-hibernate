package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.CategoriaDao;
import com.latam.alura.tienda.dao.ProductoDao;
import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.modelo.Producto;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegistroDeProducto {

    public static void main(String args[]){
        Categoria celulares = new Categoria("Celulares");

        EntityManager entityManager = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);


        entityManager.getTransaction().begin();
        /*-------------------------------------------*/
        categoriaDao.guardar(celulares);
        celulares.setNombre("LIBROS");
        /*-------------------------------------------*/
        entityManager.flush();
        entityManager.clear();  // --> Pasamos la entidad a estado detached
        /*-------------------------------------------*/
        celulares.setNombre("Software");  // -> para actualizar el dato, primero tenemos que estar en estado managed.
        celulares = entityManager.merge(celulares); // --> Pasamos la entidad a estado persistente o managed.
        entityManager.flush(); // --> Sincronizamos los datos con la base de datos.
        entityManager.clear(); // --> Pasamos los datos a estado detached.
        /*-------------------------------------------*/
        celulares = entityManager.merge(celulares);
        entityManager.remove(celulares);
        entityManager.flush();
        // entityManager.commit(); --> No permite hacer un rollback.
        /*-------------------------------------------*/

    }


}
