package ventanas;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import personajes.*;
import static ventanas.Menu.*;



public class Inicio extends JFrame{

    private JLabel etiquetaNombre, etiquetaNick;
    private JPanel panel;
    private JTextField textoNombre,  textoNick;
    private JButton botonInicio, botonVolver;
    private JCheckBox terminos;
    public static Jugador p1;
    public static Juego v1;
    

    public Inicio(){

        this.setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SurvivalVille");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        colocarPanel();
        colocarEtiquetas();
        colocarTexto();
        colocarBoton();
        colocarcheck();
    }
    

    private void colocarPanel(){

        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(0,170,61));
    }


    private void colocarEtiquetas(){

        etiquetaNombre = new JLabel("Nombre: ",SwingConstants.CENTER);
        
        etiquetaNombre.setBounds(50,50,90,20);
        panel.add(etiquetaNombre);

        etiquetaNick = new JLabel("Nickname: ", SwingConstants.CENTER);
        
        etiquetaNick.setBounds(50,120,90,20);
        panel.add(etiquetaNick);

    }

    private void colocarTexto(){
        textoNombre = new JTextField();
        textoNombre.setBounds(150,50,200,30);
        panel.add(textoNombre);
        textoNombre.setText("");

        textoNick = new JTextField();
        textoNick.setBounds(150,120,200,30);
        panel.add(textoNick);
        textoNick.setText("");
    }

    private void colocarBoton(){
        botonInicio = new JButton("Iniciar");
        botonInicio.setBounds(160,200,80,50);
        botonInicio.setEnabled(false);
        panel.add(botonInicio);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(265,200,80,50);
        panel.add(botonVolver);

        oyenteBoton();
        oyenteVolver();
    }

    private void colocarcheck(){

        terminos = new JCheckBox("Aceptar términos y condiciones.");
        terminos.setBounds(120,160,300,30);
        terminos.setBackground(null);
        panel.add(terminos);

        oyenteTerminos();
    }

    private void oyenteTerminos(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                if(terminos.isSelected()){
                    botonInicio.setEnabled(true);
                } else {
                    botonInicio.setEnabled(false);
                }
            } 
        };
        terminos.addActionListener(oyenteAccion);
    }

    private void oyenteBoton(){
        ActionListener oyenteAccion = new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae){
                String nombre = textoNombre.getText();
                String nick = textoNick.getText();
                if(nombre.equals("") || nick.equals("")){

                    JOptionPane.showMessageDialog(null, "Uno de los campos se encuentra vacío", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
                
                } else {

                    p1 =new Jugador(nombre, nick, 2000,350);
                    v1 = new Juego();
                    v1.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Como recompensa de inicio de sesión recibiste 2000 monedas de Oro.", "Bienvenida", 1);
                    dispose();
                    
                }
                
            }
        };

        botonInicio.addActionListener(oyenteAccion);
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
}
