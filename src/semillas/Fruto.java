package semillas;

import productos.Alimento;

public class Fruto extends Planta{

    public Fruto(String nombre, int vida, double precio){
        super(nombre, vida, precio);
    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){
        
    }
    
}
