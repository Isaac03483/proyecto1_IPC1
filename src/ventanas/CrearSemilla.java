package ventanas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import semillas.*;
import static ventanas.Menu.*;


public class CrearSemilla extends JFrame{

    public static JPanel panel;
    public static JLabel etiquetaTitulo, etiquetaNombre, etiquetaVida, etiquetaPrecio, etiquetaProductos;
    public static JTextField textoNombre, textoVida, textoPrecio;
    public static JTable tablaSemilla, tablaProducto;
    public static JScrollPane scrollSemilla, scrollProducto;
    public static DefaultTableModel modeloSemilla, modeloProducto;
    public static JRadioButton radioGrano, radioFruto;
    public static JButton botonVolver, botonCrear, botonAgregar;
    public static Boolean permitido;
    public static String nombre;
    public static int vida;
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

        //agregamos etiqueta para ver el producto que produce la semilla
        etiquetaProductos = new JLabel("Producto de la semilla:");
        etiquetaProductos.setBounds(375, 205, 220, 20);
        panel.add(etiquetaProductos);
        etiquetaProductos.setFont(new Font("Basic", Font.BOLD, 14));

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

    private void oyenteTipo(){  //método que agrega toda la información a las filas de la tabla
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
        botonVolver.setBounds(20,195,100,40);
        panel.add(botonVolver);
        oyenteVolver();

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(130,195,100,40);
        panel.add(botonCrear);
        oyenteCrear();

        //agregamos un boton para agregar productos al animal
        botonAgregar = new JButton("Agregar Producto");
        botonAgregar.setBounds(20,245, 150,40);
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

    public void crearSemilla(){ //crea el objeto de semilla dependiendo del radioButton seleccionado
        if(radioFruto.isSelected()){
            frutos = redimensionarPlantas(frutos, new Fruto(nombre, vida,precio));
            agregarFilas(frutos);
        } else {
            granos = redimensionarPlantas(granos, new Grano(nombre, vida,precio));
            agregarFilas(granos);
        }
    }

    private void verificarCampos(){ //método utilizado para verificar que los campos se hayan llenado de forma correcta

        verificarNombre();
        verificarNumericos();
    }

    private void verificarNombre(){ //verifica que el nombre no se encuentre vacío
        nombre = textoNombre.getText();
        if(nombre.equals("")){
            JOptionPane.showMessageDialog(null, "Campo nombre se encuentra vacío.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false; //si está vacío entonces no es permitido crear el objeto
        }
    }

    private void verificarNumericos(){ //verifica campos numéricos como la vida y el precio de la semilla
        try{
            vida = Integer.parseInt(textoVida.getText());
            precio = Double.parseDouble(textoPrecio.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Campos vida y precio requieren valores tipo numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false; //de no ser numéricos no está permitido crear el objeto/semilla
        }
    }

    private void colocarTabla(){

        modeloSemilla = new DefaultTableModel();
        modeloSemilla.addColumn("Nombre");
        modeloSemilla.addColumn("Vida");
        modeloSemilla.addColumn("Precio");

        agregarFilas(granos);
        tablaSemilla = new JTable(modeloSemilla);
        tablaSemilla.setBounds(375,40,250,150);
        panel.add(tablaSemilla);

        scrollSemilla = new JScrollPane(tablaSemilla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollSemilla.setBounds(375,40,250,150);
        panel.add(scrollSemilla);

        modeloProducto = new DefaultTableModel();
        modeloProducto.addColumn("Nombre");
        modeloProducto.addColumn("Precio");
        modeloProducto.addColumn("Cantidad");
        modeloProducto.addColumn("Tipo");

        tablaProducto = new JTable(modeloProducto);
        tablaProducto.setBounds(375, 225, 250, 50);
        panel.add(tablaProducto);

        scrollProducto = new JScrollPane(tablaProducto, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollProducto.setBounds(375,225,250,50);
        panel.add(scrollProducto);

        oyenteProductos();

    }

    private void agregarFilas(Planta[] plantas){ //método que agrega filas a la tabla dependiendo el parámetro enviado
        
        modeloSemilla.setRowCount(0);
        for(int i = 0; i < plantas.length; i++){
            String[] fila = {plantas[i].getNombre(), Integer.toString(plantas[i].getVida()), String.valueOf(plantas[i].getPrecio())};
            modeloSemilla.addRow(fila);
        }
    }

    private void oyenteProductos(){
        //método que muestra el producto de la semilla seleccionada en la tabla
        ListSelectionListener oyenteSeleccion = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){

                    modeloProducto.setRowCount(0);
                    int indice=0;

                    String nombre = (String)modeloSemilla.getValueAt(tablaSemilla.getSelectedRow(), 0);

                    if(radioGrano.isSelected()){

                        for(int i =0; i < granos.length; i++){
                            if(nombre.equals(granos[i].getNombre())){
                                indice = i;
                                break;
                            }
                        }

                        
                        if(granos[indice].getProducto() != null){
                            String[] fila = {granos[indice].getProducto().getNombre(), Double.toString(granos[indice].getProducto().getPrecio()), Integer.toString(granos[indice].getProducto().getCantidad()), granos[indice].getProducto().getTipoProducto().name()};
                            modeloProducto.addRow(fila);
                        }
                        
                    } else {

                        for(int i =0; i < frutos.length; i++){
                            if(nombre.equals(frutos[i].getNombre())){
                                indice = i;
                                break;
                            }
                        }
                        
                        if(frutos[indice].getProducto() != null){
                            String[] fila = {frutos[indice].getProducto().getNombre(), Double.toString(frutos[indice].getProducto().getPrecio()), Integer.toString(frutos[indice].getProducto().getCantidad()), frutos[indice].getProducto().getTipoProducto().name()};
                            modeloProducto.addRow(fila);
                        }
                        
                    }

                }
                
            }

            
            
        };

        tablaSemilla.getSelectionModel().addListSelectionListener(oyenteSeleccion);
    }
    
    private void limpiarTexto(){ //limpia los textField
        textoNombre.setText("");
        textoVida.setText("");
        textoPrecio.setText("");
    }
}
