package terrenos;

import javax.swing.*;
import java.awt.*;


public class Desierto extends Terreno {

    static ImageIcon imagen3 = new ImageIcon("tierra.png");
    

    @Override
    public void generarTerreno(){ //agrega la imagen a la etiqueta
        this.setIcon(new ImageIcon(imagen3.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }
}
