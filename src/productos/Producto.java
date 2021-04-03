package productos;

import enums.TipoProducto;

public class Producto {

    protected String nombre;
    protected double precio;
    protected int cantidad;
    protected TipoProducto tipoProducto;

    public Producto(String nombre, double precio, int cantidad, TipoProducto tipo){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.tipoProducto = tipo;
    }

    public String getNombre(){return this.nombre;}

    public double getPrecio(){return this.precio;}

    public int getCantidad(){return this.cantidad;}

    public TipoProducto getTipoProducto(){return this.tipoProducto;}

    public void restarCantidad(int cantidad){this.cantidad -= cantidad;}

    public void aumentarCantidad(int cantidad){this.cantidad+=cantidad;}

    @Override
    public String toString(){return this.nombre;}
    
}
