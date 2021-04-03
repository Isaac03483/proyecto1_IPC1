package semillas;

import javax.swing.*;
import java.awt.*;
import terrenos.Grama;

public class Fruto extends Planta{

    static ImageIcon imagenFruto = new ImageIcon("manzana.png");
    static ImageIcon imagenListo = new ImageIcon("manzanaLista.png");

    public Fruto(String nombre, int vida, double precio){
        super(nombre, vida, precio);
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){
        
    }

    @Override
    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));

    }


    @Override
    public void elegirTerreno(Grama terreno, JLabel etiqueta) {

        this.terreno = terreno;
        this.imagenEtiqueta = etiqueta;
        colocarImagen(imagenFruto);
        
    }
    
}
