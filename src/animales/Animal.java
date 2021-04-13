package animales;

import productos.*;
import terrenos.Parcela;
import enums.*;

import static ventanas.Menu.redimensionarProductos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
public abstract class Animal extends Thread{
    
    protected String nombre;
    protected int vida;
    protected double precio;
    protected Producto[] productos;
    protected TipoProducto tipo;
    protected int comidaIngerida;
    protected int cantidadAnimales;
    protected int criasDestazadas;
    protected Parcela terreno;
    protected JLabel imagenEtiqueta;

    { //método oculto que inicializa valores en 0
        this.cantidadAnimales = 0;
        this.criasDestazadas=0;
        this.comidaIngerida=0;
        this.productos = new Producto[0];
    }

    //constructor
    public Animal(String nombre, int vida, double precio,TipoProducto tipo){

        this.nombre = nombre;
        this.vida = vida;
        this.precio = precio;
        this.tipo = tipo;
    }

    //getters y setters

    public String getNombre(){return this.nombre;}

    public double getPrecio(){return this.precio;}

    public int getVida(){return this.vida;}

    public Producto[] getArregloProductos(){return this.productos;}  

    public int getCantidadAnimal(){return this.cantidadAnimales;}

    public int getCriasDestazadas(){return this.criasDestazadas;}

    public void setCantidadAnimal(){this.cantidadAnimales++;} 

    public void setCriasDestazadas(){this.criasDestazadas++;}

    public TipoProducto getTipo(){return this.tipo;}

    @Override
    public abstract void run();

    protected abstract void colocarImagen(ImageIcon imagen); //método abstracto
    
    //método que agrega productos al arreglo productos de los animales
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

    public void elegirTerreno(Parcela terreno, JLabel objeto){ //método que almacena el espacio en memoria del terreno y su etiqueta en el que se colocará el animal

        this.terreno=terreno;
        this.imagenEtiqueta=objeto;
    }

    
}
