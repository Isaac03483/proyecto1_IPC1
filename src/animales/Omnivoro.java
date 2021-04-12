package animales;

import java.awt.Image;

import javax.swing.*;

import enums.*;

public class Omnivoro extends Animal{

    private ImageIcon imagenOmnivoro = new ImageIcon("gallina.png");
    private ImageIcon imagenListo = new ImageIcon("gallinaLista.png");
    private ImageIcon imagenMuerto = new  ImageIcon("gallinaMuerta.png");

    public Omnivoro(String nombre, int vida, double precio,TipoProducto tipo){

        super(nombre, vida, precio, tipo);
        
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){

        colocarImagen(imagenOmnivoro);
        this.imagenEtiqueta.setVisible(true);
        this.terreno.cambiarEstado(EstadoParcela.CONANIMAL);
        do{

            

            try{

                Omnivoro.sleep(1000);

            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error clase Omnivoro: "+e, "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            

            this.vida--;
            this.terreno.setToolTipText("Animal criado: "+this.nombre+" tiempo de vida restante: "+this.vida);

        }while(this.vida > 0);

        this.terreno.cambiarEstado(EstadoParcela.ANIMALPREPARADO);
        colocarImagen(imagenListo);
        this.vida = 15;

        do{

            try{
                Omnivoro.sleep(1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en hilo omnivoro.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

            this.vida--;
            this.terreno.setToolTipText("Animal criado: "+this.nombre+" morirá en: "+this.vida);

        }while(this.terreno.getEstado() == EstadoParcela.ANIMALPREPARADO && this.vida > 0);

        if(this.terreno.getEstado() == EstadoParcela.ANIMALPREPARADO){
            colocarImagen(imagenMuerto);
            this.terreno.setToolTipText("");
            this.terreno.cambiarEstado(EstadoParcela.ANIMALMUERTO);
            JOptionPane.showMessageDialog(null, "El animal ha muerto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else {

            this.imagenEtiqueta.setVisible(false);
            this.terreno.setToolTipText("");
        }
    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
    }

    
}
