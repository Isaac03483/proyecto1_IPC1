package ventanas;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import animales.Animal;
import animales.Herbivoro;
import animales.Omnivoro;
import enums.TipoProducto;
import productos.*;
import semillas.*;

import java.awt.*;
import java.awt.event.*;

import static ventanas.Menu.*;

public class CrearProducto extends JFrame{

    private static JPanel panel;
    private static JRadioButton radioAlimento, radioMateria, radioAlDestazar, radioSinDestazar, radioAnimal, radioSemilla;
    private static JButton botonVolver, botonCrear;
    private static JTextField textoNombre, textoCantidad, textoPrecio, textoVida;
    private static JLabel etiquetaTitulo, etiquetaNombre, etiquetaCantidad, etiquetaPrecio, etiquetaVida, etiquetaAgregar;
    private static JTable tabla;
    private static DefaultTableModel modelo;
    private static JScrollPane scroll;
    private static JComboBox<Object> comboAgregar;
    private static boolean permitido, productoRepetido;
    private static String nombre;
    private static int cantidad, vida, indiceProducto;
    private static double precio;
    private static  TipoProducto tipoProducto;

    public CrearProducto(){

        this.setTitle("SurvivalVille");
        this.setSize(900,350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        iniciarComponentes();
    }

    private void iniciarComponentes(){
        colocarPanel();
        colocarEtiqueta();
        colocarRadioBotones();
        colocarTexto();
        colocarTabla();
        colocarComboBox();
        colocarBotones();
    }

    private void colocarPanel(){
        panel = new JPanel();
        panel.setBackground(new Color(0,170, 61));
        this.getContentPane().add(panel);
        panel.setLayout(null);
    }

    private void colocarEtiqueta(){
        etiquetaTitulo= new JLabel("Crear Producto.");
        etiquetaTitulo.setBounds(220,20,150,30);
        panel.add(etiquetaTitulo);
        etiquetaTitulo.setFont(new Font("Basic", Font.BOLD, 14));

        etiquetaNombre= new JLabel("Nombre:");
        etiquetaNombre.setBounds(20,150,100,30);
        panel.add(etiquetaNombre);
        etiquetaNombre.setFont(new Font("Basic", Font.BOLD, 14));
        
        etiquetaPrecio= new JLabel("Precio:");
        etiquetaPrecio.setBounds(20,180,100,30);
        panel.add(etiquetaPrecio);
        etiquetaPrecio.setFont(new Font("Basic", Font.BOLD, 14)); 

        etiquetaCantidad= new JLabel("Cantidad Generada:");
        etiquetaCantidad.setBounds(230,150,160,30);
        panel.add(etiquetaCantidad);
        etiquetaCantidad.setFont(new Font("Basic", Font.BOLD, 14));
        
        etiquetaVida= new JLabel("Vida Recuperada:");
        etiquetaVida.setBounds(230,180,160,30);
        panel.add(etiquetaVida);
        etiquetaVida.setFont(new Font("Basic", Font.BOLD, 14)); 

        etiquetaAgregar = new JLabel("Agregar a:");
        etiquetaAgregar.setBounds(20, 230, 160, 30);
        panel.add(etiquetaAgregar);
        etiquetaAgregar.setFont(new Font("Basic", Font.BOLD, 14));
    }

    private void colocarTexto(){
        textoNombre = new JTextField();
        textoNombre.setBounds(95, 155, 120, 20);
        panel.add(textoNombre);

        textoPrecio = new JTextField();
        textoPrecio.setBounds(95, 185, 120, 20);
        panel.add(textoPrecio);

        textoCantidad = new JTextField();
        textoCantidad.setBounds(400, 155, 100, 20);
        panel.add(textoCantidad);

        textoVida = new JTextField();
        textoVida.setBounds(400, 185, 100,20);
        panel.add(textoVida);
    }

    private void colocarRadioBotones(){

        radioAnimal = new JRadioButton("Animal.");
        radioAnimal.setBounds(180, 60, 90, 20);
        panel.add(radioAnimal);
        radioAnimal.setFont(new Font("Basic", Font.BOLD, 14));
        radioAnimal.setSelected(true);
        radioAnimal.setBackground(new Color(0,170,61));

        oyenteAnimal();

        radioSemilla = new JRadioButton("Semilla.");
        radioSemilla.setBounds(280, 60, 90, 20);
        panel.add(radioSemilla);
        radioSemilla.setFont(new Font("Basic", Font.BOLD, 14));
        radioSemilla.setBackground(new Color(0,170,61));

        oyenteSemilla();

        ButtonGroup grupoAS = new ButtonGroup();
        grupoAS.add(radioAnimal);
        grupoAS.add(radioSemilla);

        radioAlDestazar = new JRadioButton("Al Destazar.");
        radioAlDestazar.setBounds(40,90, 125, 20); //150
        panel.add(radioAlDestazar);
        radioAlDestazar.setFont(new Font("Basic", Font.BOLD, 14));
        radioAlDestazar.setSelected(true);
        radioAlDestazar.setBackground(new Color(0,170,61));

        radioSinDestazar = new JRadioButton("Sin Destazar.");
        radioSinDestazar.setBounds(40,120, 130, 20);
        panel.add(radioSinDestazar);
        radioSinDestazar.setFont(new Font("Basic", Font.BOLD, 14));
        radioSinDestazar.setBackground(new Color(0,170,61));

        ButtonGroup grupoDestazar = new ButtonGroup();
        grupoDestazar.add(radioAlDestazar);
        grupoDestazar.add(radioSinDestazar);

        radioAlimento = new JRadioButton("Alimento.");
        radioAlimento.setBounds(210, 90, 125, 20);
        panel.add(radioAlimento);
        radioAlimento.setFont(new Font("Basic", Font.BOLD, 14));
        radioAlimento.setBackground(new Color(0,170,61));
        radioAlimento.setSelected(true);

        oyenteAlimento();

        radioMateria = new JRadioButton("Materia Prima.");
        radioMateria.setBounds(210, 120, 150, 20); //180
        panel.add(radioMateria);
        radioMateria.setFont(new Font("Basic", Font.BOLD, 14));
        radioMateria.setBackground(new Color(0,170,61));

        oyenteMateria();

        ButtonGroup grupoProducto = new ButtonGroup();
        grupoProducto.add(radioAlimento);
        grupoProducto.add(radioMateria);
        
    }

    private void colocarTabla(){
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Tipo");
        tabla = new JTable(modelo);
        tabla.setBounds(550, 50, 300, 200);
        panel.add(tabla);

        scroll = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(550,50,300,200);
        panel.add(scroll);

        agregarProductos();
        oyenteTabla();
    }

    private void colocarComboBox(){
        
        comboAgregar = new JComboBox<>();
        comboAgregar.setBounds(125, 236, 100, 20);
        panel.add(comboAgregar);
        agregarAnimal();
    }

    private void colocarBotones(){
        botonVolver = new JButton("Volver");
        botonVolver.setBounds(70, 280, 100,30);
        panel.add(botonVolver);

        oyenteVolver();

        botonCrear = new JButton("Crear");
        botonCrear.setBounds(200, 280, 100, 30);
        panel.add(botonCrear);

        oyenteCrear();
    }

    //oyentes de acción

    private void oyenteAnimal(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                agregarAnimal();
                radioAlDestazar.setEnabled(true);
                radioSinDestazar.setEnabled(true);
                radioMateria.setEnabled(true);
            }
        };

