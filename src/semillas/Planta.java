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

    public Planta(String nombre, int vida, double precio){
        this.nombre = nombre;
        this.vida = vida;
        this.precio = precio;
        
    }
    
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

    public void elegirTerreno(Grama terreno, JLabel etiqueta) {

        this.terreno = terreno;
        this.imagenEtiqueta = etiqueta;
            
    }

    public void agregarProducto(Producto productoNuevo){
    
        this.producto = (Alimento)productoNuevo;
    
    }

    public boolean productoAsignado(){

        if(this.producto!= null){
            return true;
        } else {
            return false;
        }
    }
    
}
