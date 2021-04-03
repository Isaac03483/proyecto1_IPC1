package terrenos;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Parcela extends Grama{
    
    private ImageIcon imagenParcela = new ImageIcon("parcela.png");

    public Parcela(){
        generarTerreno();
    }

    @Override
    public void generarTerreno(){
        this.setIcon(new ImageIcon(imagenParcela.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
    }
}
