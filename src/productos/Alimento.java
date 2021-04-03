package productos;

import enums.TipoProducto;

public class Alimento extends Producto{

    private int vidaRecuperada;

    public Alimento(String nombre, double precio, int cantidad, int vidaRecuperada, TipoProducto tipo){
        super(nombre, precio, cantidad, tipo);
        this.vidaRecuperada = vidaRecuperada;
    }

    public int getVida(){return this.vidaRecuperada;} 

    
}
