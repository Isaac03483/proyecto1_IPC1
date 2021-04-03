package ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import personajes.*;
import animales.*;
import enums.TipoProducto;
import productos.*;
import semillas.*;

public class Menu extends JFrame{

    private static JPanel panel;
    private static JButton botonInicio, botonCrear, botonReporte, botonSalir;
    private static JLabel menu;
    public static Menu m1;
    public static Producto[] productosJuego = {new Alimento("Carne", 75, 75, 25, TipoProducto.ALDESTAZAR), new Alimento("Huevos", 25, 100, 10, TipoProducto.SINDESTAZAR), new Materia("Cuero", 75,25, TipoProducto.ALDESTAZAR), new Alimento("Leche", 60, 100, 5, TipoProducto.SINDESTAZAR), new Alimento("Grano", 30, 10, 10, TipoProducto.SINDESTAZAR), new Alimento("Manzana", 5, 10, 5, TipoProducto.SINDESTAZAR)};
    public static Animal[] animalesHerbivoros ={new Herbivoro("Vaca", 125, 100, TipoProducto.AMBOS)};
    public static Animal[] animalesOmnivoros ={new Omnivoro("Gallina", 75, 50, TipoProducto.AMBOS)};
    public static Planta[] granos = {new Grano("Maíz", 30, 5)};
    public static Planta[] frutos = {new Fruto("Manzano", 90, 10)};
    public static Jugador[] jugadores= new Jugador[0];
    public static void main(String[] args){

        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                m1 = new Menu();
                m1.setVisible(true);

                //asignamos todos los productos a los respectivos animales y plantas
                animalesHerbivoros[0].agregarProducto(productosJuego[0]);
                animalesHerbivoros[0].agregarProducto(productosJuego[2]);
                animalesHerbivoros[0].agregarProducto(productosJuego[3]);
                animalesOmnivoros[0].agregarProducto(productosJuego[0]);
                animalesOmnivoros[0].agregarProducto(productosJuego[1]);
                granos[0].agregarProducto(productosJuego[4]);
                frutos[0].agregarProducto(productosJuego[5]);
            }
        });
    }

    public Menu(){

        this.setSize(300,300);
        this.setTitle("SurvivalVille");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        colocarPanel();
        colocarBotones();
        colocarEtiqueta();
    }

    private void colocarEtiqueta(){
        menu = new JLabel("Menú Principal");
        menu.setBounds(90,30,140,30);
        panel.add(menu);
        menu.setForeground(Color.BLACK);
        menu.setFont(new Font("Basic", Font.BOLD, 14));
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));
    }

    private void colocarBotones(){

        botonInicio = new JButton("Jugar");
        botonInicio.setBounds(100,70, 100,30);
        panel.add(botonInicio);

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(100,110, 100,30);
        panel.add(botonCrear);

        botonReporte = new JButton("Reportes");
        botonReporte.setBounds(100,150, 100,30);
        panel.add(botonReporte);
        
        botonSalir = new JButton("Salir");
        botonSalir.setBounds(100,190,100,30);
        panel.add(botonSalir);

        

        oyenteInicio();
        oyenteCrear();
        oyenteReporte();
        oyenteSalir();

    }

    private void oyenteInicio(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                Inicio i1 = new Inicio();
                i1.setVisible(true);
                setVisible(false);
            }
        };

        botonInicio.addActionListener(oyenteAccion);
    }

    private void oyenteReporte(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                Reporte r1 = new Reporte();
                r1.setVisible(true);
                setVisible(false);
            }
        };

        botonReporte.addActionListener(oyenteAccion);
    }
    private void oyenteSalir(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        };

        botonSalir.addActionListener(oyenteAccion);
    }

    private void oyenteCrear(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                menuCrear();
            }
        };
        botonCrear.addActionListener(oyenteAccion);
    }

    private void menuCrear(){
        int opcionCrear;

        do{
            opcionCrear =0;
            try{

                opcionCrear = Integer.parseInt(JOptionPane.showInputDialog(null, "         .:MENÚ CREAR:."
                +"\n1. Crear nuevo Animal."
                +"\n2. Crear nueva Planta."
                +"\n3. Crear nuevo Producto."
                +"\n4. Volver.","SurvivalVille",JOptionPane.INFORMATION_MESSAGE));
                switch(opcionCrear){
                    case 1:
                    CrearAnimal c1 = new CrearAnimal();
                    c1.setVisible(true);
                    setVisible(false);
                    break;
                    case 2:
                    CrearSemilla c2 = new CrearSemilla();
                    c2.setVisible(true);
                    setVisible(false);
                    break;
                    case 3:
                    CrearProducto c3 = new CrearProducto();
                    c3.setVisible(true);
                    setVisible(false);
                    break;
                    case 4:
                    break;
                    default:
                    break;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Opción incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
        } while(opcionCrear>4 || opcionCrear < 1);
    }

    public static Animal[] redimensionarAnimales(Animal[] arreglo, Animal nuevoValor){

        Animal[] nArreglo = new Animal[arreglo.length +1];

        for(int i = 0; i < arreglo.length; i++){
            nArreglo[i] = arreglo[i];
        }

        nArreglo[arreglo.length] = nuevoValor;

        return nArreglo;
        
    }
    
    public static Jugador[] redimensionarJugadores(Jugador[] arreglo, Jugador nuevoValor){

        Jugador[] nArreglo = new Jugador[arreglo.length +1];

        for(int i = 0; i < arreglo.length; i++){
            nArreglo[i] = arreglo[i];
        }

        nArreglo[arreglo.length] = nuevoValor;

        return nArreglo;
        
    }

    public static Producto[] redimensionarProductos(Producto[] arreglo, Producto nuevoValor){

        Producto[] nArreglo = new Producto[arreglo.length +1];

        for(int i = 0; i < arreglo.length; i++){
            nArreglo[i] = arreglo[i];
        }

        nArreglo[arreglo.length] = nuevoValor;

        return nArreglo;
    }

    public static Planta[] redimensionarPlantas(Planta[] arreglo, Planta nuevoValor){

        Planta[] nArreglo = new Planta[arreglo.length +1];

        for(int i = 0; i < arreglo.length; i++){
            nArreglo[i] = arreglo[i];
        }

        nArreglo[arreglo.length] = nuevoValor;

        return nArreglo;
        
    }
}
