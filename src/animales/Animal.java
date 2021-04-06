package animales;

import productos.*;
import enums.*;

import static ventanas.Menu.redimensionarProductos;

import javax.swing.JOptionPane;
public abstract class Animal extends Thread{
    
    protected String nombre;
    protected int vida;
    protected double precio;
    protected Producto[] productos;
    protected EstadoAnimal estado;
    protected TipoProducto tipo;
    protected int comidaIngerida;
    protected int cantidadAnimales;
    protected int criasDestazadas;
    public Animal(String nombre, int vida, double precio,TipoProducto tipo){

        this.nombre = nombre;
        this.vida = vida;
        this.precio = precio;
        this.cantidadAnimales = 0;
        this.criasDestazadas=0;
        this.tipo = tipo;
        this.productos = new Producto[0];
        this.estado = EstadoAnimal.VIVO;
    }

    public String getNombre(){return this.nombre;}

    public double getPrecio(){return this.precio;}

    public int getVida(){return this.vida;}

    public String getProducto(int i){return this.productos[i].getNombre();}

    public EstadoAnimal getEstado(){return this.estado;}

    public int getCantidadAnimal(){return this.cantidadAnimales;}

    public int getCriasDestazadas(){return this.criasDestazadas;}

    public void setCantidadAnimal(){this.cantidadAnimales++;} 

    public void setCriasDestazadas(){this.criasDestazadas++;}

    public TipoProducto getTipo(){return this.tipo;}

    @Override
    public abstract void run();
    
    public abstract void comer();


    public void agregarProducto(Producto productoNuevo){

        boolean productoRepetido = false;
        int indice=0;

        for(int i = 0; i < this.productos.length; i++){
            if(productoNuevo.getNombre() == productos[i].getNombre()){
                productoRepetido = true;
                indice = i;
                break;
            }
        }

        if(productoRepetido == true){
            this.productos[indice] = productoNuevo;
        }  else {
            this.productos = redimensionarProductos(this.productos, productoNuevo);
        }
    }

    
}