        radioAnimal.addActionListener(oyenteAccion);
    }

    private void agregarAnimal(){
        comboAgregar.removeAllItems();

        for(int i= 0; i < animalesHerbivoros.length; i ++){
            comboAgregar.addItem((Animal)animalesHerbivoros[i]);
        }
        for(int i = 0; i < animalesOmnivoros.length; i++){
            comboAgregar.addItem((Animal)animalesOmnivoros[i]);
        }
    }

    private void oyenteSemilla(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                agregarSemilla();
                radioAlDestazar.setEnabled(false);
                radioSinDestazar.setEnabled(false);
                radioMateria.setEnabled(false);
                radioAlimento.setSelected(true);
                etiquetaVida.setEnabled(true);
                textoVida.setEnabled(true);
            }
        };

        radioSemilla.addActionListener(oyenteAccion);
    }

    private void agregarSemilla(){
        comboAgregar.removeAllItems();

        for(int i= 0; i < granos.length; i ++){
            comboAgregar.addItem((Planta)granos[i]);
        }
        for(int i = 0; i < frutos.length; i++){
            comboAgregar.addItem((Planta)frutos[i]);
        }
    }

    private void oyenteMateria(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                etiquetaVida.setEnabled(false);
                textoVida.setEnabled(false);
            }
        };

        radioMateria.addActionListener(oyenteAccion);
    }

    private void oyenteAlimento(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                etiquetaVida.setEnabled(true);
                textoVida.setEnabled(true);
            }
        };

        radioAlimento.addActionListener(oyenteAccion);
    }

    private void agregarProductos(){
        modelo.setRowCount(0);
        for(int i = 0; i < productosJuego.length; i++){
            String[] fila = {productosJuego[i].getNombre(), Double.toString(productosJuego[i].getPrecio()), productosJuego[i].getTipoProducto().name()};
            modelo.addRow(fila);
        }
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

    private void oyenteTabla(){

        ListSelectionListener oyenteSeleccion = new ListSelectionListener(){

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if(e.getValueIsAdjusting()){
                    int filaSeleccionada = tabla.getSelectedRow();

                    textoNombre.setText((String)modelo.getValueAt(filaSeleccionada, 0));
                    textoPrecio.setText((String)modelo.getValueAt(filaSeleccionada, 1));

                    if((String)modelo.getValueAt(filaSeleccionada, 2) == "ALDESTAZAR"){
                        radioAlDestazar.setSelected(true);
                    } else {
                        radioSinDestazar.setSelected(true);
                    }
                }
            }
            
        };

        tabla.getSelectionModel().addListSelectionListener(oyenteSeleccion);
    }

    //oyente para crear un nuevo producto y asignarlo a un animal
    private void oyenteCrear(){

        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                permitido = true;
                verificarCampos(); //llamamos al método para ver si todos los campos han sido llenados de forma correcta
                if(permitido==true){
                    verificarProducto();
                } else {
                    JOptionPane.showMessageDialog(null, "Ups! algo salió mal. Verifique que todos los campos necesarios se encuentren llenos.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        botonCrear.addActionListener(oyenteAccion); 
    }

    private void verificarCampos(){

        if(radioAlimento.isSelected()){
            verificarVida();
        }

        verificarNombre();
        verificarPrecio();
        verificarCantidad();
        verificarTipoProducto();
    }

    private void verificarNombre(){
        try{

            nombre = textoNombre.getText();
        } catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Campo nombre de producto se encuentra vacío", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void verificarPrecio(){
        try{

            precio =Double.parseDouble(textoPrecio.getText());
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Campo precio necesita un valor numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void verificarCantidad(){
        try{

            cantidad = Integer.parseInt(textoCantidad.getText());
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Campo cantidad de producto necesita un valor numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void verificarVida(){
        try{

            vida = Integer.parseInt(textoVida.getText());
        } catch(NumberFormatException e){

            JOptionPane.showMessageDialog(null, "Campo vida de producto necesita un valor numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            permitido=false;
        }
    }

    private void verificarTipoProducto(){
        if(radioAnimal.isSelected()){
            if(radioAlDestazar.isSelected()){
                tipoProducto = TipoProducto.ALDESTAZAR;
            } else {
                tipoProducto = TipoProducto.SINDESTAZAR;
            }
        } else if(radioSemilla.isSelected()){
            Planta plantaSeleccionada = (Planta)comboAgregar.getSelectedItem();

            if(plantaSeleccionada instanceof Grano){
                tipoProducto = TipoProducto.GRANO;
            } else {
                tipoProducto = TipoProducto.FRUTO;
            }
        }
    }

    private void verificarProducto(){

        productoRepetido=false;

        
        for(int i= 0; i < productosJuego.length; i++){
            if(nombre.equals(productosJuego[i].getNombre())){
                indiceProducto = i;
                productoRepetido=true;
                break;
            }
        }

        if(productoRepetido == true){
            JOptionPane.showMessageDialog(null, "Ya existe un producto con ese nombre.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else {
            crearProducto();
            JOptionPane.showMessageDialog(null, "Producto creado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        }
        

        asignarProducto();
        agregarProductos();
        
        
    }

    private void crearProducto(){

        if(radioAlimento.isSelected()){
            productosJuego = redimensionarProductos(productosJuego, new Alimento(nombre, precio, cantidad, vida, tipoProducto));
        } else {
            productosJuego = redimensionarProductos(productosJuego, new Materia(nombre, precio, cantidad, tipoProducto));
        }

        indiceProducto = productosJuego.length-1;
        limpiarTexto();
    }


    private void asignarProducto(){
        int indice=0;
        if(radioAnimal.isSelected()){
            Animal animalSeleccionado = (Animal)comboAgregar.getSelectedItem();
            if(animalSeleccionado instanceof Herbivoro){
                for(int i = 0; i < animalesHerbivoros.length; i++){
                    if(animalSeleccionado.getNombre() == animalesHerbivoros[i].getNombre()){
                        indice = i;
                        break;
                    }
                }

                animalesHerbivoros[indice].agregarProducto(productosJuego[indiceProducto]);

            } else if (animalSeleccionado instanceof Omnivoro){
                for(int i = 0; i < animalesOmnivoros.length; i++){
                    if(animalSeleccionado.getNombre() == animalesOmnivoros[i].getNombre()){
                        indice = i;
                        break;
                    }
                }

                animalesOmnivoros[indice].agregarProducto(productosJuego[indiceProducto]);
            
            }
            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Planta semillaSeleccionada = (Planta)comboAgregar.getSelectedItem();
            if(semillaSeleccionada instanceof Grano){
                for(int i = 0; i < granos.length; i++){
                    if(semillaSeleccionada.getNombre() == granos[i].getNombre()){
                        indice = i;
                        break;
                    }
                }

                if(granos[indice].productoAsignado()){
                    JOptionPane.showMessageDialog(null, "Esta semilla ya cuenta con un producto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    granos[indice].agregarProducto(productosJuego[indiceProducto]);
                }

            } else if (semillaSeleccionada instanceof Fruto){
                for(int i = 0; i < frutos.length; i++){
                    if(semillaSeleccionada.getNombre() == frutos[i].getNombre()){
                        indice = i;
                        break;
                    }
                }

                if(frutos[indice].productoAsignado()){
                    JOptionPane.showMessageDialog(null, "Esta semilla ya cuenta con un producto.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    frutos[indice].agregarProducto(productosJuego[indiceProducto]);
                }
            }
        
        }
    }

    private void limpiarTexto(){
        textoNombre.setText("");
        textoCantidad.setText("");
        textoPrecio.setText("");
        textoVida.setText("");
    }
}
