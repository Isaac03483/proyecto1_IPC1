package ventanas;

import java.awt.*;
import javax.swing.*;

import animales.*;
import enums.*;

import java.awt.event.*;
import terrenos.Lago;
import terrenos.*;
import juego.*;
import productos.Alimento;
import semillas.*;
import static ventanas.Menu.*;
import static ventanas.Inicio.p1;

public class Juego extends JFrame{
    
    public static JPanel panel;
    public static Terreno[][] terreno;
    public static JLabel[][] objetos;
    public static JLabel granero, etiquetaNickName, etiquetaOro, etiquetaVida, etiquetaCorazon, etiquetaMoneda, etiquetaPersonaje, etiquetaMercado;
    public static JButton botonVolver;
    public static JToggleButton verTerreno,crearParcela, comprarTerreno;
    public static int x = 60, y = 160;
    public static int indiceBarco;

    public static final double precioTierra = 20;
    public static final double precioArreglar = 50;
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
        verTerreno.setSelected(true);

        crearParcela = new JToggleButton("Crear Parcela", false);
        crearParcela.setBounds(800,200,175,30);
        panel.add(crearParcela);

        comprarTerreno = new JToggleButton("Comprar Terreno", false);
        comprarTerreno.setBounds(800,240,175,30);
        panel.add(comprarTerreno);
        ButtonGroup grupoBotones = new ButtonGroup();
        grupoBotones.add(verTerreno);
        grupoBotones.add(crearParcela);
        grupoBotones.add(comprarTerreno);

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
        granero.setBounds(60, 50, 150, 130); //creamos las dimensiones y la posici??n en el panel
        granero.setIcon(new ImageIcon(granja.getImage().getScaledInstance(granero.getWidth(), granero.getHeight(), Image.SCALE_SMOOTH))); //creamos la imagen y se la asignamos a la etiqueta
        panel.add(granero); //agregamos la etiqueta al panel

        oyenteGranja();

        //etiqueta para nickName del jugador
        etiquetaNickName = new JLabel(p1.getNickName()); //creamos la etiqueta con texto del m??todo getNickName de clase Jugador
        etiquetaNickName.setBounds(305,47,200,20); //creamos dimensiones y posici??n en el panel
        panel.add(etiquetaNickName); //agregamos la etiqueta al panel
        etiquetaNickName.setFont(new Font("Basic", Font.BOLD,14));
        etiquetaNickName.setForeground(Color.BLACK); //cambiamos el color para est??tica
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
        //etiqueta para imagen de coraz??n
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
                generarTerreno(i, j); //llamamos al m??todo del terreno
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

    //oyentes de acci??n de todos los botones/etiquetas

