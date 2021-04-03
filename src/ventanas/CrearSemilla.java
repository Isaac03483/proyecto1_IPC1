package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import semillas.*;
import static ventanas.Menu.*;


public class CrearSemilla extends JFrame{

    public static JPanel panel;
    public static JLabel etiquetaTitulo, etiquetaNombre, etiquetaVida, etiquetaPrecio, etiquetaCantidad;
    public static JTextField textoNombre, textoVida, textoPrecio, textoCantidad;
    public static JTable tabla;
    public static JScrollPane scroll;
    public static DefaultTableModel modelo;
    public static JRadioButton radioGrano, radioFruto;
    public static JButton botonVolver, botonCrear;
    public static Boolean permitido;
    public static String nombre;
    public static int vida, cantidad;
    public static double precio;

    public CrearSemilla(){

        this.setTitle("SurvivalVille");
        this.setSize(650,325);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciarComponentes();
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarRadio();
        colocarTexto();
        colocarBotones();
        colocarTabla();
    }

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));
    }

    private void colocarEtiquetas(){

        //agregamos etiqueta para el título de la ventana
        etiquetaTitulo = new JLabel(".:CREAR SEMILLA:.");
        etiquetaTitulo.setBounds(150,10,200,40);
        panel.add(etiquetaTitulo);
        //agregamos etiqueta para el nombre de la nueva semilla
        etiquetaNombre = new JLabel("Nombre de semilla:");
        etiquetaNombre.setBounds(20,80,160,20);
        panel.add(etiquetaNombre);
        etiquetaNombre.setFont(new Font("Basic", Font.BOLD, 14));
        //agregamos etiqueta para la vida de la nueva semilla
        etiquetaVida = new JLabel("Vida de semilla:");
        etiquetaVida.setBounds(20,120,160,20);
        panel.add(etiquetaVida);
        etiquetaVida.setFont(new Font("Basic", Font.BOLD, 14));
        //agregamos etiqueta para el precio de esta nueva semilla
        etiquetaPrecio = new JLabel("Precio de semilla:");
        etiquetaPrecio.setBounds(20,160,160,20);
        panel.add(etiquetaPrecio);
        etiquetaPrecio.setFont(new Font("Basic",Font.BOLD, 14));
        //agregamos etiqueta para la cantidad de semillas necesaria para sembrar
        etiquetaCantidad = new JLabel("Cantidad para siembra:");
        etiquetaCantidad.setBounds(20,200,190,20);
        panel.add(etiquetaCantidad);
        etiquetaCantidad.setFont(new Font("Basic", Font.BOLD,14));
    }

    private void colocarTexto(){
        //agregamos cajas de texto para el nombre, vida y precio de la nueva semilla
        textoNombre = new JTextField();
        textoNombre.setBounds(180, 80, 100,20);
        panel.add(textoNombre);

        textoVida = new JTextField();
        textoVida.setBounds(180,120,100,20);
        panel.add(textoVida);

        textoPrecio = new JTextField();
        textoPrecio.setBounds(180,160,100,20);
        panel.add(textoPrecio);

        textoCantidad  = new JTextField();
        textoCantidad.setBounds(210,200,70,20);
        panel.add(textoCantidad);

    }

    private void colocarRadio(){
        //agregamos RadioButton's para el tipo de Semilla que quiera crear
        radioGrano = new JRadioButton("Tipo Grano.");
        radioGrano.setBounds(85,50,120,20);
        panel.add(radioGrano);
        radioGrano.setFont(new Font("Basic", Font.BOLD, 14));
        radioGrano.setBackground(new Color(0,170,61));
        radioGrano.setSelected(true);

        radioFruto = new JRadioButton("Tipo Fruto.");
        radioFruto.setBounds(215, 50, 120, 20);
        panel.add(radioFruto);
        radioFruto.setFont(new Font("Basic", Font.BOLD, 14));
        radioFruto.setBackground(new Color(0,170,61));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioGrano);
        grupo.add(radioFruto);

        oyenteTipo();
    }

    private void oyenteTipo(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                if(radioGrano.isSelected()){
                    agregarFilas(granos);
                } else {
                    agregarFilas(frutos);
                }
            }

        };
        radioGrano.addActionListener(oyenteAccion);
        radioFruto.addActionListener(oyenteAccion);
    }

    private void colocarBotones(){
        //agregamos botones para volver y para crear la nueva Semilla
        botonVolver = new JButton("Volver");
        botonVolver.setBounds(110,235,100,40);
        panel.add(botonVolver);
        oyenteVolver();

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(220,235,100,40);
        panel.add(botonCrear);
        oyenteCrear();
    }

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

    private void oyenteCrear(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                permitido= true;
                verificarCampos();
                if(permitido==true){
                    crearSemilla();
                    limpiarTexto();
                    JOptionPane.showMessageDialog(null, "Semilla creada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ups! algo salió mal. Verifique que todos los campos se encuentren llenos.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);

                }
            }
        };

        botonCrear.addActionListener(oyenteAccion);
    }

    public void crearSemilla(){
        if(radioFruto.isSelected()){
            frutos = redimensionarPlantas(frutos, new Fruto(nombre, vida,precio));
            agregarFilas(frutos);
        } else {
            granos = redimensionarPlantas(granos, new Grano(nombre, vida,precio));
            agregarFilas(granos);
        }
    }

    private void verificarCampos(){

        verificarNombre();
        verificarNumericos();
    }

    private void verificarNombre(){
        nombre = textoNombre.getText();
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "Campo nombre se encuentra vacío.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void verificarNumericos(){
        try{
            vida = Integer.parseInt(textoVida.getText());
            precio = Double.parseDouble(textoPrecio.getText());
            cantidad = Integer.parseInt(textoCantidad.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Campos vida, precio y cantidad requieren valores tipo numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void colocarTabla(){

        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Vida");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");

        agregarFilas(granos);
        tabla = new JTable(modelo);
        tabla.setBounds(375,40,250,220);
        panel.add(tabla);

        scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(375,40,250,220);
        panel.add(scroll);
    }

    private void agregarFilas(Planta[] plantas){
        
        modelo.setRowCount(0);
        for(int i = 0; i < plantas.length; i++){
            String[] fila = {plantas[i].getNombre(), Integer.toString(plantas[i].getVida()), String.valueOf(plantas[i].getPrecio()), Integer.toString(plantas[i].getCantidad())};
            modelo.addRow(fila);
        }
    }
    
    private void limpiarTexto(){
        textoNombre.setText("");
        textoVida.setText("");
        textoPrecio.setText("");
        textoCantidad.setText("");
    }
}
