package com.latam.alura.tienda.modelo;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="items_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    private BigDecimal precioUnitario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public ItemPedido(){}

    public ItemPedido(int cantidad, Producto producto, Pedido pedido){
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.producto = producto;
        this.precioUnitario = producto.getPrecio();
    }

    public long getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return this.precioUnitario.multiply(new BigDecimal(this.cantidad));
    }


}
