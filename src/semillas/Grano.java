package semillas;

import java.awt.Image;

import javax.swing.*;

import enums.EstadoGrama;

public class Grano extends Planta{
    

    static ImageIcon imagenGrano = new ImageIcon("maiz.png");
    static ImageIcon imagenListo = new ImageIcon("maizListo.png");
    static ImageIcon imagenMuerto = new ImageIcon("maizMuerto.png");

    //constructor
    public Grano(String nombre, int vida, double precio){
        super(nombre, vida, precio);

    }

    @Override
    public void run(){ //pone a correr el hilo de vida del grano

        this.terreno.cambiarEstado(EstadoGrama.CONSIEMBRA);
        this.imagenEtiqueta.setVisible(true);
        colocarImagen(imagenGrano);
        
        do {
            try {
                Grano.sleep(1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Grano.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            this.vida--;
            this.terreno.setToolTipText("Cosechando: "+this.nombre+" tiempo restante: "+this.vida);
        } while(this.vida > 0);

        
        if(this.vida == 0){
            this.terreno.cambiarEstado(EstadoGrama.SIEMBRALISTA);
            colocarImagen(imagenListo);
            this.vida=20;
        
        }

        while(this.terreno.getEstado() == EstadoGrama.SIEMBRALISTA && this.vida > 0){

            try{
                Grano.sleep(1000);
            } catch(InterruptedException e){

            }

            this.vida--;
            this.terreno.setToolTipText("La siembra se pudrirá en: "+this.vida);

        }

        if(this.terreno.getEstado() == EstadoGrama.SIEMBRALISTA){
            
            this.terreno.cambiarEstado(EstadoGrama.INFERTIL);
            JOptionPane.showMessageDialog(null, "La siembra se pudrió.", "SurvivalVille",JOptionPane.INFORMATION_MESSAGE);
            colocarImagen(imagenMuerto);
            this.terreno.setToolTipText("");

        } else {

            this.imagenEtiqueta.setVisible(false);
            JOptionPane.showMessageDialog(null, "Siembra obtenida con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            this.terreno.setToolTipText("");

        }
    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
        

    }

}
