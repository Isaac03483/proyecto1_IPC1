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
    protected Alimento alimento;
    protected Producto producto;

    public Planta(String nombre, int vida, double precio){
        this.nombre = nombre;
        this.vida = vida;
        this.precio = precio;
        this.cantidadAdquirida=0;
        this.celdasSembradas=0;
        this.producto = null;
        
    }

    public void setCantidadAdquirida(){this.cantidadAdquirida++;}

    public void setCeldasSembradas(){this.celdasSembradas++;}

    public String getNombre(){return this.nombre;}

    public int getVida(){return this.vida;}

    public double getPrecio(){return this.precio;}

    public int getCantidad(){return this.cantidadAdquirida;}

    public int getCeldas(){return this.celdasSembradas;}

    public Producto getProducto(){return this.producto;}

    public void asignarAlimento(Alimento alimento){
        this.alimento = alimento;
    }

    @Override
    public abstract void run();

    
    public abstract void colocarImagen(ImageIcon imagen);

    public abstract void elegirTerreno(Grama terreno, JLabel etiqueta);

    public void agregarProducto(Producto productoNuevo){

        boolean productoCreado = false;
        
        if(this.producto != null){
            productoCreado=true;
        }

        if(productoCreado==true){
            JOptionPane.showMessageDialog(null, "Esta planta ya tiene asignado un producto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.producto = productoNuevo;
        }

        
    }
    
}
