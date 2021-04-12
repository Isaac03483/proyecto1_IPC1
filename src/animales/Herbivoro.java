package animales;

import java.awt.Image;

import javax.swing.*;

import enums.EstadoParcela;
import enums.TipoProducto;

public class Herbivoro extends Animal{

    private ImageIcon imagenVaca = new ImageIcon("vaca.png");
    private ImageIcon imagenLista = new ImageIcon("vacaLista.png");
    private ImageIcon imagenMuerta = new ImageIcon("vacaMuerta.png");

    public Herbivoro(String nombre, int vida, double precio,TipoProducto tipo){
        super(nombre, vida, precio, tipo);
        
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){

        colocarImagen(imagenVaca);
        this.terreno.cambiarEstado(EstadoParcela.CONANIMAL);
        this.imagenEtiqueta.setVisible(true);
        do{
            
            try{
                Herbivoro.sleep(1000);

            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Herbivoro: "+e, "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

            this.vida--;
            this.terreno.setToolTipText("Animal criado: "+this.nombre+" tiempo de vida restante: "+this.vida);

        }while(this.vida >0);

        this.terreno.cambiarEstado(EstadoParcela.ANIMALPREPARADO);
        colocarImagen(imagenLista);
        this.vida = 20;

        do{

            try{
                Herbivoro.sleep(1000);

            } catch(InterruptedException e){

            }

            this.vida--;
            this.terreno.setToolTipText("Animal criado: "+this.nombre+" morirá en: "+this.vida);

        } while(this.terreno.getEstado() == EstadoParcela.ANIMALPREPARADO && this.vida > 0);

        if(this.terreno.getEstado() == EstadoParcela.ANIMALPREPARADO){
            this.terreno.cambiarEstado(EstadoParcela.ANIMALMUERTO);
            this.terreno.setToolTipText("");
            colocarImagen(imagenMuerta);
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
