package com.latam.alura.tienda.modelo;

import javax.persistence.*;

@Entity
@Table(name="clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private DatosPersonales datosPersonales;


    public Cliente(){}

    public Cliente(String nombre, String dni){
        this.datosPersonales = new DatosPersonales(nombre, dni);
    }

    public long getId(){
        return this.id;
    }

    public String getNombre() {
        return datosPersonales.getNombre();
    }

    public void setNombre(String nombre) {
        this.datosPersonales.setNombre(nombre);
    }


    public String getDni() {
        return datosPersonales.getDni();
    }

    public void setDni(String dni) {
        this.datosPersonales.setDni(dni);
    }
}
