package personajes;

import javax.swing.*;
import animales.*;
import enums.EstadoBarco;
import productos.Alimento;
import productos.Producto;
import semillas.*;
import terrenos.*;
import juego.Barco;
import static ventanas.Juego.*;
import static ventanas.Menu.*;
import static ventanas.Inicio.*;


public class Jugador extends Thread{

    private String nombre;
    private String nickName;
    private double oro;
    private int vida;
    private Animal[] animales;
    private Planta[] semillas;
    private Producto[] productos;
    private Barco[] barco;
    private int tiempoDurado;
    private double oroGenerado;
    private int alimentoGenerado;
    private int alimentoConsumido;
    private int celdasCompradas;

    public Jugador(String nombre, String nickName,double oro, int vida){
        this.nombre= nombre;
        this.nickName = nickName;
        this.oro = oro;
        this.vida = vida;
        this.tiempoDurado=0;
        this.oroGenerado=0;
        this.celdasCompradas=0;
        this.alimentoConsumido=0;
        this.alimentoGenerado=0;
        this.animales = new Animal[0];
        this.semillas = new Planta[0];
        this.productos = new Producto[0];
        this.barco= new Barco[0];

    }

    @Override

    public void run(){
        do {

            try{
                Jugador.sleep(1000);

            }catch(InterruptedException e){

                JOptionPane.showMessageDialog(null, "Error en la clase Jugador: "+e, "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }

            this.vida--;
            this.tiempoDurado++;
            etiquetaVida.setText(Integer.toString(this.vida));
        } while(this.vida >0);

        if(this.vida == 0){
            JOptionPane.showMessageDialog(null, "Has perdido :'c", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            jugadores = redimensionarJugadores(jugadores, this);
            v1.dispose();
        }
        
        m1.setVisible(true);
    }

    //métodos setters y getters
    public void aumentarOro(double oro){
        this.oro +=oro;
        this.oroGenerado+=oro;
        etiquetaOro.setText(Double.toString(this.oro));
    }

    public void disminuirOro(double oro){
        this.oro -=oro;
        etiquetaOro.setText(Double.toString(this.oro));
    }

    public void comer(int opcionAlimento, int opcionCantidad){
        this.vida += ((Alimento)productos[opcionAlimento-1]).getVida()*opcionCantidad;
        ((Alimento)productos[opcionAlimento-1]).restarCantidad(opcionCantidad);
        etiquetaVida.setText(Integer.toString(this.vida));
        this.alimentoConsumido+=opcionCantidad;
        JOptionPane.showMessageDialog(null, "Has recuperado vida.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        verificarProductoTerminado((opcionAlimento-1));
    }

    public void vender(int opcionProducto, int opcionCantidad){
        this.oro+= productos[opcionProducto-1].getPrecio()*opcionCantidad;
        this.oroGenerado+=oro;
        productos[opcionProducto-1].restarCantidad(opcionCantidad);
        etiquetaOro.setText(Double.toString(this.oro));
        JOptionPane.showMessageDialog(null, "Venta realizada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        verificarProductoTerminado((opcionProducto-1));
        
    }

    public void setCeldas(){this.celdasCompradas++;}

    public void setVida(int vida){this.vida =vida;}

    public String getNickName(){return this.nickName;}

    public double getOro(){return this.oro;}
    
    public int getVida(){return this.vida;}

    public int getCeldasCompradas(){return this.celdasCompradas;}

    public String getNombre(){return this.nombre;}

    public int getAlimentoConsumido(){return this.alimentoConsumido;}

    public int getAlimentoGenerado(){return this.alimentoGenerado;}

    public int getTiempoDurado(){return this.tiempoDurado;}

    public double getOroGenerado(){return this.oroGenerado;}

    public Animal[] getArregloAnimales(){return this.animales;}

    public String getInformacionAnimal(int indice){
        return "Nombre: "+animales[indice].getNombre()+" Precio: "+animales[indice].getPrecio()+" Vida: "+animales[indice].getVida()+".\n";
    }

    public Producto[] getArregloProductos(){return this.productos;}

    public String getInformacionProducto(int indice){
        return "Nombre: "+productos[indice].getNombre()+" Precio de venta: "+productos[indice].getPrecio()+" Cantidad: "+productos[indice].getCantidad()+".\n";
    }

    public Barco[] getArregloBarco(){return this.barco;}

    public String getInformacionBarco(int indice){
        return "Barco no: "+(indice +1)+" Peces Obtenidos: "+barco[indice].getNumPeces()+" Estado: "+barco[indice].getEstado()+".\n";
    }

    public Planta[] getArregloPlantas(){return this.semillas;}

    public String getInformacionPlantas(int indice){
        return "Nombre: "+ semillas[indice].getNombre()+" Vida: "+semillas[indice].getVida()+".\n";
    }


    @Override
    public String toString(){return this.nickName;}

    public void agregarAnimal(Animal nuevoAnimal){

        animales = redimensionarAnimales(animales, nuevoAnimal);

    }



    public void agregarPlanta(Planta nuevaPlanta){

        semillas = redimensionarPlantas(semillas, nuevaPlanta);

    }

    public void agregarProducto(Producto nuevoProducto){
        
        int indice=0;
        boolean encontrado=false;
        for(int i = 0; i < this.productos.length; i++){
            if(productos[i].getNombre() == nuevoProducto.getNombre()){
                encontrado = true;
                indice = i;
                break;
            }
        } 

        if(encontrado == true){
            this.productos[indice].aumentarCantidad(nuevoProducto.getCantidad());
        } else if (encontrado == false){
            this.productos = redimensionarProductos(productos, nuevoProducto);
        }

        if(nuevoProducto instanceof Alimento){
            this.alimentoGenerado+= nuevoProducto.getCantidad();
        }
    }

    public void agregarBarco(Barco nuevoBarco){

        Barco[] nArrego = new Barco[barco.length+1];
        for(int i = 0; i < barco.length; i++){
            nArrego[i] = barco[i];
        }

        nArrego[barco.length] = nuevoBarco;

        barco = nArrego;
    }

    public void colocarBarco(Lago lago, JLabel imagenBarco){
        
        verificarBarco(lago, imagenBarco);
    }

    public void verificarBarco(Lago lago, JLabel imagenEtiqueta){
        int indice = -2;
        for(int i = 0; i  < barco.length; i++){
            if(barco[i].getEstado() == EstadoBarco.DISPONIBLE){
                indice = i;
                break;
            } else {
                indice = -1;
            }
        }
        if(indice == -2){ //este caso es especial se da en el caso en el que el jugador ni siquiera tenga un barco en posesión
            JOptionPane.showMessageDialog(null, "No posees ningún barco, compra uno en el mercado.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            
        } else if(indice == -1){ //este caso es por si el usuario posea un barco pero este  esté pescando
            JOptionPane.showMessageDialog(null, "No posees ningún barco disponible en este momento.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            
            JOptionPane.showMessageDialog(null, "Barco pesquero colocado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            barco[indice].elegirLago(lago, imagenEtiqueta);
            lago.setIndiceBarco(indice);
            Thread pescar = new Thread(barco[indice]);
            pescar.start();
            
        }
    }

    public void verificarProductoTerminado(int indice){

        if(productos[indice].getCantidad()==0){
            Producto[] nArreglo = new Producto[productos.length-1];
            for(int i = indice; i < productos.length-1; i++){
                productos[i]=productos[i+1];
            }
            for(int i = 0; i < nArreglo.length;i ++){
                nArreglo[i] = productos[i];
            }

            productos=nArreglo;
        }
    }

    public void colocarSemilla(Grama terreno, JLabel objetos, int opcionSemilla){

        try {
            
            terreno.setPlanta(semillas[opcionSemilla-1]);
            semillas[opcionSemilla-1].elegirTerreno(terreno, objetos);
            Thread sembrar = new Thread(semillas[opcionSemilla-1]);
            sembrar.start();
            eliminarSemilla(opcionSemilla-1);
        } catch(ArrayIndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Esta opción no se encuentra en el arreglo.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarSemilla(int indice){

        Planta[] nArreglo = new Planta[semillas.length -1];

        for(int i = indice; i < semillas.length-1; i ++){
            semillas[i] = semillas[i+1];
        }

        for(int i = 0; i < nArreglo.length; i++){
            nArreglo[i] = semillas[i];
        }

        semillas = nArreglo;
    }
        
    
}
