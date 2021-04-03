package juego;

import static ventanas.Inicio.p1;

import javax.swing.JOptionPane;

import productos.Alimento;

public class Bodega {
    
    public static int opcionBodega;

    public static void bodega(){

        do {

            try{
                opcionBodega= Integer.parseInt(JOptionPane.showInputDialog(null, "          .:BODEGA:."
                +"\n1. Ver sus animales."
                +"\n2. ver sus semillas."
                +"\n3. ver sus barcos."
                +"\n4. Vender productos."
                +"\n5. Comer."
                +"\n6. Salir.","SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionBodega){
                    case 1:
                    subMenuAnimales();
                    break;
                    case 2:
                    subMenuSemillas();
                    break;
                    case 3:
                    subMenuBarco();
                    break;
                    case 4:
                    subMenuProductos();
                    break;
                    case 5:
                    subMenuComer();
                    break;
                    case 6:
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }catch(NumberFormatException e){

                JOptionPane.showMessageDialog(null, "Solo puede ingresar datos numéricos.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
        } while(opcionBodega!= 6);
    }

    //menú 1 para ver los animales que posee el usuario
    public static void subMenuAnimales(){

        JOptionPane.showMessageDialog(null, "Animales comprados: "
        +"\n"+presentarAnimales(), "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String presentarAnimales(){ //método que nos muestra la información de los animales que posee
        String presentar="";
        for(int i=0; i < p1.getArregloAnimales().length; i++){ //creamos un ciclo en el que almacenamos en una variable toda la información de los animales del usuario
            presentar += (i+1)+". "+p1.getInformacionAnimal(i);
        }

        return presentar;
    }


    //menú 2 que nos mostrará todas las semillas que posee el usuario
    public static void subMenuSemillas(){ 

        JOptionPane.showMessageDialog(null, "Semillas compradas: "
        +"\n"+presentarSemillas(), "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String presentarSemillas(){
        String presentar="";
        for(int i = 0; i < p1.getArregloPlantas().length; i++){
            presentar+= (i+1)+". "+p1.getInformacionPlantas(i);
        }
        return presentar;
    }

    //menu 3 para ver los barcos del usuario
    public static void subMenuBarco(){
        JOptionPane.showMessageDialog(null, "Información de Barcos: "
        +"\n"+presentarBarco(), "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String presentarBarco(){
        String presentar="";
        for(int i=0; i < p1.getArregloBarco().length;i++){
            presentar+=p1.getInformacionBarco(i);
        }

        return presentar;
    }

    public static void subMenuProductos(){ //sub menú que nos permite poder vender el producto que querramos
        int opcionProducto=0;
        do{
            try {
                opcionProducto= Integer.parseInt(JOptionPane.showInputDialog(null, "Productos obtenidos: "
                +"\n0. Volver."
                +"\n"+presentarProductos()
                +"\nIngrese el número del producto que desea vender:", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionProducto){
                    case 0:
                    break;
                    default:
                    elegirCantidad(opcionProducto);
                    break;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            } catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "Dicho valor no exíste", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            
        } while(opcionProducto!=0);

    }


    public static String presentarProductos(){

        String presentar="";
        for(int i=0; i < p1.getArregloProductos().length;i++){
            if(opcionBodega ==4){
                if(p1.getArregloProductos()[i] instanceof Alimento){
                    presentar+=(i+1)+". "+p1.getInformacionProducto(i);
                }
            } else {
                presentar+=(i+1)+". "+p1.getInformacionProducto(i);
            }
        }

        return presentar;
    }

    public static void subMenuComer(){
        int opcionAlimento=0;
        
        do {

            try{
                opcionAlimento= Integer.parseInt(JOptionPane.showInputDialog(null, "Alimentos obtenidos: "
                +"\n0. Volver."
                +"\n"+presentarProductos(), "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionAlimento){
                    case 0:
                    break;
                    default:
                    if(p1.getArregloProductos()[opcionAlimento-1] instanceof Alimento){ //si la opción elegida es un alimento entonces
                        elegirCantidad(opcionAlimento);
                    } else { //de no serlo entonces muestra mensaje de error
                        JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }

            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);

            }catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "Dicho valor no existe.", "SUrvivalVille", JOptionPane.ERROR_MESSAGE);
            }
        } while(opcionAlimento!=0);
    }

    public static void elegirCantidad(int opcionProducto){
        int opcionCantidad=0;
        try{
            opcionCantidad=Integer.parseInt(JOptionPane.showInputDialog(null, "Producto seleccionado:"
            +"\n"+p1.getInformacionProducto(opcionProducto-1)
            +"\nIngrese la cantidad de productos que desea.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

            if(opcionBodega==4){
                if(opcionCantidad > p1.getArregloProductos()[opcionProducto-1].getCantidad()){
                    JOptionPane.showMessageDialog(null, "No puedes vender una cantidad de productos que no posees.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                } else {
                    p1.vender(opcionProducto, opcionCantidad);
                }
            } else if(opcionBodega==5){
                if(opcionCantidad > p1.getArregloProductos()[opcionProducto-1].getCantidad()){
                    JOptionPane.showMessageDialog(null, "No puedes consumir tal cantidad de alimentos.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                } else {
                    p1.comer(opcionProducto, opcionCantidad);
                }
            }
        } catch(NumberFormatException e){

        }
    }
}
