package juego;

import javax.swing.*;

import enums.*;
import terrenos.*;
import productos.*; 

import java.awt.Image;

public class Barco extends Thread{

    private Lago lago;
    private Alimento peces;
    private int pecesObtenidos;
    private EstadoBarco estado;
    private ImageIcon imagenBarco= new ImageIcon("barco.png");
    private ImageIcon imagenListo = new ImageIcon("barcoListo.png");
    private JLabel imagenEtiqueta;

    public Barco(){
        this.estado = EstadoBarco.DISPONIBLE;
        this.pecesObtenidos=0;
    }

    public void elegirLago(Lago lago, JLabel imagenEtiqueta){
        this.lago = lago;
        this.imagenEtiqueta = imagenEtiqueta;
        colocarImagen(imagenBarco);
    }

    public void cambiarEstado(EstadoBarco estado){this.estado=estado;}

    public EstadoBarco getEstado(){return this.estado;}

    public int getNumPeces() {return this.pecesObtenidos;}

    public Alimento getPecesPescados(){return this.peces;}

    @Override
    public void run(){
        this.cambiarEstado(EstadoBarco.PESCANDO);
        this.lago.cambiarEstado(EstadoLago.CONBARCO);
        this.imagenEtiqueta.setVisible(true);
        this.pecesObtenidos=0;
        do{

            try{
                Barco.sleep(1000);
            } catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en clase Barco.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            this.lago.pescar();
            
            this.pecesObtenidos++;
        } while(lago.getCantidadPeces() > 0);

        peces = new Alimento("Pescado", 15, this.pecesObtenidos, 10, TipoProducto.SINDESTAZAR);
        this.lago.cambiarEstado(EstadoLago.CONBARCOSINPECES);
        colocarImagen(imagenListo);
    }


    public void colocarImagen(ImageIcon imagen){
        this.imagenEtiqueta.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(this.imagenEtiqueta.getWidth(), this.imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
        
    }
    
}
