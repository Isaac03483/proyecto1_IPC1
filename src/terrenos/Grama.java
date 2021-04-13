package terrenos;

import javax.swing.*;

import enums.EstadoGrama;
import semillas.Planta;

import java.awt.*;

public class Grama extends Terreno{

    private EstadoGrama estado;
    private Planta semilla;
    static ImageIcon imagen2 = new ImageIcon("pasto.png");

    public Grama(){
       estado = EstadoGrama.DISPONIBLE;
    }

    @Override
    public void generarTerreno(){//agrega la imagen a la etiqueta

        this.setIcon(new ImageIcon(imagen2.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }

    //getters y setters
    public EstadoGrama getEstado(){return this.estado;}

    public void cambiarEstado(EstadoGrama estado){this.estado = estado;}

    public void setPlanta(Planta semilla){this.semilla = semilla;}

    public Planta getPlanta(){return this.semilla;}
     
    
}
