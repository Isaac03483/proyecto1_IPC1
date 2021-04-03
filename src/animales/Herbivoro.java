package animales;

import javax.swing.JOptionPane;

import enums.TipoProducto;
import productos.*;

public class Herbivoro extends Animal{

    public Herbivoro(String nombre, int vida, double precio,TipoProducto tipo){
        super(nombre, vida, precio, tipo);
        
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){

        do{
            this.vida--;

            try{
                Thread.sleep(1000);

            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Herbivoro: "+e, "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

        }while(this.vida >10);

        
    }

    @Override
    public void comer(){

    }
    
}
