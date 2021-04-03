package genericos;

import semillas.*;

public class Nodo {

    private Planta informacion;
    private Nodo siguiente;
    

    public void setInformacion(Planta informacion){this.informacion = informacion;}

    public Planta getInformacion(){return this.informacion;}

    public Nodo setSiguiente(){return this.siguiente;}

    public void getSiguiente(Nodo siguiente){this.siguiente = siguiente;}
}
