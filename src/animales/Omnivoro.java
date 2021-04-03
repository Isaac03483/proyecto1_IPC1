package animales;

import javax.swing.JOptionPane;

import enums.*;
import productos.*;

public class Omnivoro extends Animal{

    public Omnivoro(String nombre, int vida, double precio,TipoProducto tipo){

        super(nombre, vida, precio, tipo);
        
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){

        do{

            this.vida--;

            try{

                Thread.sleep(2000);

            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error clase Omnivoro: "+e, "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            

        }while(this.vida > 5);
    }

    @Override
    public void comer() {
        
        
    }
    
}
