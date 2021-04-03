package terrenos;

import javax.swing.*;

import enums.EstadoGrama;


import java.awt.*;

public class Grama extends Terreno{

    private EstadoGrama estado;
    static ImageIcon imagen2 = new ImageIcon("pasto.png");
    static ImageIcon imagenFruto = new ImageIcon("manzana.png");
    static ImageIcon imagenGrano = new ImageIcon("maiz.png");

    public Grama(){
       estado = EstadoGrama.DISPONIBLE;
    }

    @Override
    public void generarTerreno(){

        this.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }

    public EstadoGrama getEstado(){return this.estado;}
     
    
}
