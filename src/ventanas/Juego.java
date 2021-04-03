package ventanas;

import java.awt.*;
import javax.swing.*;

import enums.EstadoBarco;
import enums.EstadoGrama;
import enums.EstadoLago;

import java.awt.event.*;
import terrenos.Lago;
import terrenos.*;
import juego.*;

import static ventanas.Inicio.p1;

public class Juego extends JFrame{
    
    public static JPanel panel;
    public static Terreno[][] terreno;
    public static JLabel[][] objetos;
    public static JLabel granero, etiquetaNickName, etiquetaOro, etiquetaVida, etiquetaCorazon, etiquetaMoneda, etiquetaPersonaje, etiquetaMercado;
    public static JButton botonVolver;
    public static JToggleButton verTerreno, sembrar, pescar, crearParcela, comprarTerreno;
    public static final int precioTierra = 20;
    public static int x = 60, y = 160;
    public static int indiceBarco;

    static ImageIcon granja = new ImageIcon("granja.png");
    static ImageIcon corazon = new ImageIcon("corazon.png");
    static ImageIcon personaje = new ImageIcon("personaje.png");
    static ImageIcon moneda = new ImageIcon("moneda.png");
    static ImageIcon mercado = new ImageIcon("mercado.png");
    
    public Juego(){
        terreno = new Terreno[7][7];
        objetos = new JLabel[7][7];
        this.setSize(1000,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("SurvivalVille");
        this.setResizable(false);
        this.setUndecorated(false);
        
        iniciarComponentes();
        
    }

    private void iniciarComponentes(){

        colocarPanel();
        colocarEtiquetas();
        colocarBoton();
        colocarToggle();
        iniciarHilos();
    }

    private void iniciarHilos(){

        p1.start();
    }

    private void colocarToggle(){

        verTerreno = new JToggleButton("Ver terreno", false);
        verTerreno.setBounds(800,160,175,30);
        panel.add(verTerreno);

        sembrar = new JToggleButton("Sembrar", false);
        sembrar.setBounds(800,200,175,30);
        panel.add(sembrar);

        pescar = new JToggleButton("Pescar", false);
        pescar.setBounds(800, 240, 175, 30);
        panel.add(pescar);

        crearParcela = new JToggleButton("Crear Parcela", false);
        crearParcela.setBounds(800,280,175,30);
        panel.add(crearParcela);

        comprarTerreno = new JToggleButton("Comprar Terreno", false);
        comprarTerreno.setBounds(800,320,175,30);
        panel.add(comprarTerreno);
        ButtonGroup grupoBotones = new ButtonGroup();
        grupoBotones.add(verTerreno);
        grupoBotones.add(sembrar);
        grupoBotones.add(pescar);
        grupoBotones.add(crearParcela);
        grupoBotones.add(comprarTerreno);

        oyenteSembrar();
        oyentePescar();
        oyenteVer();
        oyenteComprar();
        oyenteParcela();
    }

    private void colocarBoton(){

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(600,50,100,50);
        panel.add(botonVolver);
        oyenteVolver();

    }


    private void colocarPanel(){
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));
    }

    private void colocarEtiquetas(){

        //etiqueta imagen Granero
        granero = new JLabel(); //creamos la etiqueta
        granero.setBounds(60, 50, 150, 130); //creamos las dimensiones y la posición en el panel
        granero.setIcon(new ImageIcon(granja.getImage().getScaledInstance(granero.getWidth(), granero.getHeight(), Image.SCALE_SMOOTH))); //creamos la imagen y se la asignamos a la etiqueta
        panel.add(granero); //agregamos la etiqueta al panel

        oyenteGranja();

        //etiqueta para nickName del jugador
        etiquetaNickName = new JLabel(p1.getNickName()); //creamos la etiqueta con texto del método getNickName de clase Jugador
        etiquetaNickName.setBounds(305,47,200,20); //creamos dimensiones y posición en el panel
        panel.add(etiquetaNickName); //agregamos la etiqueta al panel
        etiquetaNickName.setFont(new Font("Basic", Font.BOLD,14));
        etiquetaNickName.setForeground(Color.BLACK); //cambiamos el color para estética
        //etiqueta para imagen de personaje
        etiquetaPersonaje = new JLabel();
        etiquetaPersonaje.setBounds(260,30,50,50);
        etiquetaPersonaje.setIcon(new ImageIcon(personaje.getImage().getScaledInstance(etiquetaPersonaje.getWidth(), etiquetaPersonaje.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaPersonaje);
        //etiqueta para el Oro del jugador
        etiquetaOro = new JLabel(Double.toString(p1.getOro()));
        etiquetaOro.setBounds(305,84,200,20);
        panel.add(etiquetaOro);
        etiquetaOro.setForeground(new Color(255,255,0));
        etiquetaOro.setFont(new Font("Basic", Font.BOLD, 14));
        //etiqueta para imagen de moneda
        etiquetaMoneda = new JLabel();
        etiquetaMoneda.setBounds(260,70,50,50);
        etiquetaMoneda.setIcon(new ImageIcon(moneda.getImage().getScaledInstance(etiquetaMoneda.getWidth(), etiquetaMoneda.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaMoneda);
        //etiqueta para la vida del jugador
        etiquetaVida = new JLabel(Integer.toString(p1.getVida()));
        etiquetaVida.setBounds(305,115,200,20);
        panel.add(etiquetaVida);
        etiquetaVida.setFont(new Font("Basic", Font.BOLD,14));
        etiquetaVida.setForeground(Color.RED);
        //etiqueta para imagen de corazón
        etiquetaCorazon = new JLabel();
        etiquetaCorazon.setBounds(271,110,30,30);
        etiquetaCorazon.setIcon(new ImageIcon(corazon.getImage().getScaledInstance(etiquetaCorazon.getWidth(), etiquetaCorazon.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaCorazon);
        
        colocarTerreno();
        colocarMercado();
        
    }

    private void colocarMercado(){
        //etiqueta para el mercado
        etiquetaMercado = new JLabel();
        etiquetaMercado.setBounds(850,50,70,70);
        etiquetaMercado.setIcon(new ImageIcon(mercado.getImage().getScaledInstance(etiquetaMercado.getWidth(), etiquetaMercado.getHeight(), Image.SCALE_SMOOTH)));
        panel.add(etiquetaMercado);

        oyenteMercado();
    }


    private void generarPosicion(int i, int j){
        
                
        objetos[i][j].setBounds(x, y, 50,50);
        terreno[i][j].setBounds(x, y, 70, 70);
        x+=100;
        if(j == 6){
            if( i == 6){
                x = 60;
                y = 160;
            } else {
                y += 100;
                x = 60;
            }
        } 
        panel.add(objetos[i][j]);
        panel.add(terreno[i][j]);
        objetos[i][j].setVisible(false);
            
        
    }

    private void colocarTerreno(){
        for(int i = 0; i < 7; i++){
            for(int j = 0; j <7; j++){
                objetos[i][j] = new JLabel();
                generarTerreno(i, j); //llamamos al método del terreno
                generarPosicion(i, j);
                terreno[i][j].generarTerreno();
                if(i > 4 || j>4){ //si la columna o fila es mayor a 4 (recordando que el arreglo tiene 7 columnas pero estas inician desde 0) entonces
                    terreno[i][j].setEnabled(false); //bloqueamos el terreno
                    terreno[i][j].setVisible(false);  //escondemos el terreno
                }

                
                oyenteTerreno(i, j);
            }
        }
    }

    

    private void generarTerreno(int i, int j){

        int aleatorio = ((int)(Math.random()*100)+1);
        if(aleatorio>60 && aleatorio <= 100){

            terreno[i][j] = new Lago();
        
        } else if (aleatorio >25 && aleatorio <= 60){
            terreno[i][j] = new Grama();
        } else {
            terreno[i][j] = new Desierto();
        }

       
    }

    //oyentes de acción de todos los botones/etiquetas

    private void oyenteGranja(){
        MouseListener oyenteAccion = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Bodega.bodega();
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            
        };

        granero.addMouseListener(oyenteAccion);
    }

    private void oyenteTerreno(int i, int j){ //le asignamos un evento a todos los terrenos/JLabels
        MouseListener oyenteAccion = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if(terreno[i][j] instanceof Grama){

                    if(sembrar.isSelected()){
                       accionesGrama(i, j);
                    }

                } else if(terreno[i][j] instanceof Lago){
                    if(pescar.isSelected()){

                        accionesLago(i, j);
                       
                    }
                } else if(terreno[i][j] instanceof Desierto && terreno[i][j].isEnabled()){

                    JOptionPane.showMessageDialog(null, "Desierto, no se puede realizar ninguna tarea en él.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }

                if(comprarTerreno.isSelected()){
                    if(!terreno[i][j].isEnabled()){

                        if(p1.getOro() >=precioTierra){

                            JOptionPane.showMessageDialog(null, "Tierra comprada con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                            p1.disminuirOro(precioTierra);
                            p1.setCeldas();
                            etiquetaOro.setText(""+p1.getOro());
                            terreno[i][j].setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "No cuenta con el dinero suficiente", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        };

        terreno[i][j].addMouseListener(oyenteAccion);
    }

    private void accionesLago(int i, int j){
        if(((Lago)terreno[i][j]).getEstado()== EstadoLago.CONPECES){

            p1.colocarBarco((Lago)terreno[i][j], objetos[i][j]);
            
        } else if (((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCO){
            JOptionPane.showMessageDialog(null, "El barco se encuentra pescando en este momento, espere a que todos los peces hayan sido pescados.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else if(((Lago)terreno[i][j]).getEstado() == EstadoLago.SINPECES){
            
            JOptionPane.showMessageDialog(null, "El lago se está llenando de peces, por favor espere a que esté lleno.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            
        } else if(((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCOSINPECES){
            objetos[i][j].setVisible(false);
            p1.agregarProducto(p1.getArregloBarco()[((Lago)terreno[i][j]).getIndiceBarco()].getPecesPescados());
            p1.getArregloBarco()[((Lago)terreno[i][j]).getIndiceBarco()].cambiarEstado(EstadoBarco.DISPONIBLE);
            ((Lago)terreno[i][j]).cambiarEstado(EstadoLago.SINPECES);
            ((Lago)terreno[i][j]).generarPeces();
            JOptionPane.showMessageDialog(null, "El barco ha regresado con varios peces.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void accionesGrama(int i, int j){
        if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.DISPONIBLE){
            int opcionSemilla=0;

            try{
                opcionSemilla= Integer.parseInt(JOptionPane.showInputDialog(null, "Semillas disponibles:"
                +"\n0. Volver"
                +"\n"+Bodega.presentarSemillas()
                +"\nIngrese el número de semilla que desea sembrar.", "SurvivalVille",JOptionPane.INFORMATION_MESSAGE));

                switch(opcionSemilla){
                    case 0:
                    break;
                    default:
                    p1.colocarSemilla((Grama)terreno[i][j], objetos[i][j] ,opcionSemilla);
                    break;
                }

            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);

            }
            
        } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.CONSIEMBRA){

            JOptionPane.showMessageDialog(null, "La siembra aún no se encuentra lista.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);

        } else if (((Grama)terreno[i][j]).getEstado() == EstadoGrama.SIEMBRALISTA){

            objetos[i][j].setVisible(false);
            p1.agregarProducto(((Grama)terreno[i][j]).getPlanta().getProducto());
            JOptionPane.showMessageDialog(null, "Se ha recogido la siembra con éxito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            ((Grama)terreno[i][j]).cambiarEstado(EstadoGrama.DISPONIBLE);

        } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.INFERTIL){

        }
    }

    //metodo para cuando le den click a la etiqueta que contiene el mercado
    private void oyenteMercado(){
        MouseListener oyenteMouse = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Mercado.menuMercado();
            
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
               
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                
            }

        };

        etiquetaMercado.addMouseListener(oyenteMouse);
    }

    //agregamos evento al boton volver
    private void oyenteVolver(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                
                if(p1.isAlive()){ //si el jugador está vivo cuando presione el botón volver
                    
                    p1.setVida(1); //matamos al jugador
                }
                
            }
        };

        botonVolver.addActionListener(oyenteAccion); //agregamos evento al boton
    }

    //agregamos evento al JToggleButton
    private void oyenteSembrar(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){

                    for(int j = 0; j<7; j++){

                        if(terreno[i][j].isEnabled() && terreno[i][j] instanceof Grama){ //si el terreno está desbloqueado y la instancia del objeto es Grama
                            terreno[i][j].setVisible(true); //mostramos el terreno
                            if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.CONSIEMBRA || ((Grama)terreno[i][j]).getEstado() == EstadoGrama.SIEMBRALISTA){
                                objetos[i][j].setVisible(true);
                            }
                        } else {
                            terreno[i][j].setVisible(false); //ocultamos todos los terrenos que no cumplan con estas condiciones
                            objetos[i][j].setVisible(false); //ocultamos los objetos de estos terrenos

                        }
                    }
                }
            }
        };

        sembrar.addActionListener(oyenteAccion); //agregamos evento al boton
    }

    private void oyentePescar(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){

                    for(int j = 0; j<7; j++){

                        if(terreno[i][j].isEnabled() && terreno[i][j] instanceof Lago){ //si el terreno es desbloqueado y la instancia es un Lago
                            terreno[i][j].setVisible(true); //mostramos el terreno
                            if(((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCO){
                                objetos[i][j].setVisible(true);
                            }
                            
                        } else { //si no cumple con las condiciones ocultamos todos los terrenos y objetos
                            terreno[i][j].setVisible(false);
                            objetos[i][j].setVisible(false);
                           

                        }
                    }
                }
            }
        };

        pescar.addActionListener(oyenteAccion);
    }

    private void oyenteVer(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){

                    for(int j = 0; j<7; j++){

                        if(terreno[i][j].isEnabled()){ //si el terreno está desbloqueado 
                            terreno[i][j].setVisible(true); //mostramos el terreno y el objeto
                            if(terreno[i][j] instanceof Lago){
                                if(((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCO){
                                    objetos[i][j].setVisible(true);
                                }
                            } else if(terreno[i][j] instanceof Grama){
                                if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.CONSIEMBRA){
                                    objetos[i][j].setVisible(true);
                                }
                            }
                        } else { //si no está desbloqueado entonces ocultamos el terreno
                            terreno[i][j].setVisible(false); //posible error, agregar que oculte también el objeto
                        
                        }
                    }
                }
            }
        };

        verTerreno.addActionListener(oyenteAccion);
    }

    private void oyenteComprar(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j <7; j++){
                        if(terreno[i][j].isEnabled()){ //si el terreno se encuentra desbloqueado
                            terreno[i][j].setVisible(false); //ocultamos el terreno y su objeto
                            objetos[i][j].setVisible(false);
                        } else {
                            terreno[i][j].setVisible(true); //mostramos el terreno bloqueado para su compra
                        }
                    }
                }

            }
        };

        comprarTerreno.addActionListener(oyenteAccion);
    }

    private void oyenteParcela(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j <6; j++){
                        if(terreno[i][j] instanceof Grama && terreno[i][j+1] instanceof Grama){
                            if(terreno[i][j].isVisible() && terreno[i][j+1].isVisible()){
                                terreno[i][j].setVisible(true);
                                terreno[i][j+1].setVisible(true);
                            }
                        } else {
                            terreno[i][j].setVisible(false);
                            terreno[i][j+1].setVisible(false);
                        }
                    }
                }
            }
        };

        crearParcela.addActionListener(oyenteAccion);
    }


}