    private void oyenteGranja(){
        granero.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Bodega.bodega();
            }  
        });

    }

    private void oyenteTerreno(int i, int j){ //le asignamos un evento a todos los terrenos/JLabels
        terreno[i][j].addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent ae) {
                
                if(verTerreno.isSelected()){
                    if(terreno[i][j] instanceof Grama){
                        
                        accionesGrama(i, j);
    
                    } else if(terreno[i][j] instanceof Lago){
                            
                        accionesLago(i, j);

                    } else if(terreno[i][j] instanceof Parcela){
                        accionesParcela(i, j);
                    }

                } else if(comprarTerreno.isSelected()){
                    
                    accionesCompra(i, j);
                } else if(crearParcela.isSelected()){

                    comprarParcela(i, j);
                }
            }

            
        });

        
    }

    

    //metodo para cuando le den click a la etiqueta que contiene el mercado
    private void oyenteMercado(){
        etiquetaMercado.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent ae){
                Mercado.menuMercado();
            }
        });
    }

    //agregamos evento al boton volver
    private void oyenteVolver(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                
                if(p1.isAlive()){ //si el jugador est?? vivo cuando presione el bot??n volver
                    
                    p1.setVida(1); //matamos al jugador
                }
                
            }
        };

        botonVolver.addActionListener(oyenteAccion); //agregamos evento al boton
    }

    

    private void oyenteVer(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                for(int i = 0; i < 7; i++){

                    for(int j = 0; j<7; j++){

                        if(terreno[i][j].isEnabled()){ //si el terreno est?? desbloqueado 
                            terreno[i][j].setVisible(true); //mostramos el terreno y el objeto
                            if(terreno[i][j] instanceof Lago){
                                if(((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCO){
                                    objetos[i][j].setVisible(true);
                                }
                            } else if(terreno[i][j] instanceof Grama){
                                if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.CONSIEMBRA || ((Grama)terreno[i][j]).getEstado() == EstadoGrama.SIEMBRALISTA || ((Grama)terreno[i][j]).getEstado() == EstadoGrama.INFERTIL){
                                    objetos[i][j].setVisible(true);
                                } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.FRUTOLISTO){
                                    objetos[i][j].setVisible(true);
                                }
                            } else if(terreno[i][j] instanceof Parcela){
                                if(((Parcela)terreno[i][j]).getEstado() == EstadoParcela.ANIMALPREPARADO || ((Parcela)terreno[i][j]).getEstado() == EstadoParcela.CONANIMAL || ((Parcela)terreno[i][j]).getEstado() == EstadoParcela.ANIMALMUERTO){
                                    objetos[i][j].setVisible(true);
                                }
                            }
                        } else { //si no est?? desbloqueado entonces ocultamos el terreno
                            terreno[i][j].setVisible(false); //posible error, agregar que oculte tambi??n el objeto
                        
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
                //primero ocultamos todos los terrenos sin importar su instancia o si est??n desbloqueados
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j< 7; j++){
                        terreno[i][j].setVisible(false);
                        objetos[i][j].setVisible(false);
                    }
                }
                //en este apartado veremos la creaci??n de parcelas en las que se piden las siguientes condiciones
                //que el terreno est?? desbloqueado (enabled), que su instancia sea grama y que a su par (arriba, abajo, derecha o izquierda) est?? otro campo que sea Grama
                for(int i = 0; i < 7; i++){
                    for(int j = 0; j < 7; j++){

                        if(terreno[i][j].isEnabled()){ //primero revisamos si est?? desbloqueado (comprado)
                            if(terreno[i][j] instanceof Grama){ //revisamos si su instancia es de Grama
                                //pasamos a revisar en qu?? posici??n de fila se encuentra el ciclo i
                                if(i == 0){ //si el ciclo i se encuentra verificando la primera fila
                                    //dentro de esta condici??n verificaremos en qu?? n??mero de columna se encuentra el ciclo j
                                    if(j == 0){ //si el ciclo j se encuentra en la primera columna

                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su derecha (posicion i = 0, j = 1) es grama
                                            terreno[i][j].setVisible(true); //mostramos ambos terrenos
                                            terreno[i][j+1].setVisible(true);
                                        }

                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno abajo de ??l i = 1 j = 0 es grama
                                            terreno[i][j].setVisible(true); //mostramos ambos terrenos
                                            terreno[i+1][j].setVisible(true);

                                        }
                                    } else if ( j == 6){ //si el ciclo j se encuentra en la ??ltima columna

                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su izquierda es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }

                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno abajo es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i+1][j].setVisible(true);
                                        }
                                    } else { //si el ciclo j se encuentra en una de las columnas intermedias

                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su izquierda es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }
                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su derecha es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j+1].setVisible(true);
                                        }
                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno debajo es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i+1][j].setVisible(true);
                                        }
                                    }

                                    
                                } else if(i == 6){ //si el ciclo i se encuentra verificando la ??ltima fila

                                    //dentro de esta condici??n verificaremos en qu?? n??mero de columna se encuentra el ciclo j
                                    if(j == 0){ //si el ciclo j se encuentra en la primera columna

                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su derecha es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i][j+1].setVisible(true);
                                        }

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){//si el terreno sobre ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }
                                    } else if ( j == 6){ //si el ciclo j se encuentra en la ??ltima columna

                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su izquierda es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){ //si el terreno sobre ??l es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }
                                    } else { //si el ciclo j se encuentra en una de las columnas intermedias
                                        
                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su izquierda es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }

                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su derecha es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j+1].setVisible(true);
                                        }

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){ //si el terreno sobre ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }
                                    }

                                } else { //si el ciclo i se encuentra verificando las filas de en medio

                                    //dentro de esta condici??n verificaremos en qu?? n??mero de columna se encuentra el ciclo j
                                    if(j == 0){ //si el ciclo j se encuentra en la primera columna

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){ //si el terreno sobre ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }

                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno debajo de ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i+1][j].setVisible(true);
                                        }

                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su derecha es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j+1].setVisible(true);
                                        }

                                    } else if ( j == 6){ //si el ciclo j se encuentra en la ??ltima columna

                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su derecha es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){ //si el terreno sobre ??l es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }

                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno debajo de ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i+1][j].setVisible(true);
                                        }
                                    } else { //si el ciclo j se encuentra en una de las columnas intermedias

                                        if(terreno[i][j+1] instanceof Grama && terreno[i][j+1].isEnabled()){ //si el terreno a su izquierda es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i][j+1].setVisible(true);
                                        }

                                        if(terreno[i][j-1] instanceof Grama && terreno[i][j-1].isEnabled()){ //si el terreno a su derecha es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i][j-1].setVisible(true);
                                        }

                                        if(terreno[i-1][j] instanceof Grama && terreno[i-1][j].isEnabled()){ //si el terreno sobre ??l es Grama
                                            terreno[i][j].setVisible(true);
                                            terreno[i-1][j].setVisible(true);
                                        }

                                        if(terreno[i+1][j] instanceof Grama && terreno[i+1][j].isEnabled()){ //si el terreno debajo de ??l es Grama

                                            terreno[i][j].setVisible(true);
                                            terreno[i+1][j].setVisible(true);
                                        }
                                    }

                                }
                            } else { //de no ser as?? se oculta
                                terreno[i][j].setVisible(false);
                            }

                        } else { //de no ser as?? directamente lo ocultamos
                            terreno[i][j].setVisible(false);
                        }
                    }
                }
            }
        };

        crearParcela.addActionListener(oyenteAccion);
    }

    //espacio agregado para todas las acciones del oyenteTerreno
    private void accionesLago(int i, int j){
        if(((Lago)terreno[i][j]).getEstado()== EstadoLago.CONPECES){

            p1.colocarBarco((Lago)terreno[i][j], objetos[i][j]);
            
        } else if (((Lago)terreno[i][j]).getEstado() == EstadoLago.CONBARCO){
            JOptionPane.showMessageDialog(null, "El barco se encuentra pescando en este momento, espere a que todos los peces hayan sido pescados.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
        } else if(((Lago)terreno[i][j]).getEstado() == EstadoLago.SINPECES){
            
            JOptionPane.showMessageDialog(null, "El lago se est?? llenando de peces, por favor espere a que est?? lleno.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            
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
        if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.DISPONIBLE){ //si est?? disponible el terreno puede sembrar semillas
            int opcionSemilla=0;

            try{
                opcionSemilla= Integer.parseInt(JOptionPane.showInputDialog(null, "Semillas disponibles:"
                +"\n0. Volver"
                +"\n"+Bodega.presentarSemillas()
                +"\nIngrese el n??mero de semilla que desea sembrar.", "SurvivalVille",JOptionPane.INFORMATION_MESSAGE));

                switch(opcionSemilla){
                    case 0:
                    break;
                    default:
                    p1.colocarSemilla((Grama)terreno[i][j], objetos[i][j] ,opcionSemilla);
                    break;
                }

            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);

            }
            
        } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.CONSIEMBRA){ //de estar con siembra debe esperar a que termine su proceso

            JOptionPane.showMessageDialog(null, "La siembra a??n no se encuentra lista.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);

        } else if (((Grama)terreno[i][j]).getEstado() == EstadoGrama.SIEMBRALISTA){ //recoge la siembra si ya est?? lista

            
            p1.agregarProducto(((Grama)terreno[i][j]).getPlanta().getProducto()); //new Alimento(((Grama)terreno[i][j]).getPlanta().getProducto().getNombre(), ((Grama)terreno[i][j]).getPlanta().getProducto().getPrecio(),((Grama)terreno[i][j]).getPlanta().getProducto().getCantidad(), ((Alimento)((Grama)terreno[i][j]).getPlanta().getProducto()).getVida(), TipoProducto.SIEMBRA));
            ((Grama)terreno[i][j]).cambiarEstado(EstadoGrama.DISPONIBLE);

        } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.FRUTOLISTO){ //recoge parte del producto producido por el fruto

            p1.agregarProducto(new Alimento(((Grama)terreno[i][j]).getPlanta().getProducto().getNombre(), ((Grama)terreno[i][j]).getPlanta().getProducto().getPrecio(), ((Fruto)((Grama)terreno[i][j]).getPlanta()).getCantidadProducto(), ((Alimento)((Grama)terreno[i][j]).getPlanta().getProducto()).getVida(), TipoProducto.FRUTO));
            JOptionPane.showMessageDialog(null, "Se ha recogido una parte de los productos.", "SuvivalVille", JOptionPane.INFORMATION_MESSAGE);
            ((Grama)terreno[i][j]).cambiarEstado(EstadoGrama.CONSIEMBRA);
            
        } else if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.INFERTIL){ //si es infertil debe pagar para limpiar el terreno
            
            try{

                int arreglarTerreno = Integer.parseInt(JOptionPane.showInputDialog(null, "Una siembra se pudri?? por lo que debe pagar para poder usarla de nuevo. Precio: "+ precioArreglar
                +"\n??Desea arreglar el terreno?"
                +"\n1. S??."
                +"\n2. No.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                switch(arreglarTerreno){
                    case 1:
                    if(p1.getOro() >= precioArreglar){
                        p1.disminuirOro(precioArreglar);
                        ((Grama)terreno[i][j]).cambiarEstado(EstadoGrama.DISPONIBLE);
                        objetos[i][j].setVisible(false);
                        JOptionPane.showMessageDialog(null, "Terreno limpiado con ??xito.", "survivalVille", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No posees el oro suficiente.", "survivalVille", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                    case 2:
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Opci??n incorrecta.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            } catch(NumberFormatException e){

                JOptionPane.showMessageDialog(null, "Debe ingresar un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void accionesCompra(int i, int j){
        if(!terreno[i][j].isEnabled()){

            if(p1.getOro() >=precioTierra){ //si posee dinero suficiente entonces puede comprar m??s tierras

                try{

                    int opcionCompra = Integer.parseInt(JOptionPane.showInputDialog(null, "Precio de la tierra: "+precioTierra
                    +"\n??Desea comprarla?"
                    +"\n1. S??."
                    +"\n2. No.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                    switch(opcionCompra){
                        case 1:
                        JOptionPane.showMessageDialog(null, "Tierra comprada con ??xito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        p1.disminuirOro(precioTierra);
                        p1.setCeldas();
                        etiquetaOro.setText(""+p1.getOro());
                        terreno[i][j].setEnabled(true);
                        break;
                        case 2:
                        break;
                        default:
                        break;
                    }

                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Solo puede ingresar  un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "No cuenta con el dinero suficiente", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void comprarParcela(int i, int j){

        if(terreno[i][j] instanceof Grama){
            if(((Grama)terreno[i][j]).getEstado() == EstadoGrama.DISPONIBLE){
                if(p1.getOro() >= precioTierra){ //si cuenta con el dinero suficiente puede comprar una nueva parcela

                    try{

                        int opcionCompra = Integer.parseInt(JOptionPane.showInputDialog(null, "Precio para crear Parcela: "+precioTierra
                        +"\n??Desea crear una parcela?"
                        +"\n1. S??."
                        +"\n2. No.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));
    
                        switch(opcionCompra){
                            case 1:
                            terreno[i][j].setVisible(false);
                            int x = terreno[i][j].getX();
                            int y = terreno[i][j].getY();
                            int ancho = terreno[i][j].getWidth();
                            int largo = terreno[i][j].getHeight();

                            terreno[i][j] = new Parcela();
                            terreno[i][j].setBounds(x, y, ancho, largo);
                            panel.add(terreno[i][j]);
                            terreno[i][j].generarTerreno();
                            oyenteTerreno(i, j);
                            JOptionPane.showMessageDialog(null, "Parcela creada con ??xito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                            p1.disminuirOro(precioTierra);
                            break;
                            case 2:
                            break;
                            default:
                            break;
                        }
    
                    } catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(null, "Solo puede ingresar  un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No cuenta con el dinero suficiente", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El terreno se encuentra con una siembra en proceso.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void accionesParcela(int i, int j){

        if(terreno[i][j].isEnabled()){

            if(((Parcela)terreno[i][j]).getEstado() == EstadoParcela.DISPONIBLE){
                try{
                    int opcionAnimal = Integer.parseInt(JOptionPane.showInputDialog(null, "Animales Disponibles:"
                    +"\n0. Volver."
                    +"\n"+Bodega.presentarAnimales(), "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                    switch(opcionAnimal){
                        case 0:
                        break;
                        default:
                        p1.colocarAnimal(((Parcela)terreno[i][j]), objetos[i][j], opcionAnimal);
                        break;
                    }
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Debe ingresar un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }
            } else if(((Parcela)terreno[i][j]).getEstado() == EstadoParcela.CONANIMAL){

                JOptionPane.showMessageDialog(null, "Un animal se encuentra creciendo  en este momento.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);

            } else if(((Parcela)terreno[i][j]).getEstado() == EstadoParcela.ANIMALPREPARADO){

                ((Parcela)terreno[i][j]).cambiarEstado(EstadoParcela.DISPONIBLE);
                
                for(int x = 0; x < ((Parcela)terreno[i][j]).getAnimal().getArregloProductos().length; x++){

                    p1.agregarProducto(((Parcela)terreno[i][j]).getAnimal().getArregloProductos()[x]);

                }

                Juego.actualizarAnimalesDestazados(((Parcela)terreno[i][j]).getAnimal());

                JOptionPane.showMessageDialog(null, "Todos los productos se han recogido con ??xito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                
            } else if(((Parcela)terreno[i][j]).getEstado() == EstadoParcela.ANIMALMUERTO){

                try{

                    int arreglarParcela = Integer.parseInt(JOptionPane.showInputDialog(null, "Un animal muri?? en esta parcela por lo que debe limpiarla para poder usarla de nuevo. Precio: "+precioArreglar
                    +"\n??Desea arreglar la parcela?"
                    +"\n1. S??."
                    +"\n2. No.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE));

                    switch(arreglarParcela){

                        case 1:
                        if(p1.getOro() >= precioArreglar){
                            p1.disminuirOro(precioArreglar);
                            ((Parcela)terreno[i][j]).cambiarEstado(EstadoParcela.DISPONIBLE);
                            objetos[i][j].setVisible(false);
                            JOptionPane.showMessageDialog(null, "Parcela limpiada con ??xito.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        } else{
                            JOptionPane.showMessageDialog(null, "No posees el oro suficiente.", "SurvivalVille", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                        case 2:
                        break;
                        default:
                        JOptionPane.showMessageDialog(null, "Opci??n incorrecta.", "SuvivalVille", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                } catch(NumberFormatException e){

                    JOptionPane.showMessageDialog(null, "Debe ingresar un valor num??rico.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                }


            }
        }
    }
    //fin espacio para acciones

    public static void actualizarCeldasSembradas(Planta semilla){ //actualiza el n??mero de celdas sembradas de la semilla que recibe como par??metro
        int indice =0;
        if(semilla instanceof Grano){
            for(int i = 0; i < granos.length; i++){
                if(semilla.getNombre() == granos[i].getNombre()){
                    indice = i;
                    break;
                }
            }

            granos[indice].setCeldasSembradas();
            
        } else if (semilla instanceof Fruto){
            for(int i = 0; i < frutos.length; i++){
                if(semilla.getNombre() == frutos[i].getNombre()){
                    indice = i;
                    break;
                }
            }

            frutos[indice].setCeldasSembradas();
        }
    }

    public static void actualizarAnimalesDestazados(Animal animal){ //actualiza el n??mero de animales destazados del animal enviado por par??metro

        int indice =0;
        if(animal instanceof Herbivoro){
            for(int i = 0; i < animalesHerbivoros.length; i++){
                if(animal.getNombre() == animalesHerbivoros[i].getNombre()){
                    indice = i;
                    break;
                }
            }

            animalesHerbivoros[indice].setCriasDestazadas();
            
        } else if (animal instanceof Omnivoro){
            for(int i = 0; i < animalesOmnivoros.length; i++){
                if(animal.getNombre() == animalesOmnivoros[i].getNombre()){
                    indice = i;
                    break;
                }
            }

            animalesOmnivoros[indice].setCriasDestazadas();
        }
    }


}