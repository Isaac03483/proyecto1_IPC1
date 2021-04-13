package semillas;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import productos.Alimento;
import productos.Producto;
import terrenos.Grama;

public abstract class Planta extends Thread{

    protected String nombre;
    protected Grama terreno;
    protected JLabel imagenEtiqueta;
    protected int vida;
    protected double precio;
    protected int cantidadAdquirida;
    protected int celdasSembradas;
    protected Alimento producto;

    {
        this.cantidadAdquirida=0;
        this.celdasSembradas=0;
        this.producto = null;
    }

    //constructor
    public Planta(String nombre, int vida, double precio){
        this.nombre = nombre;
        this.vida = vida;
        this.precio = precio;
        
    }
    
    //getters y setters
    @Override
    public String toString(){return this.nombre;}

    public void setCantidadAdquirida(){this.cantidadAdquirida++;}

    public void setCeldasSembradas(){this.celdasSembradas++;}

    public String getNombre(){return this.nombre;}

    public int getVida(){return this.vida;}

    public double getPrecio(){return this.precio;}

    public int getCantidad(){return this.cantidadAdquirida;}

    public int getCeldas(){return this.celdasSembradas;}

    public Producto getProducto(){return this.producto;}

    @Override
    public abstract void run();

    public abstract void colocarImagen(ImageIcon imagen);

    public void elegirTerreno(Grama terreno, JLabel etiqueta) { //hace referencia al espacio en memoria del terreno y su objeto en el que se colocará la planta

        this.terreno = terreno;
        this.imagenEtiqueta = etiqueta;
            
    }

    public void agregarProducto(Producto productoNuevo){ //agrega el producto a la planta en caso de que no tenga
    
        this.producto = (Alimento)productoNuevo;
    
    }

    public boolean productoAsignado(){ //retorna un valor booleano dependiendo del estado del producto de la planta

        if(this.producto != null){
            return true;
        } else {
            return false;
        }
    }
    
}
