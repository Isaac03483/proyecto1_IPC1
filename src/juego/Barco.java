package juego;

import javax.swing.*;

import enums.*;
import terrenos.*;
import productos.*;
import static ventanas.Inicio.*;

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
        colocarImagen(imagenEtiqueta);
    }

    public void cambiarEstado(EstadoBarco estado){this.estado=estado;}

    public EstadoBarco getEstado(){return this.estado;}

    public int getNumPeces() {return this.pecesObtenidos;}

    public Alimento getPecesPescados(){return this.peces;}

    @Override
    public void run(){
        this.cambiarEstado(EstadoBarco.PESCANDO);
        this.lago.cambiarEstado(EstadoLago.CONBARCO);
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
        this.imagenEtiqueta.setIcon(new ImageIcon(this.imagenListo.getImage().getScaledInstance(imagenEtiqueta.getWidth(), imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
    }


    public void colocarImagen(JLabel imagenEtiqueta){
        imagenEtiqueta.setIcon(new ImageIcon(this.imagenBarco.getImage().getScaledInstance(imagenEtiqueta.getWidth(), imagenEtiqueta.getHeight(), Image.SCALE_SMOOTH)));
        this.imagenEtiqueta=imagenEtiqueta;
        this.imagenEtiqueta.setVisible(true);
    }
    
}
