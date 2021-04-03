package ventanas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import enums.TipoProducto;
import java.awt.*;
import java.awt.event.*;
import animales.*;
import static ventanas.Menu.*;

public class CrearAnimal extends JFrame{

    public static JPanel panel;
    public static JButton botonVolver, botonCrear;
    public static JLabel etiquetaTitulo,etiquetaNombre, etiquetaProductos, etiquetaVida, etiquetaPrecio, etiquetaProduccion;
    public static JTextField textoNombre, textoVida, textoPrecio;
    public static JRadioButton radioCon, radioSin, radioAmbos, radioH, radioO;
    public static String nombre;
    public static int vida;
    public static TipoProducto tipo = TipoProducto.AMBOS;
    public static double precio;
    public static boolean permitido;
    public static JTable tabla;
    public static JScrollPane scroll;
    public static DefaultTableModel modelo;
    
    public CrearAnimal(){
        this.setTitle("SurvivalVille");
        this.setSize(750,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiqueta();
        colocarBoton();
        colocarTexto();
        colocarRadioBotones();
        colocarTabla();
    }
    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));

    }

    private void colocarTabla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Vida");
        modelo.addColumn("Precio");
        modelo.addColumn("Tipo");
        agregarFilas(animalesHerbivoros);
        tabla = new JTable(modelo); 
        tabla.setBounds(475,40,250,220);
        panel.add(tabla);
        
        scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(475,40,250,220);
        panel.add(scroll);

    }

    private void agregarFilas(Animal[] animales){
        
        modelo.setRowCount(0);
        for(int i = 0; i < animales.length; i++){
            String[] fila = {animales[i].getNombre(), Integer.toString(animales[i].getVida()), String.valueOf(animales[i].getPrecio()), animales[i].getTipo().name()};
            modelo.addRow(fila);
        }
    }

    private void colocarRadioBotones(){
        //agregamos radioButton para el tipo de animal
        radioH = new JRadioButton("Animal Herbívoro.");
        radioH.setBounds(300,60,170,30);
        radioH.setFont(new Font("Basic",Font.BOLD,14));
        panel.add(radioH);
        radioH.setBackground(new Color(0,170,61));
        radioH.setSelected(true);

        radioO = new JRadioButton("Animal Omnívoro.");
        radioO.setBounds(300,100,170,30);
        radioO.setFont(new Font("Basic",Font.BOLD,14));
        panel.add(radioO);
        radioO.setBackground(new Color(0,170,61));

        ButtonGroup grupoAnimal = new ButtonGroup(); //agregamos los radioButton a un grupo de botones
        grupoAnimal.add(radioH);
        grupoAnimal.add(radioO);

        oyenteAnimales();
        //agregamos radioButton para los productos a almacenar
        radioCon = new JRadioButton("Al Destazar");
        radioCon.setBounds(120, 180, 125, 30);
        panel.add(radioCon);
        radioCon.setBackground(new Color(0,170,61));

        radioSin = new JRadioButton("Sin Destazar");
        radioSin.setBounds(240, 180, 125, 30);
        panel.add(radioSin);
        radioSin.setBackground(new Color(0,170,61));

        radioAmbos = new JRadioButton("Ambas");
        radioAmbos.setBounds(360, 180, 80, 30);
        panel.add(radioAmbos);
        radioAmbos.setBackground(new Color(0,170,61));
        radioAmbos.setSelected(true);

        ButtonGroup grupoProducto = new ButtonGroup(); //agregamos los radioButton a un grupo de botones
        grupoProducto.add(radioCon);
        grupoProducto.add(radioSin);
        grupoProducto.add(radioAmbos);        

        oyenteTipoProducto();

    
    }

    private void oyenteTipoProducto(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e){
                if(radioCon.isSelected()){
                    tipo = TipoProducto.ALDESTAZAR;
                } else if(radioSin.isSelected()){
                    tipo = TipoProducto.SINDESTAZAR;
                } else {
                    tipo = TipoProducto.AMBOS;
                }
            }
        };

        radioCon.addActionListener(oyenteAccion);
        radioSin.addActionListener(oyenteAccion);
        radioAmbos.addActionListener(oyenteAccion);
    }

    private void oyenteAnimales(){

        ActionListener oyenteAccion = new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent ae){
              if(radioH.isSelected()){
                agregarFilas(animalesHerbivoros);
              } else {
                  agregarFilas(animalesOmnivoros);

              }
          }  
        };
        radioH.addActionListener(oyenteAccion);
        radioO.addActionListener(oyenteAccion);
    }

    private void colocarTexto(){

        //agregamos caja texto para el nombre del animal
        textoNombre = new JTextField();
        textoNombre.setBounds(180,60,100,20);
        panel.add(textoNombre);
        //agregamos caja texto para la vida del animal
        textoVida = new JTextField();
        textoVida.setBounds(180,100, 100,20);
        panel.add(textoVida);
        //agregamos caja texto para el precio del animal
        textoPrecio = new JTextField();
        textoPrecio.setBounds(180,140,100,20);
        panel.add(textoPrecio);
        
    }

    private void colocarEtiqueta(){

        etiquetaTitulo = new JLabel("CREAR NUEVO ANIMAL.");
        etiquetaTitulo.setBounds(150,15,200,40);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic",Font.BOLD,15));
        //agregamos etiqueta para el nombre
        etiquetaNombre = new JLabel("Nombre del animal:");
        etiquetaNombre.setBounds(20,60,160,20);
        panel.add(etiquetaNombre);
        etiquetaNombre.setFont(new Font("Basic", Font.BOLD,14));
        //agregamos etiqueta para la vida
        etiquetaVida = new JLabel("Vida del animal:");
        etiquetaVida.setBounds(20,100, 130,20);
        panel.add(etiquetaVida);
        etiquetaVida.setFont(new Font("Basic", Font.BOLD, 14));
        //agregamos etiqueta para el precio del animal
        etiquetaPrecio = new JLabel("Precio del animal:");
        etiquetaPrecio.setBounds(20,140,140,20);
        panel.add(etiquetaPrecio);
        etiquetaPrecio.setFont(new Font("Basic", Font.BOLD, 14));
        //agregamos etiqueta para saber los productos que serán agregados
        etiquetaProductos = new JLabel("Productos:");
        etiquetaProductos.setBounds(20,185, 100,20);
        panel.add(etiquetaProductos);
        etiquetaProductos.setFont(new Font("Basic", Font.BOLD, 14));
    }

    private void colocarBoton(){

        //agregamos un boton para volver al menú principal
        botonVolver = new JButton("Volver");
        botonVolver.setBounds(110,225,100,40);
        panel.add(botonVolver);
        botonVolver.setFont(new Font("Basic", Font.BOLD, 13));
        oyenteVolver();

        //agregamos un boton para crear al animal
        botonCrear = new JButton("Crear");
        botonCrear.setBounds(220, 225,100,40);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
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
                permitido=true;
                verificarCampos();
                if(permitido==true){
                    crearAnimal();
                    limpiarTexto();
                    JOptionPane.showMessageDialog(null, "Animal creado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ups! algo salió mal. Verifique que todos los campos se encuentren llenos.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        botonCrear.addActionListener(oyenteAccion);
    }

    private void limpiarTexto(){
        textoNombre.setText("");
        textoVida.setText("");
        textoPrecio.setText("");
    }

    private void crearAnimal(){
        if(radioH.isSelected()){
            animalesHerbivoros = redimensionarAnimales(animalesHerbivoros, new Herbivoro(nombre, vida,precio, tipo));
            agregarFilas(animalesHerbivoros);
        } else {
            animalesOmnivoros = redimensionarAnimales(animalesOmnivoros, new Omnivoro(nombre, vida,precio, tipo));
            agregarFilas(animalesOmnivoros);
        }
    }

    private void verificarCampos(){
        verificarNombre();
    }

    private void verificarNombre(){
        nombre = textoNombre.getText();
        if(!nombre.equals("")){
            try{
                vida = Integer.parseInt(textoVida.getText());
                precio = Double.parseDouble(textoPrecio.getText());
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error, en los campos vida y precio debe ingresar un valor numérico", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                permitido=false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar el campo de Nombre del animal.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            permitido=false;
        }
    }

}
