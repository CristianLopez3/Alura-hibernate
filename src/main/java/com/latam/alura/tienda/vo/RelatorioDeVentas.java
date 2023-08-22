package com.latam.alura.tienda.vo;



import java.time.LocalDate;

public class RelatorioDeVentas {
    private String nombreDelProducto;
    private long cantidadDeProducto;
    private LocalDate fechaDeUltimaVenta;

    public RelatorioDeVentas(String nombreDelProducto, long cantidadDeProducto, LocalDate fechaDeUltimaVenta){
        this.nombreDelProducto = nombreDelProducto;
        this.cantidadDeProducto = cantidadDeProducto;
        this.fechaDeUltimaVenta = fechaDeUltimaVenta;
    }

    public String getNombreDelProducto() {
        return nombreDelProducto;
    }

    public void setNombreDelProducto(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }

    public long getCantidadDeProducto() {
        return cantidadDeProducto;
    }

    public void setCantidadDeProducto(long cantidadDeProducto) {
        this.cantidadDeProducto = cantidadDeProducto;
    }

    public LocalDate getFechaDeUltimaVenta() {
        return fechaDeUltimaVenta;
    }

    public void setFechaDeUltimaVenta(LocalDate fechaDeUltimaVenta) {
        this.fechaDeUltimaVenta = fechaDeUltimaVenta;
    }

    @Override
    public String toString(){
        return "Sales report:\n product name "+this.nombreDelProducto+"" +
                "\nproducto quantity: "+this.cantidadDeProducto+"" +
                "\ndate of the last sale: "+this.fechaDeUltimaVenta;
    }
}
