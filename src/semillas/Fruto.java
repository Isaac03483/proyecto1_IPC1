package semillas;

import javax.swing.*;

import enums.EstadoGrama;

import java.awt.*;

public class Fruto extends Planta{

    static ImageIcon imagenFruto = new ImageIcon("manzana.png");
    static ImageIcon imagenListo = new ImageIcon("manzanaLista.png");
    private int cantidadProducto;

    //Constructor
    public Fruto(String nombre, int vida, double precio){
        super(nombre, vida, precio);
    }

    @Override
    public void run(){ //método que pone a correr el hilo de vida del fruto
        
        int duracionVida = (this.vida/this.producto.getCantidad());
        this.cantidadProducto=0;
        this.imagenEtiqueta.setVisible(true);
        colocarImagen(imagenFruto);
        this.terreno.cambiarEstado(EstadoGrama.CONSIEMBRA);

        do{
            
            this.terreno.setToolTipText("Tiempo de vida restante: "+this.vida);
            try{

                Fruto.sleep(duracionVida*1000);
                
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Fruto.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

            
            if(this.terreno.getEstado() == EstadoGrama.CONSIEMBRA){

                colocarImagen(imagenListo);
                this.terreno.cambiarEstado(EstadoGrama.FRUTOLISTO);
            }

            this.vida-=duracionVida;
            this.cantidadProducto++;
            
            
        } while(this.vida > 0);

        this.imagenEtiqueta.setVisible(false);
        this.terreno.cambiarEstado(EstadoGrama.DISPONIBLE);
        JOptionPane.showMessageDialog(null, "La siembra de "+this.producto.getNombre()+" ha muerto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);

        this.terreno.setToolTipText("");

    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));

    }


    public int getCantidadProducto(){ //retorna la cantidad de producto generado por el fruto
        int intercambio;
        intercambio = this.cantidadProducto;
        this.cantidadProducto=0;
        colocarImagen(imagenFruto);
        return intercambio;
    }

    
    
}
