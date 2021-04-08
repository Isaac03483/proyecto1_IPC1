package ventanas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import enums.TipoProducto;
import java.awt.*;
import java.awt.event.*;
import animales.*;
import static ventanas.Menu.*;

public class CrearAnimal extends JFrame{

    public static JPanel panel;
    public static JButton botonVolver, botonCrear, botonAgregar;
    public static JLabel etiquetaTitulo,etiquetaNombre, etiquetaProductos, etiquetaVida, etiquetaPrecio, etiquetaProduccion;
    public static JTextField textoNombre, textoVida, textoPrecio;
    public static JRadioButton radioCon, radioSin, radioAmbos, radioH, radioO;
    public static String nombre;
    public static int vida;
    public static TipoProducto tipo = TipoProducto.AMBOS;
    public static double precio;
    public static boolean permitido;
    public static JTable tablaAnimal, tablaProducto;
    public static JScrollPane scrollAnimal, scrollProducto;
    public static DefaultTableModel modeloAnimal, modeloProducto;
    
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

        //agregamos modelo para la tabla donde se mostrarán los animales
        modeloAnimal = new DefaultTableModel();
        modeloAnimal.addColumn("Nombre");
        modeloAnimal.addColumn("Vida");
        modeloAnimal.addColumn("Precio");
        modeloAnimal.addColumn("Tipo");
        agregarFilasAnimal(animalesHerbivoros);
        tablaAnimal = new JTable(modeloAnimal); 
        tablaAnimal.setBounds(475,20,250,100);
        panel.add(tablaAnimal);
        
        scrollAnimal = new JScrollPane(tablaAnimal, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollAnimal.setBounds(475,20,250,100);
        panel.add(scrollAnimal);

        //agregamos modelo para la tabla donde se mostrarán los productos del animal seleccionado
        modeloProducto = new DefaultTableModel();
        modeloProducto.addColumn("Nombre");
        modeloProducto.addColumn("Precio");
        modeloProducto.addColumn("Cantidad");
        modeloProducto.addColumn("Tipo");

        tablaProducto = new JTable(modeloProducto);
        tablaProducto.setBounds(475, 165, 250, 100);

        scrollProducto = new JScrollPane(tablaProducto, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollProducto.setBounds(475,165,250,100);
        panel.add(scrollProducto);

        oyenteProductos();
    }

    private void agregarFilasAnimal(Animal[] animales){
        
        modeloAnimal.setRowCount(0);
        for(int i = 0; i < animales.length; i++){
            String[] fila = {animales[i].getNombre(), Integer.toString(animales[i].getVida()), Double.toString(animales[i].getPrecio()), animales[i].getTipo().name()};
            modeloAnimal.addRow(fila);
        }
    }

    private void oyenteProductos(){
        ListSelectionListener oyenteSeleccion = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(e.getValueIsAdjusting()){
                    modeloProducto.setRowCount(0);
                    int indice=0;
                    String nombre = (String)modeloAnimal.getValueAt(tablaAnimal.getSelectedRow(), 0);

                    if(radioH.isSelected()){

                        for(int i =0; i < animalesHerbivoros.length; i++){
                            if(nombre.equals(animalesHerbivoros[i].getNombre())){
                                indice = i;
                                break;
                            }
                        }

                        for(int i = 0; i< animalesHerbivoros[indice].getArregloProductos().length; i++){
                            String[] fila = {animalesHerbivoros[indice].getArregloProductos()[i].getNombre(), Double.toString(animalesHerbivoros[indice].getArregloProductos()[i].getPrecio()), Integer.toString(animalesHerbivoros[indice].getArregloProductos()[i].getCantidad()), animalesHerbivoros[indice].getArregloProductos()[i].getTipoProducto().name()};
                            modeloProducto.addRow(fila);
                        }
                    } else {

                        for(int i =0; i < animalesOmnivoros.length; i++){
                            if(nombre.equals(animalesOmnivoros[i].getNombre())){
                                indice = i;
                                break;
                            }
                        }
                        for(int i = 0; i< animalesOmnivoros[indice].getArregloProductos().length; i++){
                            String[] fila = {animalesOmnivoros[indice].getArregloProductos()[i].getNombre(), Double.toString(animalesOmnivoros[indice].getArregloProductos()[i].getPrecio()), Integer.toString(animalesOmnivoros[indice].getArregloProductos()[i].getCantidad()), animalesOmnivoros[indice].getArregloProductos()[i].getTipoProducto().name()};
                            modeloProducto.addRow(fila);
                        }
                    }

                }
                
            }
            
        };

        tablaAnimal.getSelectionModel().addListSelectionListener(oyenteSeleccion);
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
                agregarFilasAnimal(animalesHerbivoros);
              } else {
                  agregarFilasAnimal(animalesOmnivoros);

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

        etiquetaProduccion = new JLabel("Productos del animal:");
        etiquetaProduccion.setBounds(475, 140, 200, 20);
        panel.add(etiquetaProduccion);
        etiquetaProduccion.setFont(new Font("Basic", Font.BOLD, 14));
    }

    private void colocarBoton(){

        //agregamos un boton para volver al menú principal
        botonVolver = new JButton("Volver");
        botonVolver.setBounds(70,225,100,40);
        panel.add(botonVolver);
        botonVolver.setFont(new Font("Basic", Font.BOLD, 13));
        oyenteVolver();

        //agregamos un boton para crear al animal
        botonCrear = new JButton("Crear");
        botonCrear.setBounds(180, 225,100,40);
        panel.add(botonCrear);
        botonCrear.setFont(new Font("Basic", Font.BOLD, 13));
        oyenteCrear();

        //agregamos un boton para agregar productos al animal
        botonAgregar = new JButton("Agregar Producto");
        botonAgregar.setBounds(290,225, 150,40);
        panel.add(botonAgregar);
        botonAgregar.setFont(new Font("Basic", Font.BOLD, 10));
        oyenteAgregar();
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

    private void oyenteAgregar(){
        
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){

                CrearProducto c3 = new CrearProducto();
                c3.setVisible(true);
                dispose();
            }
        };

        botonAgregar.addActionListener(oyenteAccion);
    }

    private void limpiarTexto(){
        textoNombre.setText("");
        textoVida.setText("");
        textoPrecio.setText("");
    }

    private void crearAnimal(){
        if(radioH.isSelected()){
            animalesHerbivoros = redimensionarAnimales(animalesHerbivoros, new Herbivoro(nombre, vida,precio, tipo));
            agregarFilasAnimal(animalesHerbivoros);
        } else {
            animalesOmnivoros = redimensionarAnimales(animalesOmnivoros, new Omnivoro(nombre, vida,precio, tipo));
            agregarFilasAnimal(animalesOmnivoros);
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
