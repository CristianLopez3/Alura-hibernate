package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal valorTotal;
    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy="pedido")
    private List<ItemPedido> items = new ArrayList<>();


    public Pedido(){}

    public Pedido (Cliente cliente){
        this.cliente = cliente;
    }

    public void agregarItems(ItemPedido item){
        item.setPedido(this);
        this.items.add(item);
    }

    public long getId() {
        return id;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
