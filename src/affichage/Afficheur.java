package affichage;

import java.awt.Color;
import java.awt.Graphics2D;
import blocks.Block;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Afficheur {

    public Graphics2D g;
    Fenetre fenetre;
    
    public Afficheur(Fenetre f) {
        fenetre = f;
    }

    /* permet de préparer l'affichage à chaque frame */
    public void setUp() {
        g = (Graphics2D)fenetre.getBufferStrategy().getDrawGraphics();
        g.setColor(Fenetre.COULEUR_FOND);
        g.fillRect(0, 0, Fenetre.LARGEUR, Fenetre.HAUTEUR);
        try {
            Camera camera = CameraManager.getCamera();
            g.drawImage(ImageIO.read(new File("sky.png")),0,0,null);
            
            
        }
        catch(Exception e) {
        }
    }

    public void cleanUp() {
        g.dispose();
    }

    /* afficher un objet affichable
    IN: x,y coordonnées en bloc du coin supérieur gauche de l'objet */
    public void afficher(Sprite sprite, double x, double y) {
        Camera camera = CameraManager.getCamera();
        g.drawImage(sprite.getImage(), 
            (int) ((x - camera.getX())*Block.TAILLE), 
            (int) ((y - camera.getY())*Block.TAILLE), 
            null);
    }

    public void afficher(BufferedImage image, double x, double y, int width, int height) {
        Camera camera = CameraManager.getCamera();
        g.drawImage(image, 
            (int) ((x - camera.getX())*Block.TAILLE), 
            (int) ((y - camera.getY())*Block.TAILLE), 
            width, height, null);
    }

    /* trace un rectangle
    IN: x,y coordonnées en blocs du coin supérieur gauche
    IN: largeur, hauteur en blocs */
    public void drawRect(double x, double y, double largeur, double hauteur, Color coul) {
        Camera camera = CameraManager.getCamera();
        g.setColor(coul);
        g.drawRect((int) ((x - camera.getX())*Block.TAILLE),
        (int) ((y - camera.getY())*Block.TAILLE),
        (int) (largeur*Block.TAILLE),
        (int) (hauteur*Block.TAILLE));
    }

}
