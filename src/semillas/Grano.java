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
        this.terreno.cambiarEstado(EstadoGrama.CONSIEMBRA);
        this.imagenEtiqueta.setVisible(true);
        do {
            try {
                Grano.sleep(1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Grano.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            this.vida--;
            this.terreno.setToolTipText("La siembra estará lista en: "+this.vida);
        } while(this.vida > 0);

        
        if(this.vida == 0){
            this.terreno.cambiarEstado(EstadoGrama.SIEMBRALISTA);
            colocarImagen(imagenListo);
        
        }
    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
        

    }


    @Override
    public void elegirTerreno(Grama terreno, JLabel etiqueta) {

        this.terreno = terreno;
        this.imagenEtiqueta = etiqueta;
        colocarImagen(imagenGrano);
        
    }

}
