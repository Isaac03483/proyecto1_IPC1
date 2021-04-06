package semillas;

import javax.swing.*;

import enums.EstadoGrama;

import java.awt.*;
import static ventanas.Juego.actualizarCeldasSembradas;

public class Fruto extends Planta{

    static ImageIcon imagenFruto = new ImageIcon("manzana.png");
    static ImageIcon imagenListo = new ImageIcon("manzanaLista.png");
    private int cantidadProducto;

    public Fruto(String nombre, int vida, double precio){
        super(nombre, vida, precio);
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){
        int  tiempoVida=this.vida;
        int duracionVida = this.getVida()/this.producto.getCantidad();
        this.cantidadProducto=0;
        this.imagenEtiqueta.setVisible(true);
        colocarImagen(imagenFruto);
        this.terreno.cambiarEstado(EstadoGrama.CONSIEMBRA);

        do{
            
            this.terreno.setToolTipText("Tiempo de vida restante: "+tiempoVida);
            try{

                Fruto.sleep(duracionVida*1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Fruto.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

            
            if(this.terreno.getEstado() == EstadoGrama.CONSIEMBRA){
                tiempoVida-=duracionVida;
                this.cantidadProducto++;
                
                colocarImagen(imagenListo);
                this.terreno.cambiarEstado(EstadoGrama.FRUTOLISTO);
            } else {
                tiempoVida-=duracionVida;
                this.cantidadProducto++;
            }
            
            
        } while(tiempoVida > 0);

        this.imagenEtiqueta.setVisible(false);
        this.terreno.cambiarEstado(EstadoGrama.DISPONIBLE);
        JOptionPane.showMessageDialog(null, "La siembra de "+this.producto.getNombre()+" ha muerto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        actualizarCeldasSembradas(this);
        

    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));

    }


    public int getCantidadProducto(){
        int intercambio;
        intercambio = this.cantidadProducto;
        this.cantidadProducto=0;
        colocarImagen(imagenFruto);
        return intercambio;
    }

    
    
}
