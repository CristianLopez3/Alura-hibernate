package com.latam.alura.tienda.prueba;

import com.latam.alura.tienda.dao.PedidoDao;
import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebaDeDesempeno {
    public static void main(String[] args) throws FileNotFoundException {
        LoadRecords.cargarRegistros();
        EntityManager entityManager = JPAUtils.getEntityManager();
        // Pedido pedido = entityManager.find(Pedido.class, 1L);
        PedidoDao pedidoDao = new PedidoDao(entityManager);
        Pedido pedidoConCliente = pedidoDao.consultarPedidoPorCliente(3L);

        /**
         * Con el fin de ver que pasa cuando pasamos los datos de lazy a eager, cerramos la
         * conexion y vemos como nos ayuda el método que creamos.
         */
        entityManager.close();
        // System.out.println(pedido.getItem().size());
        System.out.println(pedidoConCliente.getCliente().getNombre());
        // System.out.println(pedido.getFecha());

    }
}
