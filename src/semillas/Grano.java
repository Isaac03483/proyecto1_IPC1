package semillas;

import productos.Alimento;

public class Grano extends Planta{
    
    public Grano(String nombre, int vida, double precio){
        super(nombre, vida, precio);

    }

    @Override
    public String toString(){return this.nombre;}

    @Override
    public void run(){
        
    }

}
