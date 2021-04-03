package productos;

import enums.TipoProducto;

public class Materia extends Producto{

    public Materia(String nombre, double precio, int cantidad, TipoProducto tipo){
        super(nombre, precio, cantidad, tipo);
    }
    
}
