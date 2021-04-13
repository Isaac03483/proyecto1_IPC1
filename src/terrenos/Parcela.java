package terrenos;

import java.awt.Image;

import javax.swing.ImageIcon;

import animales.Animal;
import enums.EstadoParcela;

public class Parcela extends Terreno{
    
    private ImageIcon imagenParcela = new ImageIcon("parcela.png");
    private EstadoParcela estado;
    private Animal animal;

    //costructor
    public Parcela(){
        this.estado = EstadoParcela.DISPONIBLE;
    }

    //getters y setters
    public void cambiarEstado(EstadoParcela estado){this.estado = estado;}

    public void setAnimal(Animal animal){this.animal = animal;}

    public Animal getAnimal(){return this.animal;}

    public EstadoParcela getEstado(){return this.estado;}
    
    @Override
    public void generarTerreno(){ //agrega la imagen a la etiqueta
        this.setIcon(new ImageIcon(imagenParcela.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }
}
