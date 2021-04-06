package semillas;

import java.awt.Image;

import javax.swing.*;

import enums.EstadoGrama;
import terrenos.Grama;

public class Grano extends Planta{
    

    static ImageIcon imagenGrano = new ImageIcon("maiz.png");
    static ImageIcon imagenListo = new ImageIcon("maizListo.png");

    public Grano(String nombre, int vida, double precio){
        super(nombre, vida, precio);

    }


    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){
        int tiempoVida = this.vida;
        this.terreno.cambiarEstado(EstadoGrama.CONSIEMBRA);
        this.imagenEtiqueta.setVisible(true);
        colocarImagen(imagenGrano);
        do {
            try {
                Grano.sleep(1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Grano.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            tiempoVida--;
            this.terreno.setToolTipText("Cosechando: "+this.nombre+" tiempo restante: "+tiempoVida);
        } while(tiempoVida > 0);

        
        if(tiempoVida == 0){
            this.terreno.cambiarEstado(EstadoGrama.SIEMBRALISTA);
            colocarImagen(imagenListo);
        
        }
    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
        

    }

}
