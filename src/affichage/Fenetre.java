package affichage;

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;

public class Fenetre extends Canvas {
    
    /* La largeur de la fenêtre */
    public static final int LARGEUR = 1400;

    /* La hauteur de la fenêtre */
    public static final int HAUTEUR = 800;

    /* Le titre de la fenêtre */
    public static final String NOM = "Minecraft 2D - v1";

    /* La couleur de fond de la fenêtre */
    public static final Color COULEUR_FOND = Color.BLUE;

    /* La fenêtre principale */
    private JFrame frame;

    public Fenetre() {
        this.setFocusable(true);
        frame = new JFrame(NOM);
        setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /* préparer l'affichagge à chaque frame */
    public void setup() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
    }

    /* affiche la frame à l'écran */
    public void afficher() {
        getBufferStrategy().show();
    }

}
