package juego;

import javax.swing.*;

import animales.*;
import enums.TipoProducto;
import productos.*;
import semillas.*;

import static ventanas.Menu.*;
import static ventanas.Inicio.*;

public class Mercado {

    public static Barco barcoComprado;
    public static final double precioBarco = 1500;

    //método para el menú del mercado
    public static void menuMercado(){
        int opcionMenu;
        do{
            opcionMenu=0;
            try{
                opcionMenu = Integer.parseInt(JOptionPane.showInputDialog(null, "          .:MERCADO:."
                +"\n1. sección de animales."
                +"\n2. sección de Semillas."
                +"\n3. Comprar Barco pesquero."
                +"\n4. Salir.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
                switch(opcionMenu){
                    case 1: 
                    subMenuAnimales();
                    break;
                    case 2: 
                    subMenuSemillas();
                    break;
                    case 3:
                    comprarBarco();
                    break;
                    case 4:
                    JOptionPane.showMessageDialog(null, "Hasta pronto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "No puede realizar dicha acción.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            
        } while(opcionMenu != 4);
    }

    //todo lo relacionado al menú para los animales
    private static void subMenuAnimales(){
        int opcionAnimal;
        do{
            opcionAnimal=0;
            try{
                opcionAnimal = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué desea hacer?"
                +"\n1. Comprar animales Herbívoros."
                +"\n2. Comprar animales Omnívoros."
                +"\n3. Comprar alimento de animales Herbívoros."
                +"\n4. Comprar alimento de animales Omnívoros."
                +"\n5. Volver.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                switch(opcionAnimal){
                    case 1:
                    case 2:
                    comprarAnimales(opcionAnimal);
                    break;
                    case 3:
                    case 4:
                    break;
                    case 5:
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);

            }

        }while(opcionAnimal!=5);
    }


    private static void comprarAnimales(int opcionAnimal){ //crea objetos de animales y se los agrega al jugador dependiendo de su seleccion
        int opcionCompra;
        
        try{
            opcionCompra= Integer.parseInt(JOptionPane.showInputDialog(null, "Animales disponibles:"
            +"\n"+presentarAnimales(opcionAnimal)
            +"Ingrese el dígito del animal que desea comprar:","SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
            if(opcionAnimal==1){
                if(p1.getOro() >= animalesHerbivoros[opcionCompra-1].getPrecio()){
                    Herbivoro animalComprado = new Herbivoro(animalesHerbivoros[opcionCompra-1].getNombre(), animalesHerbivoros[opcionCompra-1].getVida(), animalesHerbivoros[opcionCompra-1].getPrecio(), animalesHerbivoros[opcionCompra-1].getTipo());
                    Mercado.agregarProducto(animalComprado, opcionCompra-1);
                    p1.agregarAnimal(animalComprado);
                    p1.disminuirOro(animalesHerbivoros[opcionCompra-1].getPrecio());
                    animalesHerbivoros[opcionCompra-1].setCantidadAnimal();
                    JOptionPane.showMessageDialog(null, "Animal comprado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No cuentas con el oro suficiente.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } else {
                if(p1.getOro() >= animalesOmnivoros[opcionCompra-1].getPrecio()){

                    Omnivoro animalComprado = new Omnivoro(animalesOmnivoros[opcionCompra-1].getNombre(), animalesOmnivoros[opcionCompra-1].getVida(), animalesOmnivoros[opcionCompra-1].getPrecio(), animalesOmnivoros[opcionCompra-1].getTipo());
                    Mercado.agregarProducto(animalComprado, opcionCompra-1);
                    p1.agregarAnimal(animalComprado);
                    p1.disminuirOro(animalesOmnivoros[opcionCompra-1].getPrecio());
                    animalesOmnivoros[opcionCompra-1].setCantidadAnimal();
                    JOptionPane.showMessageDialog(null, "Animal comprado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No cuentas con el oro suficiente.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
            
        } catch(NumberFormatException e){

            JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException e){

            JOptionPane.showMessageDialog(null, "Este animal no se encuentra en la lista.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }

    private static String presentarAnimales(int opcionAnimal){
        if(opcionAnimal == 1){
            String arregloAnimales="";
            for(int i = 0; i < animalesHerbivoros.length;i++){
                arregloAnimales+=(i+1)+". "+animalesHerbivoros[i].getNombre()+". Precio: "+animalesHerbivoros[i].getPrecio()+". Tiempo de vida: "+animalesHerbivoros[i].getVida()+" Segundos.\n";
            }
            return arregloAnimales;
        } else {

            String arregloAnimales="";
            for(int i = 0; i < animalesOmnivoros.length;i++){
                arregloAnimales+=(i+1)+". "+animalesOmnivoros[i].getNombre()+". Precio: "+animalesOmnivoros[i].getPrecio()+". Tiempo de vida: "+animalesOmnivoros[i].getVida()+" Segundos.\n";
            }
            return arregloAnimales;
        }
    }

    private static void agregarProducto(Animal animalComprado, int indice){ //agrega todos los productos al animal creado desde 0

        if(animalComprado instanceof Herbivoro){

            for(int i = 0; i < animalesHerbivoros[indice].getArregloProductos().length; i++){
                if(animalesHerbivoros[indice].getArregloProductos()[i] instanceof Materia){
                    animalComprado.agregarProducto(new Materia(animalesHerbivoros[indice].getArregloProductos()[i].getNombre(), animalesHerbivoros[indice].getArregloProductos()[i].getPrecio(), animalesHerbivoros[indice].getArregloProductos()[i].getCantidad(), animalesHerbivoros[indice].getArregloProductos()[i].getTipoProducto()));
                } else {
                    animalComprado.agregarProducto(new Alimento(animalesHerbivoros[indice].getArregloProductos()[i].getNombre(), animalesHerbivoros[indice].getArregloProductos()[i].getPrecio(),animalesHerbivoros[indice].getArregloProductos()[i].getCantidad(),((Alimento)animalesHerbivoros[indice].getArregloProductos()[i]).getVida(), animalesHerbivoros[indice].getArregloProductos()[i].getTipoProducto()));
                }
            }
        } else {

            for(int i = 0; i < animalesOmnivoros[indice].getArregloProductos().length; i++){
                if(animalesOmnivoros[indice].getArregloProductos()[i] instanceof Materia){
                    animalComprado.agregarProducto(new Materia(animalesOmnivoros[indice].getArregloProductos()[i].getNombre(), animalesOmnivoros[indice].getArregloProductos()[i].getPrecio(), animalesOmnivoros[indice].getArregloProductos()[i].getCantidad(), animalesOmnivoros[indice].getArregloProductos()[i].getTipoProducto()));
                } else {
                    animalComprado.agregarProducto(new Alimento(animalesOmnivoros[indice].getArregloProductos()[i].getNombre(), animalesOmnivoros[indice].getArregloProductos()[i].getPrecio(),animalesOmnivoros[indice].getArregloProductos()[i].getCantidad(),((Alimento)animalesOmnivoros[indice].getArregloProductos()[i]).getVida(), animalesOmnivoros[indice].getArregloProductos()[i].getTipoProducto()));
                }
            }
        }
    }

    //todo lo relacionado a las semillas
    public static void subMenuSemillas(){
        int opcionSemilla;
        do{
            opcionSemilla=0;
            try{
                opcionSemilla = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué desea hacer?"
                +"\n1. Comprar semillas de granos."
                +"\n2. Comprar semillas de frutos."
                +"\n3. Comprar fertilizante."
                +"\n4. Volver.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                switch(opcionSemilla){
                    case 1:
                    case 2:
                    comprarSemillas(opcionSemilla);
                    break;
                    case 3:
                    case 4:
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);

            }

        }while(opcionSemilla!=4);
    }

    private static void comprarSemillas(int opcionSemilla){ //crea objetos de Planta que son agregados al jugador
        int opcionCompra;
        
        try{
            opcionCompra= Integer.parseInt(JOptionPane.showInputDialog(null, "Bolsas de semillas disponibles:"
            +"\n"+presentarSemillas(opcionSemilla)
            +"Ingrese el dígito de la semilla que desea comprar:","SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
            if(opcionSemilla==1){ 
                if(p1.getOro() >= granos[opcionCompra-1].getPrecio()){ //se verifica si posee el dinero suficiente para la compra
                    Grano granoCompra = new Grano(granos[opcionCompra-1].getNombre(), granos[opcionCompra-1].getVida(), granos[opcionCompra-1].getPrecio());
                    Alimento granoProducto = new Alimento(granos[opcionCompra-1].getProducto().getNombre(), granos[opcionCompra-1].getProducto().getPrecio(), granos[opcionCompra-1].getProducto().getCantidad(),((Alimento)granos[opcionCompra-1].getProducto()).getVida(), TipoProducto.GRANO);
                    granoCompra.agregarProducto(granoProducto); //se le agrega el producto de la planta selleccionada
                    p1.agregarPlanta(granoCompra);
                    p1.disminuirOro(granoCompra.getPrecio());
                    granos[opcionCompra-1].setCantidadAdquirida();
                    JOptionPane.showMessageDialog(null, "Bolsa de semillas comprada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No cuentas con el oro suficiente.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                if(p1.getOro() >= frutos[opcionCompra-1].getPrecio()){ //se verifica si posee el dinero suficiente
                    Fruto frutoCompra = new Fruto(frutos[opcionCompra-1].getNombre(), frutos[opcionCompra-1].getVida(), frutos[opcionCompra-1].getPrecio());
                    Alimento frutoProducto = new Alimento(frutos[opcionCompra-1].getProducto().getNombre(), frutos[opcionCompra-1].getProducto().getPrecio(), frutos[opcionCompra-1].getProducto().getCantidad(),((Alimento)frutos[opcionCompra-1].getProducto()).getVida(), TipoProducto.FRUTO);
                    frutoCompra.agregarProducto(frutoProducto);
                    p1.agregarPlanta(frutoCompra);
                    p1.disminuirOro(frutoCompra.getPrecio());
                    frutos[opcionCompra-1].setCantidadAdquirida();
                    JOptionPane.showMessageDialog(null, "Bolsa de semillas comprada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No cuentas con el oro suficiente.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Esta bolsa de semillas no se encuentra en la lista.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String presentarSemillas(int opcionSemilla){

        if(opcionSemilla == 1){
            String arregloSemillas="";
            for(int i = 0; i < granos.length;i++){
                arregloSemillas+=(i+1)+". "+granos[i].getNombre()+". Precio: "+granos[i].getPrecio()+". Tiempo de vida: "+granos[i].getVida()+" Segundos.\n";
            }
            return arregloSemillas;
        } else {

            String arregloSemillas="";
            for(int i = 0; i < frutos.length;i++){
                arregloSemillas+=(i+1)+". "+frutos[i].getNombre()+". Precio: "+frutos[i].getPrecio()+". Tiempo de vida: "+frutos[i].getVida()+" Segundos.\n";
            }
            return arregloSemillas;
        }
    }

    //menú compra barco
    private static void comprarBarco(){ //crea objetos  de barco y se los agrega al jugador
        int opcionBarco;
        do{
            opcionBarco=0;
            if(p1.getOro()>= precioBarco){
                try{
                    opcionBarco= Integer.parseInt(JOptionPane.showInputDialog(null, ".:COMPRAR BARCO PESQUERO:."
                    +"\n¿Desea comprar un barco pesquero? Costo: "+precioBarco
                    +"\n1. Sí."
                    +"\n2. No.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
        
                    switch(opcionBarco){
                        case 1:
                        barcoComprado = new Barco();
                        p1.agregarBarco(barcoComprado);
                        p1.disminuirOro(precioBarco);
                        JOptionPane.showMessageDialog(null, "Barco comprado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        break;
                        case 2:
                        break;
                        default:
                        JOptionPane.showMessageDialog(null, "Opcion incorrecta.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Dato incorrecto.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No posees el oro suficiente para comprar un barco nuevo.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                opcionBarco=2;
            }

        } while(opcionBarco != 2);
    }
    
}
