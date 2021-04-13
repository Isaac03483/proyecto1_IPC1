package terrenos;

import javax.swing.*;

import enums.EstadoLago;

import java.awt.*;


public class Lago extends Terreno implements Runnable{

    private static ImageIcon imagen1 = new ImageIcon("lago.png");
    private int totalPeces;
    private int indiceBarco;
    private int cantidadActualPeces;
    private EstadoLago estado;

    //constructor
    public Lago(){
        
        this.totalPeces = (int)(Math.random()*20)+5;
        this.cantidadActualPeces=0;
        estado = EstadoLago.SINPECES;
        generarPeces();
    }

    public void pescar(){ //método que disminuye el valor de los peces en el lago
        this.cantidadActualPeces--;
        this.setToolTipText("Faltan: "+cantidadActualPeces+" peces.");
    }

    //getters y setters
    public void setIndiceBarco(int indice){this.indiceBarco = indice;}

    public int getIndiceBarco(){return this.indiceBarco;}

    public int getCantidadPeces(){return this.cantidadActualPeces;}

    public EstadoLago getEstado(){return this.estado;}

    public void cambiarEstado(EstadoLago estado){this.estado = estado;}


    @Override
    public void generarTerreno(){ //agrega la imagen a la etiqueta
        this.setIcon(new ImageIcon(imagen1.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }

    @Override
    public void run(){ //método que llena de peces el lago
        
        do {

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                JOptionPane.showMessageDialog(null, "Error en la clase lago.", "SurvivalVille", JOptionPane.ERROR_MESSAGE);
            }
            this.cantidadActualPeces++;
            this.setToolTipText("Cantidad de peces en el lago: "+this.cantidadActualPeces);
        } while(this.cantidadActualPeces < totalPeces);

        this.setToolTipText("El lago está lleno de peces. "+cantidadActualPeces);
        this.estado = EstadoLago.CONPECES;
        
    }

    public void generarPeces(){ //método que llama al hilo
        Thread generarPeces = new Thread(this);
        generarPeces.start();
    }
}
