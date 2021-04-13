package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import static ventanas.Menu.*;


public class Reporte extends JFrame{
    

    public static JPanel panel;
    public static JRadioButton reporteJugador, reporteAnimal, reportePlanta;
    public static JButton botonVolver;
    public static JTable tabla;
    public static JScrollPane scroll;
    public static DefaultTableModel modelo;

    public Reporte(){
        this.setSize(900,300);
        this.setLocationRelativeTo(null);
        this.setTitle("SurvivalVille");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        iniciarComponentes();
        
    }

    private void iniciarComponentes(){

        agregarPanel();
        agregarRadioButton();
        agregarBoton();
        agregarTabla();
    }

    private void agregarPanel(){
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));
    }

    private void agregarRadioButton(){

        reporteJugador = new JRadioButton("Reporte de jugador.");
        reporteJugador.setBounds(40,20, 200, 20);
        reporteJugador.setBackground(new Color(0,170,61));
        reporteJugador.setSelected(true);
        panel.add(reporteJugador);
        reporteJugador.setFont(new Font("Basic", Font.BOLD, 14));

        reporteAnimal = new JRadioButton("Reporte de animales.");
        reporteAnimal.setBounds(250,20, 200, 20);
        reporteAnimal.setBackground(new Color(0,170,61));
        panel.add(reporteAnimal);
        reporteAnimal.setFont(new Font("Basic", Font.BOLD, 14));

        reportePlanta = new JRadioButton("Reporte de Semillas.");
        reportePlanta.setBounds(460,20, 200, 20);
        reportePlanta.setBackground(new Color(0,170,61));
        panel.add(reportePlanta);
        reportePlanta.setFont(new Font("Basic", Font.BOLD, 14));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(reporteJugador);
        grupo.add(reporteAnimal);
        grupo.add(reportePlanta);

        oyenteJugador();
        oyenteAnimal();
        oyentePlanta();
    }

    private void agregarBoton(){
        botonVolver = new JButton("Volver");
        botonVolver.setBounds(700, 20, 100, 20);
        panel.add(botonVolver);

        oyenteVolver();
    }

    private void agregarTabla(){
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        tabla.setBounds(20, 60, 850, 200);
        panel.add(tabla);

        tabla.setEnabled(false);

        scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(20,60,850,200);
        panel.add(scroll);

        agregarJugadores();
    }

    //apartado para los oyentes de acción

    private void oyenteVolver(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                m1.setVisible(true);
                dispose();
            }
        };

        botonVolver.addActionListener(oyenteAccion);
    }


    private void oyenteJugador(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                agregarJugadores();
            }
        };

        reporteJugador.addActionListener(oyenteAccion);
    }

    private void agregarJugadores(){ //método que agrega todos los usuarios que han jugado

        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Nombre Granjero");
        modelo.addColumn("Duracion partida");
        modelo.addColumn("Oro generado");
        modelo.addColumn("Alimento Generado");
        modelo.addColumn("Alimento Consumido");
        modelo.addColumn("Celdas compradas");

        for(int i = 0; i < jugadores.length; i++){
            String[] fila = {jugadores[i].getNickName(), Integer.toString(jugadores[i].getTiempoDurado()), Double.toString(jugadores[i].getOroGenerado()), Integer.toString(jugadores[i].getAlimentoGenerado()), Integer.toString(jugadores[i].getAlimentoConsumido()), Integer.toString(jugadores[i].getCeldasCompradas())};
            modelo.addRow(fila);

        }
    }

    private void oyenteAnimal(){ 

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                agregarAnimales();
            }
        };

        reporteAnimal.addActionListener(oyenteAccion);
    }

    private void agregarAnimales(){ //método que agrega toda la informacióń de los animales a la tabla
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Nombre Animal");
        modelo.addColumn("Crias compradas");
        modelo.addColumn("Crias destazadas");

        for(int i = 0; i < animalesHerbivoros.length; i++){
            String[] fila = {animalesHerbivoros[i].getNombre(), Integer.toString(animalesHerbivoros[i].getCantidadAnimal()),Integer.toString(animalesHerbivoros[i].getCriasDestazadas())};
            modelo.addRow(fila);
        }
        for(int i = 0; i < animalesOmnivoros.length; i++){
            String[] fila = {animalesOmnivoros[i].getNombre(), Integer.toString(animalesOmnivoros[i].getCantidadAnimal()),Integer.toString(animalesOmnivoros[i].getCriasDestazadas())};
            modelo.addRow(fila);
        }
    }


    private void oyentePlanta(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                agregarPlanta();
            }
        };

        reportePlanta.addActionListener(oyenteAccion);
    }

    private void agregarPlanta(){ //método que agrega toda la información de las plantas a la tabla
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
        modelo.addColumn("Nombre Planta");
        modelo.addColumn("Bolsas compradas");
        modelo.addColumn("Celdas sembradas");

        for(int i = 0; i < granos.length; i++){
            String[] fila = {granos[i].getNombre(), Integer.toString(granos[i].getCantidad()),Integer.toString(granos[i].getCeldas())};
            modelo.addRow(fila);
        }
        for(int i = 0; i < frutos.length; i++){
            String[] fila = {frutos[i].getNombre(), Integer.toString(frutos[i].getCantidad()),Integer.toString(frutos[i].getCeldas())};
            modelo.addRow(fila);
        }
    }

    
}
