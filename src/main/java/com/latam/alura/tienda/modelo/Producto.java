package com.latam.alura.tienda.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="productos")
@NamedQuery(name="Producto.consultarNombrePorPrecio", query="SELECT P.precio FROM Producto AS P WHERE P.nombre = :nombre")
// we can choose between single_table and join depending on whether we want performance or understanding
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDate fecha = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;

    public Producto(){}
   public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria){
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.precio = precio;
       this.categoria = categoria;
   }


    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
