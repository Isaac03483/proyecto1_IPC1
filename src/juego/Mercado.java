package juego;

import javax.swing.*;

import animales.*;
import semillas.*;

import static ventanas.Menu.*;
import static ventanas.Inicio.*;

public class Mercado {

    public static Barco barcoComprado;
    public static final double precioBarco = 500;

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


    private static void comprarAnimales(int opcionAnimal){
        int opcionCompra;
        
        try{
            opcionCompra= Integer.parseInt(JOptionPane.showInputDialog(null, "Animales disponibles:"
            +"\n"+presentarAnimales(opcionAnimal)
            +"Ingrese el dígito del animal que desea comprar:","SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
            if(opcionAnimal==1){
                p1.agregarAnimal(new Herbivoro(animalesHerbivoros[opcionCompra-1].getNombre(), animalesHerbivoros[opcionCompra-1].getVida(), animalesHerbivoros[opcionCompra-1].getPrecio(), animalesHerbivoros[opcionCompra-1].getTipo()));
                p1.disminuirOro(animalesHerbivoros[opcionCompra-1].getPrecio());
                animalesHerbivoros[opcionCompra-1].setCantidadAnimal();
            } else {
                p1.agregarAnimal(new Omnivoro(animalesOmnivoros[opcionCompra-1].getNombre(), animalesOmnivoros[opcionCompra-1].getVida(), animalesOmnivoros[opcionCompra-1].getPrecio(), animalesOmnivoros[opcionCompra-1].getTipo()));
                p1.disminuirOro(animalesOmnivoros[opcionCompra-1].getPrecio());
                animalesOmnivoros[opcionCompra-1].setCantidadAnimal();
            }
            JOptionPane.showMessageDialog(null, "Animal comprado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
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

    //todo lo relacionado a las semillas
    public static void subMenuSemillas(){
        int opcionSemilla;
        do{
            opcionSemilla=0;
            try{
                opcionSemilla = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Qué desea hacer?"
                +"\n1. Comprar semillas de granos."
                +"\n2. Comprar semillas de frutos."
                +"\n3. Comprar alimento de animales Herbívoros."
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

    private static void comprarSemillas(int opcionSemilla){
        int opcionCompra;
        
        try{
            opcionCompra= Integer.parseInt(JOptionPane.showInputDialog(null, "Bolsas de semillas disponibles:"
            +"\n"+presentarSemillas(opcionSemilla)
            +"Ingrese el dígito de la semilla que desea comprar:","SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
            if(opcionSemilla==1){
                p1.agregarPlanta(new Grano(granos[opcionCompra-1].getNombre(), granos[opcionCompra-1].getVida(), granos[opcionCompra-1].getPrecio()));
                p1.disminuirOro(granos[opcionCompra-1].getPrecio());
                granos[opcionCompra-1].setCantidadAdquirida();
            } else {
                p1.agregarPlanta(new Fruto(frutos[opcionCompra-1].getNombre(), frutos[opcionCompra-1].getVida(), frutos[opcionCompra-1].getPrecio()));
                p1.disminuirOro(frutos[opcionCompra-1].getPrecio());
                frutos[opcionCompra-1].setCantidadAdquirida();
            }
            JOptionPane.showMessageDialog(null, "Bolsa de semillas comprada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
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
    private static void comprarBarco(){
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
